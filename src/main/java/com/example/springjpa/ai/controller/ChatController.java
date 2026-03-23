package com.example.springjpa.ai.controller;

import com.example.springjpa.ai.dto.ChatResponse;
import com.example.springjpa.ai.dto.ChatResquest;
import com.example.springjpa.ai.service.ChatService;
import com.example.springjpa.dto.response.ApiResponse;
import com.example.springjpa.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.checkerframework.checker.nonempty.qual.RequiresNonEmpty;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/chat")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class ChatController {
    ChatService chatService;

    @PostMapping("/chat-ai")
    public ResponseEntity<ApiResponse<ChatResponse>> chatAI(@RequestBody ChatResquest chatResquest){
        ApiResponse<ChatResponse>response = new ApiResponse<>();
         response.setRsulte(chatService.chat(chatResquest));
         response.setMessages(ErrorCode.SUCCESS.getMessage());
         response.setCode(ErrorCode.SUCCESS.getCode());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
