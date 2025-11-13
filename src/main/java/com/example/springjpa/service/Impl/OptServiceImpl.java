package com.example.springjpa.service.Impl;

import com.example.springjpa.service.OptService;
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

    @Override
    public String generateOTP(String email, int minutesValid) {
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
