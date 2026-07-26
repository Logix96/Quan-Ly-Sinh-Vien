package repository;

import model.PhongKtx;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhongKtxRepository {
    public List<PhongKtx> getAll() {
        List<PhongKtx> list = new ArrayList<>();
        String sql = "SELECT * FROM phong_ktx";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new PhongKtx(
                        rs.getString("ma_phong"),
                        rs.getString("loai_phong"),
                        rs.getInt("suc_chua"),
                        rs.getInt("so_nguoi_dang_o")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean xepPhong(String maSv, String maPhong) {
        String checkSql = "SELECT suc_chua, so_nguoi_dang_o FROM phong_ktx WHERE ma_phong = ?";
        String updateSvSql = "UPDATE sinh_vien SET ma_phong = ? WHERE ma_sv = ?";
        String updateKtxSql = "UPDATE phong_ktx SET so_nguoi_dang_o = so_nguoi_dang_o + 1 WHERE ma_phong = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Bắt đầu transaction

            // Check sức chứa
            try (PreparedStatement pstmtCheck = conn.prepareStatement(checkSql)) {
                pstmtCheck.setString(1, maPhong);
                ResultSet rs = pstmtCheck.executeQuery();
                if (rs.next()) {
                    if (rs.getInt("so_nguoi_dang_o") >= rs.getInt("suc_chua")) {
                        return false;
                    }
                } else {
                    return false;
                }
            }

            // Cập nhật sinh viên
            try (PreparedStatement pstmtSv = conn.prepareStatement(updateSvSql)) {
                pstmtSv.setString(1, maPhong);
                pstmtSv.setString(2, maSv);
                if (pstmtSv.executeUpdate() == 0) {
                    conn.rollback();
                    return false;
                }
            }

            // Tăng số người ở
            try (PreparedStatement pstmtKtx = conn.prepareStatement(updateKtxSql)) {
                pstmtKtx.setString(1, maPhong);
                pstmtKtx.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addPhong(PhongKtx phong) {
        String sql = "INSERT INTO phong_ktx (ma_phong, loai_phong, suc_chua, so_nguoi_dang_o) VALUES (?, ?, ?, 0)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, phong.getMaPhong());
            pstmt.setString(2, phong.getLoaiPhong());
            pstmt.setInt(3, phong.getSucChua());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}