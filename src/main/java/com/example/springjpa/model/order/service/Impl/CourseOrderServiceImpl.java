package com.example.springjpa.model.order.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springjpa.dto.response.CouresAllResponse.CourseDetailResponse;
import com.example.springjpa.enums.order.OrderStatus;
import com.example.springjpa.enums.wallet.WalletTransactionStatus;
import com.example.springjpa.enums.wallet.WalletTransactionType;
import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.mapper.CourseMapper;
import com.example.springjpa.model.auth.User;
import com.example.springjpa.model.course.Course;
import com.example.springjpa.model.nevenue.repository.NevenueRepository;
import com.example.springjpa.model.nevenue.service.NevenueService;
import com.example.springjpa.model.order.CourseOrder;
import com.example.springjpa.model.order.CourseOrderRepository;
import com.example.springjpa.model.order.dto.response.CourseOrderResponse;
import com.example.springjpa.model.order.dto.resquest.CourseOrderRequest;
import com.example.springjpa.model.order.mapper.CourseOrderMapper;
import com.example.springjpa.model.order.service.CourseOrderService;
import com.example.springjpa.model.wallet.Wallet;
import com.example.springjpa.model.wallet.WalletTransaction;
import com.example.springjpa.repository.AuthorRepository;
import com.example.springjpa.repository.CourseRepository;
import com.example.springjpa.repository.UserRepository;
import com.example.springjpa.repository.WalletRepository;
import com.example.springjpa.repository.WalletTransactionRepository;
import com.example.springjpa.security.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Service
@RequiredArgsConstructor
@Log4j2
public class CourseOrderServiceImpl implements CourseOrderService {
    
    private final CourseOrderRepository courseOrderRepository;
    private final CourseOrderMapper mapperCourseOrder;
    private final WalletRepository walletRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userrepository;
    private final WalletTransactionRepository walletTransactionRepository ;
    private final NevenueService nevenueService;
    private final AuthorRepository authorRepository;
    private final NevenueRepository nevenueRepository;
    private final JwtAuthenticationFilter authenticationFilter;
    private final CourseMapper courseMapper;
    
    public CourseOrderResponse saveCourseOrder( CourseOrderRequest courseOrderResquest) {
      
       

        Boolean check = courseOrderRepository.existsByUserIdAndCourseId(courseOrderResquest.getUserId(), courseOrderResquest.getCourseId());
        if(check){
            throw  new AppExcepotion(ErrorCode.COURSE_ALREADY_PURCHASED);
        }
       User user = userrepository.findById(courseOrderResquest.getUserId())
        .orElseThrow(() -> new AppExcepotion(ErrorCode.USER_NOT_FOUND));

        if (user.getWallet() == null) {
            throw new AppExcepotion(ErrorCode.WALLET_NOT_FOUND);
        }

        Wallet wallet = walletRepository.findById(user.getWallet().getId())
                .orElseThrow(() -> new AppExcepotion(ErrorCode.WALLET_NOT_FOUND));

        Course course = courseRepository.findById(courseOrderResquest.getCourseId())
                .orElseThrow(() -> new AppExcepotion(ErrorCode.COURSE_NOT_FOUND));

        if (wallet.getBalance().compareTo(course.getPrice()) < 0) {
            throw new AppExcepotion(ErrorCode.INSUFFICIENT_BALANCE);
        }

        wallet.setBalance(wallet.getBalance().subtract(course.getPrice()));

        walletRepository.save(wallet);

       
         
        
             WalletTransaction transaction = new WalletTransaction();
             transaction.setStatus(WalletTransactionStatus.SUCCESS);
             transaction.setType(WalletTransactionType.PURCHASE);
             transaction.setAmount(course.getPrice());
             transaction.setWallet(wallet);
             transaction.setUpdatedBy(authenticationFilter.getIdByUsertoToken());
             walletTransactionRepository.save(transaction);
       
        

        CourseOrder  courseOrder =  mapperCourseOrder.toCourseOrder(courseOrderResquest);
                    courseOrder.setUser(user);
                    courseOrder.setCourse(course);
                    courseOrder.setStatus(OrderStatus.PAID);
                    courseOrder.setPrice(course.getPrice());
                    courseOrder.setUpdatedBy(authenticationFilter .getIdByUsertoToken());
                    courseOrderRepository.save(courseOrder);
        
        
      
       return  CourseOrderResponse.builder()
              .userId(courseOrder.getUser().getId())
              .courseId(courseOrder.getCourse().getId())
              .price(courseOrder.getPrice().abs())
              .status(courseOrder.getStatus())
       .build();

        
    }



    @Override
    @Transactional(readOnly = true)
    public CourseDetailResponse getPurchasedCourseDetail(String courseId) {
       
        String userId = authenticationFilter.getIdByUsertoToken();
        userrepository.findById(userId)
                .orElseThrow(() -> new AppExcepotion(ErrorCode.USER_NOT_FOUND));

        boolean hasBought = courseOrderRepository.userHasBoughtCourse(userId, courseId);
        if (!hasBought) {
            throw new AppExcepotion(ErrorCode.COURSE_NOT_PURCHASED);
        }

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new AppExcepotion(ErrorCode.COURSE_NOT_FOUND));

        return courseMapper.toCourseDetailDTO(course);
    }


   
    
    
    
}
