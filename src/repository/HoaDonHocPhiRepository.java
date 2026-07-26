package repository;

import model.HoaDonHocPhi;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HoaDonHocPhiRepository {
    public List<HoaDonHocPhi> getAll() {
        List<HoaDonHocPhi> list = new ArrayList<>();
        String sql = "SELECT * FROM hoa_don_hoc_phi";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new HoaDonHocPhi(
                        rs.getInt("ma_hd"),
                        rs.getString("ma_sv"),
                        rs.getString("ky_hoc"),
                        rs.getFloat("so_tien"),
                        rs.getString("trang_thai")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean xacNhanThuTien(int maHd) {
        String sql = "UPDATE hoa_don_hoc_phi SET trang_thai = 'Đã nộp' WHERE ma_hd = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, maHd);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<HoaDonHocPhi> searchByMaSv(String maSv) {
        List<HoaDonHocPhi> list = new ArrayList<>();
        String sql = "SELECT * FROM hoa_don_hoc_phi WHERE ma_sv LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + maSv + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new HoaDonHocPhi(
                            rs.getInt("ma_hd"), rs.getString("ma_sv"),
                            rs.getString("ky_hoc"), rs.getFloat("so_tien"),
                            rs.getString("trang_thai")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addHoaDon(HoaDonHocPhi hd) {
        String sql = "INSERT INTO hoa_don_hoc_phi (ma_sv, ky_hoc, so_tien, trang_thai) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, hd.getMaSv());
            pstmt.setString(2, hd.getKyHoc());
            pstmt.setFloat(3, hd.getSoTien());
            pstmt.setString(4, hd.getTrangThai());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}