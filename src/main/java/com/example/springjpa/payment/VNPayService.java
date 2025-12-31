package com.example.springjpa.payment;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.springjpa.enums.wallet.WalletTransactionStatus;
import com.example.springjpa.enums.wallet.WalletTransactionType;
import com.example.springjpa.model.auth.User;
import com.example.springjpa.model.wallet.Wallet;
import com.example.springjpa.model.wallet.WalletTransaction;
import com.example.springjpa.repository.UserRepository;
import com.example.springjpa.repository.WalletRepository;
import com.example.springjpa.repository.WalletTransactionRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class VNPayService {

    private final VNPAYConfig config;
    private final WalletTransactionRepository transactionRepo;
   private  final UserRepository userRepository;
   private final WalletRepository walletrepository;
    public String createTopUpUrl(HttpServletRequest request, long amount) {
        

     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
     String name = authentication.getName();
     User user = userRepository.findByUserName(name).orElseThrow(()-> new RuntimeException("User not found"));
     Wallet wallet = walletrepository.findById(user.getWallet().getId()).orElseThrow(()-> new RuntimeException("Wallet not found"));

        // 1. creat money transaction
        WalletTransaction tx = new WalletTransaction();
        tx.setWallet(wallet);
        tx.setAmount(BigDecimal.valueOf(amount));
        tx.setType(WalletTransactionType.TOP_UP);
        tx.setStatus(WalletTransactionStatus.PENDING);
        tx.setCreatedAt(LocalDateTime.now());
        tx = transactionRepo.save(tx);
        
      
        // 2. creat params VNPay
        Map<String, String> params = config.getBaseParams();
        params.put("vnp_TxnRef", tx.getId().toString());
        params.put("vnp_OrderInfo", "Nap tien vao tai khoan");
        params.put("vnp_Amount", String.valueOf(amount * 100));
        params.put("vnp_IpAddr", request.getRemoteAddr());

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        params.put("vnp_CreateDate", formatter.format(calendar.getTime()));
        calendar.add(Calendar.MINUTE, 15);
        params.put("vnp_ExpireDate", formatter.format(calendar.getTime()));

        // 3. Hash & build query
        List<String> fieldNames = new ArrayList<>(params.keySet());
        Collections.sort(fieldNames);

        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        for (String field : fieldNames) {
            String value = params.get(field);
            if (value != null && !value.isEmpty()) {
                hashData.append(field).append('=').append(value).append('&');
                query.append(URLEncoder.encode(field, StandardCharsets.UTF_8))
                     .append('=')
                     .append(URLEncoder.encode(value, StandardCharsets.UTF_8))
                     .append('&');
            }
        }
        hashData.deleteCharAt(hashData.length() - 1);
        query.deleteCharAt(query.length() - 1);

        String secureHash = VNPayUtil.hmacSHA512(config.getSecretKey(), hashData.toString());
        query.append("&vnp_SecureHash=").append(secureHash);

        return config.getVnp_PayUrl() + "?" + query;
    }

    public void handleCallback(HttpServletRequest request) {

        Map<String, String> params = new HashMap<>();
        request.getParameterMap().forEach((key, value) ->
                params.put(key, value[0])
        );

        String secureHash = params.remove("vnp_SecureHash");
        params.remove("vnp_SecureHashType");

        // 1. Verify chữ ký
        if (!VNPayUtil.verifySignature(params, secureHash, config.getSecretKey())) {
            throw new RuntimeException("INVALID_SIGNATURE");
        }

        // 2. Lấy dữ liệu
        String txId = params.get("vnp_TxnRef");
        String responseCode = params.get("vnp_ResponseCode");
        long amountFromVnp = Long.parseLong(params.get("vnp_Amount")) / 100;

        WalletTransaction tx = transactionRepo.findById(txId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        // 3. Chống callback nhiều lần
        if (tx.getStatus() != WalletTransactionStatus.PENDING) {
            return;
        }

        // 4. Check amount
        if (tx.getAmount().longValue() != amountFromVnp) {
            tx.setStatus(WalletTransactionStatus.FAILED);
            transactionRepo.save(tx);
            return;
        }

        // 5. Update trạng thái
        if ("00".equals(responseCode)) {
            tx.setStatus(WalletTransactionStatus.SUCCESS);
             Wallet wallet = tx.getWallet();
            wallet.setBalance(wallet.getBalance().add(tx.getAmount()));
        } else {
            tx.setStatus(WalletTransactionStatus.FAILED);
        }

        transactionRepo.save(tx);
    }
}