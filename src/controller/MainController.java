package controller;

import exception.InvalidDataException;
import model.SinhVien;
import service.SinhVienService;
import view.MainView;
import view.SinhVienDialog;

import javax.swing.*;
import java.util.List;

public class MainController {
    private MainView view;
    private SinhVienService service;

    // Sửa tham số truyền vào thành SinhVienService
    public MainController(MainView view, SinhVienService service) {
        this.view = view;
        this.service = service;

        initController();
        loadDataToTable(service.getAllSinhVien()); // Gọi qua service
    }

    private void initController() {
        view.getBtnSearch().addActionListener(e -> search());
        view.getBtnDelete().addActionListener(e -> delete());

        view.getBtnAdd().addActionListener(e -> {
            SinhVienDialog dialog = new SinhVienDialog(view, "Thêm Sinh Viên", null);
            dialog.setVisible(true);
            SinhVien svNew = dialog.getSinhVienResult();

            if (svNew != null) {
                try {
                    if (service.addSinhVien(svNew)) {
                        loadDataToTable(service.getAllSinhVien());
                        JOptionPane.showMessageDialog(view, "Thêm thành công!");
                    } else {
                        JOptionPane.showMessageDialog(view, "Thêm thất bại (Trùng mã SV)!");
                    }
                } catch (InvalidDataException ex) {
                    JOptionPane.showMessageDialog(view, ex.getMessage(), "Lỗi dữ liệu", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        view.getBtnEdit().addActionListener(e -> edit());
    }

    private void loadDataToTable(List<SinhVien> danhSach) {
        view.getTableModel().setRowCount(0);
        for (SinhVien sv : danhSach) {
            view.getTableModel().addRow(new Object[]{
                    sv.getMaSv(), sv.getHoTen(), sv.getNgaySinh(),
                    sv.getGioiTinh(), sv.getChuyenNganh(), sv.getDiemTb()
            });
        }
    }

    private void search() {
        String keyword = view.getTxtSearch().getText();
        List<SinhVien> ketQua = service.searchSinhVien(keyword);
        loadDataToTable(ketQua);
    }

    private void delete() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow >= 0) {
            String maSv = (String) view.getTableModel().getValueAt(selectedRow, 0);

            int confirm = JOptionPane.showConfirmDialog(view, "Xóa sinh viên " + maSv + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (service.deleteSinhVien(maSv)) {
                    loadDataToTable(service.getAllSinhVien());
                    JOptionPane.showMessageDialog(view, "Xóa thành công!");
                } else {
                    JOptionPane.showMessageDialog(view, "Lỗi khi xóa!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn một dòng để xóa!");
        }
    }

    private void edit() {
        //Kiểm tra xem người dùng đã chọn dòng nào trên bảng chưa
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow >= 0) {
            // Lấy dữ liệu của sinh viên từ dòng được chọn
            String maSv = (String) view.getTableModel().getValueAt(selectedRow, 0);
            String hoTen = (String) view.getTableModel().getValueAt(selectedRow, 1);
            String ngaySinh = (String) view.getTableModel().getValueAt(selectedRow, 2);
            String gioiTinh = (String) view.getTableModel().getValueAt(selectedRow, 3);
            String chuyenNganh = (String) view.getTableModel().getValueAt(selectedRow, 4);
            float diemTb = (Float) view.getTableModel().getValueAt(selectedRow, 5);

            // Đóng gói thành đối tượng SinhVien
            SinhVien svCurrent = new SinhVien(maSv, hoTen, ngaySinh, gioiTinh, chuyenNganh, diemTb);

            // Mở JDialog và truyền đối tượng này vào
            SinhVienDialog dialog = new SinhVienDialog(view, "Sửa Thông Tin Sinh Viên", svCurrent);
            dialog.setVisible(true);

            // Nhận lại kết quả sau
            SinhVien svEdited = dialog.getSinhVienResult();

            if (svEdited != null) {
                try {
                    if (service.updateSinhVien(svEdited)) {
                        loadDataToTable(service.getAllSinhVien()); // Tải lại bảng để thấy dữ liệu mới
                        JOptionPane.showMessageDialog(view, "Cập nhật thành công!");
                    } else {
                        JOptionPane.showMessageDialog(view, "Cập nhật thất bại!");
                    }
                } catch (InvalidDataException ex) {
                    JOptionPane.showMessageDialog(view, ex.getMessage(), "Lỗi dữ liệu", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn một dòng để sửa!");
        }
    }
}