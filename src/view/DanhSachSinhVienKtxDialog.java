package view;

import util.DatabaseConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DanhSachSinhVienKtxDialog extends JDialog {
    public DanhSachSinhVienKtxDialog(Frame parent, String maPhong) {
        super(parent, "Sinh viên đang ở phòng: " + maPhong, true);
        setSize(500, 300);
        setLocationRelativeTo(parent);

        String[] columns = {"Mã SV", "Họ Tên", "Ngày Sinh", "Chuyên Ngành"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        JTable table = new JTable(model);

        // Lấy dữ liệu từ database
        String sql = "SELECT ma_sv, ho_ten, DATE_FORMAT(ngay_sinh, '%d-%m-%Y') AS ngay_sinh_format, chuyen_nganh FROM sinh_vien WHERE ma_phong = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, maPhong);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    model.addRow(new Object[]{
                            rs.getString("ma_sv"),
                            rs.getString("ho_ten"),
                            rs.getString("ngay_sinh_format"),
                            rs.getString("chuyen_nganh")
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu KTX!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}