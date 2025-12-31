package com.example.springjpa.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class VNPayController {

    private final VNPayService vnPayService;
  @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'AUTHOR')")
    @PostMapping("/topup")
public void topUp(
        @RequestParam long amount,
        HttpServletRequest request,
        HttpServletResponse response
) throws IOException, java.io.IOException {

    String url = vnPayService.createTopUpUrl(request, amount);
    response.sendRedirect(url);
}
  
 @GetMapping("/vn-pay-callback")
    public ResponseEntity<String> vnPayCallback(HttpServletRequest request) {
        vnPayService.handleCallback(request);
        return ResponseEntity.ok("PAYMENT_SUCCESS");
    }
}

