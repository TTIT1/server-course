package com.example.springjpa.model.order.service.Impl;

import org.springframework.stereotype.Service;

import com.example.springjpa.enums.order.OrderStatus;
import com.example.springjpa.enums.wallet.WalletTransactionStatus;
import com.example.springjpa.enums.wallet.WalletTransactionType;
import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.model.auth.User;
import com.example.springjpa.model.course.Course;
import com.example.springjpa.model.order.CourseOrder;
import com.example.springjpa.model.order.CourseOrderRepository;
import com.example.springjpa.model.order.dto.response.CourseOrderResponse;
import com.example.springjpa.model.order.dto.resquest.CourseOrderRequest;
import com.example.springjpa.model.order.mapper.CourseOrderMapper;
import com.example.springjpa.model.order.service.CourseOrderService;
import com.example.springjpa.model.wallet.Wallet;
import com.example.springjpa.model.wallet.WalletTransaction;
import com.example.springjpa.repository.CourseRepository;
import com.example.springjpa.repository.UserRepository;
import com.example.springjpa.repository.WalletRepository;
import com.example.springjpa.repository.WalletTransactionRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor

public class CourseOrderServiceImpl implements CourseOrderService {
    
    private final CourseOrderRepository courseOrderRepository;
    private final CourseOrderMapper mapperCourseOrder;
    private final WalletRepository walletRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userrepository;
    private final WalletTransactionRepository walletTransactionRepository ;
    public CourseOrderResponse saveCourseOrder(CourseOrderRequest courseOrderResquest) {
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

        // Boolean walletTransaction = walletTransactionRepository.existsByWalletId(wallet.getId());

        // if(walletTransaction){
             WalletTransaction transaction = new WalletTransaction();
             transaction.setStatus(WalletTransactionStatus.SUCCESS);
             transaction.setType(WalletTransactionType.PURCHASE);
             transaction.setAmount(course.getPrice());
             transaction.setWallet(wallet);
             walletTransactionRepository.save(transaction);
        //}
        

        CourseOrder  courseOrder =  mapperCourseOrder.toCourseOrder(courseOrderResquest);
                    courseOrder.setUser(user);
                    courseOrder.setCourse(course);
                    courseOrder.setStatus(OrderStatus.PAID);
                    courseOrderRepository.save(courseOrder);

              return CourseOrderResponse.builder()
                .userId(user.getId())
                .courseId(course.getId())
                .price(course.getPrice())
                .status(courseOrder.getStatus())
                .build();

        
    }

   
    

    
}
