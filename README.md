# 💰 FinTrack - Hệ Thống Quản Lý Tài Chính Cá Nhân

Đồ án kết thúc học phần môn **Phát triển ứng dụng Web** - Đại học Nha Trang (NTU). 
**FinTrack** là hệ thống web cá nhân giúp số hóa quy trình quản lý chi tiêu, ghi chép các khoản thu chi hàng ngày và theo dõi các quỹ tiết kiệm một cách trực quan, tự động hóa thông qua các biểu đồ thống kê.

---

## 💻 Nền tảng Công nghệ
* **Backend:** Java 17, Spring Boot, Spring Security, Spring Data JPA.
* **Frontend:** HTML5, CSS3, Tailwind CSS, Thymeleaf Template Engine, Chart.js.
* **Database:** MySQL.
* **Công cụ phát triển:** Eclipse / IntelliJ IDEA, Git.

---

## ✨ Các chức năng chính & Hình ảnh thực tế

### 1. Phân hệ Xác thực & Bảo mật

**🔐 Đăng nhập an toàn**
Hệ thống xác thực người dùng chặt chẽ thông qua bộ lọc Spring Security. Thông tin mật khẩu được mã hóa an toàn chuẩn BCrypt trước khi lưu xuống cơ sở dữ liệu.
![Đăng nhập hệ thống](https://github.com/user-attachments/assets/559cc637-b9d5-4e8e-8376-6ebb3d65e7bc)

---

### 2. Phân hệ Thống kê trung tâm

**📊 Dashboard & Biểu đồ trực quan**
Giao diện Bảng điều khiển tổng hợp nhanh Số dư, Tổng thu nhập và Tổng chi tiêu. Hệ thống tự động phân tích dữ liệu và vẽ biểu đồ luồng tiền (Bar Chart) cùng cơ cấu danh mục chi tiêu (Doughnut Chart).
![Dashboard](https://github.com/user-attachments/assets/66ba09ea-bdd8-4efb-a4d8-dc49c6a3847f)

---

### 3. Phân hệ Giao dịch & Tiết kiệm

**💸 Ghi chép & Phân loại giao dịch**
Hộp thoại (Modal) nhập liệu trực quan cho phép người dùng thêm mới, chỉnh sửa, phân loại luồng Thu (+) / Chi (-) dễ dàng. Hệ thống sử dụng API xử lý dữ liệu ngầm giúp cập nhật danh sách ngay lập tức.
![Thêm giao dịch](https://github.com/user-attachments/assets/fa3323fb-e065-4fe7-952a-e1189defcf11)

**🎯 Theo dõi Quỹ tiết kiệm (Saving Goals)**
Cho phép thiết lập các mục tiêu tài chính dài hạn. Khi nạp tiền tích lũy, thuật toán tự động tính toán tỷ lệ và hiển thị phần trăm (%) tiến độ thông qua thanh Progress Bar.
![Quỹ tiết kiệm](https://github.com/user-attachments/assets/607c4a12-0e84-4b61-828b-ab1c5fd74a88)

---
