package com.example.springjpa.ai.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.intellij.lang.annotations.JdkConstants;

@Data
@FieldDefaults(level = AccessLevel.PACKAGE,makeFinal = true)
public class ChatResquest {
    String messger;
}
