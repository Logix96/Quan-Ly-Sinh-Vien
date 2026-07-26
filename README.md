# HỆ THỐNG QUẢN LÝ SINH VIÊN
Phần mềm quản lý sinh viên xây dựng bằng Java (Swing GUI) kết hợp với cơ sở dữ liệu MySQL

Hệ thống được chia thành 3 tab quản lý giao diện dạng thẻ (JTabbedPane):

1. Quản lý Sinh viên
- Thêm mới, sửa thông tin, xóa sinh viên.
- Tìm kiếm sinh viên theo tên hoặc mã sinh viên.
- Quản lý điểm trung bình (GPA) trên thang điểm 4.0.

2. Quản lý Học phí
-Tạo mới hóa đơn học phí theo từng kỳ học cho sinh viên.
- Tra cứu danh sách hóa đơn học phí theo Mã Sinh Viên.
- Xác nhận chuyển trạng thái hóa đơn từ "Chưa nộp" sang "Đã nộp".

3. Quản lý Ký túc xá
- Tạo phòng KTX mới với loại phòng và sức chứa tùy chỉnh.
- Xếp sinh viên vào phòng KTX 
- Nhấp đúp chuột vào từng dòng phòng KTX để mở cửa sổ xem danh sách sinh viên đang ở trong phòng đó.



Hướng dẫn chạy chương trình:

Bước 1: Import Cơ sở dữ liệu (Database)
- Mở MySQL Workbench (hoặc công cụ quản lý MySQL tương tự).
- Chạy toàn bộ script SQL trong file createdb.sql để khởi tạo cơ sở dữ liệu quan_ly_sinh_vien

Bước 2: Cấu hình kết nối MySQL trong Project
- Mở IDE.
- Tìm đến file cấu hình kết nối tại src/util/DatabaseConnection.java và thay đổi thông tin USER và PASSWORD khớp với tài khoản MySQL
  
Bước 3: Thêm thư viện MySQL JDBC Driver
Đảm bảo dự án đã import file mysql-connector-j.jar vào Project Structure / Libraries / Build Path.

Bước 4: Chạy ứng dụng
- Điều hướng đến file src/Main.java.
- Khởi chạy file Main.java (Run Main).
(Màn hình đăng nhập sẽ hiện ra, nhập tài khoản admin có trong bảng tai_khoan để đăng nhập vào hệ thống.)
