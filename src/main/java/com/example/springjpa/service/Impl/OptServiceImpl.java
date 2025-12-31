package com.example.springjpa.service.Impl;

import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.model.auth.User;
import com.example.springjpa.repository.UserRepository;
import com.example.springjpa.service.OptService;
import com.example.springjpa.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Service
public class OptServiceImpl implements OptService {
    SecureRandom random = new SecureRandom();
    Map<String,OTPEntry> store = new ConcurrentHashMap<>();
  UserRepository  userRepository;;
    @Override
    public String generateOTP(String email, int minutesValid) {
        User user = userRepository.findBygmail(email).orElseThrow(()->new AppExcepotion(ErrorCode.NOT_FOUND));

        int opt = random.nextInt(900000);
        long expiry = Instant.now().getEpochSecond()+minutesValid * 60;
        OTPEntry entry = new OTPEntry(String.valueOf(opt), expiry);
        store.put(email,new OTPEntry(String.valueOf(opt), expiry));
      return String.valueOf(opt);
    }

    @Override
    public boolean verifyOTP(String email, String otp) {
            OTPEntry entry = store.get(email);
            if (entry == null) {return false;}
            // kiểm tra xem thời gian hạn của opt còn hay không
        // nếu không còn thi nó hếthanjn xóa kay gmail này trong mapp
            if (Instant.now().getEpochSecond() > entry.expiry) {
                store.remove(email);
            }
            Boolean result = entry.otp.equals(otp);
            return result;
    }


      class OTPEntry {
        final String otp;
        final long expiry;
        OTPEntry(String otp, long expiry){ this.otp = otp; this.expiry = expiry; }
    }
}
