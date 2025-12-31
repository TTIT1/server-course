# Online Course Management System (Backend)

## 📌 Giới thiệu

Hệ thống quản lý khóa học trực tuyến là một **backend REST API** được xây dựng bằng **Spring Boot**, phục vụ cho nền tảng học tập trực tuyến.
Hệ thống cho phép giảng viên tạo khóa học, upload video, người học đăng ký/mua khóa học và theo dõi tiến độ học tập.

Dự án được thực hiện **solo**, tập trung vào thiết kế backend, tối ưu hiệu năng và khả năng mở rộng.

---

## 🚀 Tính năng chính

* Xác thực & phân quyền người dùng (Admin / Instructor / Student)
* Quản lý khóa học, chương, bài giảng
* Upload và quản lý video với **Cloudinary**
* Theo dõi tiến độ học tập của người dùng
* Gửi thông báo và xử lý tác vụ bất đồng bộ với **Kafka**
* Tối ưu hiệu năng bằng **Redis caching**

---

## 🛠 Công nghệ sử dụng

* **Backend:** Java, Spring Boot, Spring Security
* **ORM:** JPA / Hibernate
* **Database:** PostgreSQL
* **Caching:** Redis
* **Message Broker:** Apache Kafka
* **Media Storage:** Cloudinary
* **DevOps & Tools:** Docker, Git, GitHub, Postman
* **Deploy:** Render

---

## 📂 Kiến trúc tổng quan

* Controller: Xử lý request/response REST API
* Service: Xử lý business logic
* Repository: Tương tác với cơ sở dữ liệu
* DTO: Truyền dữ liệu giữa các layer
* Security: JWT Authentication & Authorization

---

## 🔗 Demo & Tài liệu API

* **Swagger UI:** [https://server-course-5wmb.onrender.com/swagger-ui/index.html](https://server-course-5wmb.onrender.com/swagger-ui/index.html)
* **Source code:** [https://github.com/TTIT1/server-course.git](https://github.com/TTIT1/server-course.git)

---

## ⚙️ Cách chạy project (Local)

```bash
# Clone repository
git clone https://github.com/HUBT-Social/hubtsocial_mobile.git

# Vào thư mục project
cd hubtsocial_mobile

# Cấu hình application.yml (PostgreSQL, Redis, Cloudinary)

# Chạy ứng dụng
./mvnw spring-boot:run
```

---

## 👨‍💻 Tác giả

* **Nguyễn Thế Trường**
* Backend Developer (Fresher)
* Email: [thetruongit1236@gmail.com](mailto:thetruongit1236@gmail.com)

---

## 🎯 Mục tiêu học tập

* Nâng cao kỹ năng thiết kế REST API với Spring Boot
* Áp dụng caching và xử lý bất đồng bộ trong hệ thống backend
* Xây dựng backend có khả năng mở rộng và triển khai thực tế
