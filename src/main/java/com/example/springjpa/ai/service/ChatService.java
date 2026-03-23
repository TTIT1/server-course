package com.example.springjpa.ai.service;

import com.example.springjpa.ai.dto.ChatResponse;
import com.example.springjpa.ai.dto.ChatResquest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class ChatService {
        
     

    ChatClient chatClient;
    //RagService ragService;
    VectorStore vectorStore;
    TokenTextSplitter textSplitter;

    public ChatResponse chat    (ChatResquest chatResquest){
        // Tạo id cho chat
        String  conversationID   = UUID.randomUUID().toString();
        String textChat = chatResquest.getMessger();
        int topK = 5;
        // tìm tài liẹue trong dataa
        List<Document> docs = vectorStore.similaritySearch(
                SearchRequest.builder()
                        .query(textChat)
                        .topK(topK)
                        .filterExpression("type == 'doc'")
                        .build()
        );
        String context = docs.isEmpty()
                ?"(no relevant documents)"
                :docs.stream()
                .map((Document::getText))//
                .reduce((a,b) -> a+ "\n\n ---\n\n"+b)
                .orElse("");

        String prompt = """
        Bạn là trợ lý hữu ích. Trả lời dựa trên ngữ cảnh bên dưới.
        Nếu không đủ thông tin hãy dùng kiến thức của bạn.
        
        Ngữ cảnh:
        %s
        
        Câu hỏi: %s
        """.formatted(context, textChat);
        String answer = chatClient.prompt()
               
                .user(prompt)
                .call()
                .content();

 persistChat(conversationID, "plain", textChat, answer);

     return ChatResponse.builder()
             .messger(answer)
             .build();

    }

    public void persistChat (String conversationId ,String mode,String userText,String assistantText){
        Instant now = Instant.now();

        // log hội thoại (type=chat)
        Document userChat = new Document(userText, Map.of(
                "type", "chat",
                "role", "user",
                "mode", mode,
                "conversationId", conversationId,
                "ts", now.toString()
        ));
        // dung de tach biet giua document de RAG va document de luu history chat, de sau nay co the loc de hien thi lich su hoi thoai ma khong can hien thi document de RAG
        Document assistantChat = new Document(assistantText, Map.of(
                "type", "chat",
                "role", "assistant",
                "mode", mode,
                "conversationId", conversationId,
                "ts", now.toString()
        ));
        String docText = """
                [Conversation mode=%s id=%s]
                USER:
                %s

                ASSISTANT:
                %s
                """.formatted(mode, conversationId, userText, assistantText);

        Document docForRag = new Document(docText, Map.of(
                "type", "doc",
                "source", "chat",
                "mode", mode,
                "conversationId", conversationId,
                "ts", now.toString()
        ));

        List<Document>chumk = textSplitter.split(userChat);
        chumk.addAll(textSplitter.split(assistantChat));
        chumk.addAll(textSplitter.split(docForRag));
        vectorStore.add(chumk);

    }








}
