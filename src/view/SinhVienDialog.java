package view;

import model.SinhVien;

import javax.swing.*;
import java.awt.*;

public class SinhVienDialog extends JDialog {
    private JTextField txtMaSv, txtHoTen, txtNgaySinh, txtGioiTinh, txtChuyenNganh, txtDiemTb;
    private JButton btnSave, btnCancel;
    private SinhVien sinhVienResult = null;

    public SinhVienDialog(Frame parent, String title, SinhVien sv) {
        super(parent, title, true); // Modal dialog
        setSize(400, 350);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(7, 2, 10, 10));

        // Khởi tạo các ô nhập dữ liệu
        txtMaSv = new JTextField();
        txtHoTen = new JTextField();
        txtNgaySinh = new JTextField();
        txtGioiTinh = new JTextField();
        txtChuyenNganh = new JTextField();
        txtDiemTb = new JTextField();

        // Nếu là form Sửa, điền sẵn dữ liệu cũ
        if (sv != null) {
            txtMaSv.setText(sv.getMaSv());
            txtMaSv.setEditable(false); // Không cho sửa khóa chính
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

        // Xử lý sự kiện nút hủy
        btnCancel.addActionListener(e -> dispose());

        // Xử lý sự kiện nút lưu
        btnSave.addActionListener(e -> {
            try {
                String maSv = txtMaSv.getText();
                String hoTen = txtHoTen.getText();
                String ngaySinh = txtNgaySinh.getText();
                String gioiTinh = txtGioiTinh.getText();
                String chuyenNganh = txtChuyenNganh.getText();
                float diemTb = Float.parseFloat(txtDiemTb.getText());

                sinhVienResult = new SinhVien(maSv, hoTen, ngaySinh, gioiTinh, chuyenNganh, diemTb);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Điểm TB phải là số!");
            }
        });
    }

    public SinhVien getSinhVienResult() {
        return sinhVienResult;
    }
}