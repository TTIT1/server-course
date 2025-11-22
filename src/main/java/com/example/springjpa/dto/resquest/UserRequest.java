package com.example.springjpa.dto.resquest;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequest{

    @Schema(description = "Tên đăng nhập", example = "admin@gmail.com", required = true)
    String gmail;

    @Schema(
            description = "Mật khẩu",
            example = "admin123",
            required = true,
            format = "password"
    )
    String PassWordUser;
}
