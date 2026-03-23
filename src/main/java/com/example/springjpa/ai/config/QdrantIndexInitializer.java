package com.example.springjpa.ai.config;


import io.qdrant.client.QdrantClient;
import io.qdrant.client.grpc.Collections.PayloadSchemaType;
import io.qdrant.client.grpc.Points;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import java.time.Duration;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QdrantIndexInitializer {

    private final QdrantClient qdrantClient;
@PostConstruct
public void createIndex() {
    try {
        qdrantClient.createPayloadIndexAsync(
            Points.CreateFieldIndexCollection.newBuilder()
                .setCollectionName("documents")
                .setFieldName("type")
                .setFieldType(Points.FieldType.FieldTypeKeyword)
                .build(),
            Duration.ofSeconds(5)
        ).get();
        System.out.println("Qdrant index created successfully");
    } catch (Exception e) {
        System.out.println("Index already exists: " + e.getMessage());
    }
}
}