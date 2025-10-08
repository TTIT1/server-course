package com.example.springjpa.exception;

public enum ErrorCode {
    UNAUTHORIZED(1001, "Chưa đăng nhập hoặc token hết hạn"),
    FORBIDDEN(1002, "Không có quyền truy cập"),
    INVALID_CREDENTIALS(1003, "Sai tên đăng nhập hoặc mật khẩu"),

    // 🔹 Lỗi dữ liệu đầu vào
    INVALID_INPUT(2001, "Dữ liệu không hợp lệ"),
    MISSING_FIELD(2002, "Thiếu trường bắt buộc"),
    INVALID_EMAIL(2003, "Email đã tồn tại"),

    // 🔹 Lỗi nghiệp vụ
    USER_EXISTS(3001, "Người dùng đã tồn tại"),
    USER_NOT_FOUND(3002, "Không tìm thấy người dùng"),
    DUPLICATE_RECORD(3003, "Bản ghi đã tồn tại"),

    // 🔹 Lỗi hệ thống
    INTERNAL_ERROR(4001, "Lỗi hệ thống"),
    DATABASE_ERROR(4002, "Lỗi kết nối cơ sở dữ liệu"),
    SERVICE_UNAVAILABLE(4003, "Dịch vụ tạm thời không khả dụng"),

    // 🔹 Lỗi chung
    BAD_REQUEST(5001, "Yêu cầu không hợp lệ"),
    NOT_FOUND(5002, "Không tìm thấy tài nguyên"),
    TIMEOUT(5003, "Quá thời gian chờ"),
    SUCCESS(0, "Thành công"),
    registerNew_SUCCESS(10, "Đăng ký thành công")
    ;


    ErrorCode(int code, String messages) {
        this.code = code;
        this.messages = messages;
    }

    private int code;
    private  String messages;

    public int getCode() {
        return code;
    }

    public String getMessages() {
        return messages;
    }



}
