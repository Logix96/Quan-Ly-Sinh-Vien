package repository;

import model.TaiKhoan;
import util.DatabaseConnection;
import java.sql.*;

public class TaiKhoanRepository {
    public TaiKhoan kienTraDangNhap(String username, String password) {
        String sql = "SELECT * FROM tai_khoan WHERE ten_dang_nhap = ? AND mat_khau = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new TaiKhoan(
                            rs.getString("ten_dang_nhap"),
                            rs.getString("mat_khau")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}