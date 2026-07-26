package view;

import model.SinhVien;
import javax.swing.*;
import java.awt.*;

public class SinhVienDialog extends JDialog {
    private JTextField txtMaSv, txtHoTen, txtNgaySinh, txtGioiTinh, txtChuyenNganh, txtDiemTb;
    private JButton btnSave, btnCancel;
    private SinhVien sinhVienResult = null;

    public SinhVienDialog(Frame parent, String title, SinhVien sv) {
        super(parent, title, true);
        setSize(400, 350);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(7, 2, 10, 10));

        txtMaSv = new JTextField();
        txtHoTen = new JTextField();
        txtNgaySinh = new JTextField();
        txtGioiTinh = new JTextField();
        txtChuyenNganh = new JTextField();
        txtDiemTb = new JTextField();

        if (sv == null) {
            txtDiemTb.setText("0.0");
        } else {
            txtMaSv.setText(sv.getMaSv());
            txtMaSv.setEditable(false);
            txtHoTen.setText(sv.getHoTen());
            txtNgaySinh.setText(sv.getNgaySinh());
            txtGioiTinh.setText(sv.getGioiTinh());
            txtChuyenNganh.setText(sv.getChuyenNganh());
            txtDiemTb.setText(String.valueOf(sv.getDiemTb()));
        }

        add(new JLabel("Mã SV:"));
        add(txtMaSv);
        add(new JLabel("Họ Tên:"));
        add(txtHoTen);
        add(new JLabel("Ngày Sinh (DD-MM-YYYY):"));
        add(txtNgaySinh);
        add(new JLabel("Giới Tính:"));
        add(txtGioiTinh);
        add(new JLabel("Chuyên Ngành:"));
        add(txtChuyenNganh);
        add(new JLabel("Điểm TB:"));
        add(txtDiemTb);

        btnSave = new JButton("Lưu");
        btnCancel = new JButton("Hủy");
        add(btnSave);
        add(btnCancel);

        btnCancel.addActionListener(e -> dispose());

        btnSave.addActionListener(e -> {
            try {
                String maSv = txtMaSv.getText().trim();
                String hoTen = txtHoTen.getText().trim();
                String ngaySinh = txtNgaySinh.getText().trim();
                String gioiTinh = txtGioiTinh.getText().trim();
                String chuyenNganh = txtChuyenNganh.getText().trim();

                if (maSv.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Mã sinh viên không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (hoTen.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Họ tên không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!ngaySinh.matches("\\d{2}-\\d{2}-\\d{4}")) {
                    JOptionPane.showMessageDialog(this, "Ngày sinh phải nhập đúng định dạng DD-MM-YYYY!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                float diemTb = Float.parseFloat(txtDiemTb.getText().trim());
                if (diemTb < 0.0 || diemTb > 4.0) {
                    JOptionPane.showMessageDialog(this, "Điểm trung bình phải nằm trong khoảng 0.0 - 4.0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                sinhVienResult = new SinhVien(maSv, hoTen, ngaySinh, gioiTinh, chuyenNganh, diemTb);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Điểm TB phải là số thực hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public SinhVien getSinhVienResult() {
        return sinhVienResult;
    }
}