package repository;

import model.SinhVien;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SinhVienRepository {

    // Lấy danh sách và lưu vào Collection (ArrayList) thay vì mảng
    public List<SinhVien> getAllSinhVien() {
        List<SinhVien> list = new ArrayList<>();
        // Dùng DATE_FORMAT để chuyển từ YYYY-MM-DD trong DB sang DD-MM-YYYY cho Java
        String sql = "SELECT ma_sv, ho_ten, DATE_FORMAT(ngay_sinh, '%d-%m-%Y') AS ngay_sinh_format, gioi_tinh, chuyen_nganh, diem_tb FROM sinh_vien";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                SinhVien sv = new SinhVien(
                        rs.getString("ma_sv"),
                        rs.getString("ho_ten"),
                        rs.getString("ngay_sinh_format"), // Lấy tên cột vừa format
                        rs.getString("gioi_tinh"),
                        rs.getString("chuyen_nganh"),
                        rs.getFloat("diem_tb")
                );
                list.add(sv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm sinh viên mới
    public boolean addSinhVien(SinhVien sv) {
        // Dùng STR_TO_DATE để chuyển chuỗi DD-MM-YYYY từ Java thành ngày chuẩn của DB
        String sql = "INSERT INTO sinh_vien (ma_sv, ho_ten, ngay_sinh, gioi_tinh, chuyen_nganh, diem_tb) VALUES (?, ?, STR_TO_DATE(?, '%d-%m-%Y'), ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, sv.getMaSv());
            pstmt.setString(2, sv.getHoTen());
            pstmt.setString(3, sv.getNgaySinh());
            pstmt.setString(4, sv.getGioiTinh());
            pstmt.setString(5, sv.getChuyenNganh());
            pstmt.setFloat(6, sv.getDiemTb());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật thông tin sinh viên
    public boolean updateSinhVien(SinhVien sv) {
        String sql = "UPDATE sinh_vien SET ho_ten=?, ngay_sinh=STR_TO_DATE(?, '%d-%m-%Y'), gioi_tinh=?, chuyen_nganh=?, diem_tb=? WHERE ma_sv=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, sv.getHoTen());
            pstmt.setString(2, sv.getNgaySinh());
            pstmt.setString(3, sv.getGioiTinh());
            pstmt.setString(4, sv.getChuyenNganh());
            pstmt.setFloat(5, sv.getDiemTb());
            pstmt.setString(6, sv.getMaSv());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa sinh viên
    public boolean deleteSinhVien(String maSv) {
        String sql = "DELETE FROM sinh_vien WHERE ma_sv=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, maSv);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Tìm kiếm sinh viên theo tên hoặc mã
    public List<SinhVien> searchSinhVien(String keyword) {
        List<SinhVien> list = new ArrayList<>();
        String sql = "SELECT ma_sv, ho_ten, DATE_FORMAT(ngay_sinh, '%d-%m-%Y') AS ngay_sinh_format, gioi_tinh, chuyen_nganh, diem_tb FROM sinh_vien WHERE ma_sv LIKE ? OR ho_ten LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new SinhVien(
                            rs.getString("ma_sv"),
                            rs.getString("ho_ten"),
                            rs.getString("ngay_sinh_format"), // Lấy tên cột vừa format
                            rs.getString("gioi_tinh"),
                            rs.getString("chuyen_nganh"),
                            rs.getFloat("diem_tb")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}