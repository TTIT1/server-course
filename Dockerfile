# 1. Chọn base image JDK 23 nhẹ
FROM eclipse-temurin:23-jre-alpine

# 2. Đặt thư mục làm việc
WORKDIR /app

# 3. Copy file jar vào container
COPY target/*.jar app.jar

# 4. Mở cổng cho Spring Boot
EXPOSE 8080

# 5. Chạy app
ENTRYPOINT ["java", "-jar", "app.jar"]
