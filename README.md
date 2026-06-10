# 💰 FinTrack - Smart Finance Tracker

**FinTrack** là một ứng dụng web Quản lý Tài chính Cá nhân được phát triển bằng **Spring Boot** và **Thymeleaf**. Dự án giúp người dùng dễ dàng ghi chép các khoản thu chi hàng ngày, thiết lập và theo dõi các quỹ tiết kiệm, đồng thời cung cấp các biểu đồ thống kê trực quan để tối ưu hóa thói quen tiêu dùng.

---

## 🚀 Các tính năng chính

*   **🔐 Xác thực người dùng:** Đăng nhập an toàn với Spring Security (Mật khẩu được mã hóa chuẩn BCrypt).
*   **📊 Bảng điều khiển (Dashboard):** 
    *   Tổng hợp nhanh Số dư, Tổng thu nhập và Tổng chi tiêu.
    *   Tự động vẽ biểu đồ phân tích dòng tiền theo tháng (Bar Chart) và cơ cấu chi tiêu (Doughnut Chart).
*   **💸 Quản lý Giao dịch:** Thêm mới, chỉnh sửa, phân loại (Thu/Chi) và xóa giao dịch dễ dàng. API xử lý ngầm mượt mà không cần tải lại trang.
*   **🎯 Quỹ tiết kiệm (Saving Goals):** Tạo các mục tiêu tài chính, nạp tiền tích lũy và theo dõi phần trăm (%) tiến độ qua thanh Progress Bar trực quan.

---

## 🛠️ Công nghệ sử dụng

**Backend:**
*   Java (Phiên bản 17 hoặc 21)
*   Spring Boot (Web, Security, Data JPA)
*   Cơ sở dữ liệu: MySQL

**Frontend:**
*   HTML5 / CSS3
*   Thymeleaf (Template Engine)
*   Tailwind CSS (Thiết kế giao diện hiện đại, Responsive)
*   Chart.js (Vẽ biểu đồ thống kê)
*   JavaScript (Fetch API xử lý dữ liệu động)

---

## ⚙️ Hướng dẫn Cài đặt & Chạy dự án

### 1. Yêu cầu hệ thống
*   Đã cài đặt **JDK 17** (hoặc mới hơn).
*   Đã cài đặt **MySQL Server** và công cụ quản lý (MySQL Workbench / XAMPP phpMyAdmin).
*   Đã cài đặt IDE (Eclipse / IntelliJ IDEA).

### 2. Thiết lập Cơ sở dữ liệu
1. Mở MySQL và tạo một cơ sở dữ liệu mới với tên: `smart_finance_db`
```sql
   CREATE DATABASE smart_finance_db;
