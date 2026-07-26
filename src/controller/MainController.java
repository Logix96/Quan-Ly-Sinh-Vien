package controller;

import exception.InvalidDataException;
import model.SinhVien;
import model.PhongKtx;
import model.HoaDonHocPhi;
import repository.PhongKtxRepository;
import repository.HoaDonHocPhiRepository;
import service.SinhVienService;
import view.MainView;
import view.SinhVienDialog;

import javax.swing.*;
import java.util.List;

public class MainController {
    private MainView view;
    private SinhVienService service;

    public MainController(MainView view, SinhVienService service) {
        this.view = view;
        this.service = service;

        initController();

        // Load dữ liệu cho cả 3 tab
        loadDataToTable(service.getAllSinhVien());
        loadDataHocPhi();
        loadDataKtx();
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
        view.getBtnPayHP().addActionListener(e -> thanhToanHocPhi());
        view.getBtnAssignRoom().addActionListener(e -> xepPhong());
        view.getBtnSearchHP().addActionListener(e -> timHoaDon());
        view.getBtnAddHP().addActionListener(e -> taoHoaDonMoi());
        view.getBtnAddRoom().addActionListener(e -> themPhongMoi());
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
        //Kiểm tra xem user đã chọn dòng nào trên bảng chưa
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow >= 0) {
            // Lấy dữ liệu của sinh viên từ dòng được chọn
            String maSv = (String) view.getTableModel().getValueAt(selectedRow, 0);
            String hoTen = (String) view.getTableModel().getValueAt(selectedRow, 1);
            String ngaySinh = (String) view.getTableModel().getValueAt(selectedRow, 2);
            String gioiTinh = (String) view.getTableModel().getValueAt(selectedRow, 3);
            String chuyenNganh = (String) view.getTableModel().getValueAt(selectedRow, 4);
            float diemTb = (Float) view.getTableModel().getValueAt(selectedRow, 5);

            // Đóng gói
            SinhVien svCurrent = new SinhVien(maSv, hoTen, ngaySinh, gioiTinh, chuyenNganh, diemTb);

            // Mở JDialog và truyền
            SinhVienDialog dialog = new SinhVienDialog(view, "Sửa Thông Tin Sinh Viên", svCurrent);
            dialog.setVisible(true);

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

    private void loadDataHocPhi() {
        view.getModelHocPhi().setRowCount(0);
        HoaDonHocPhiRepository repo = new HoaDonHocPhiRepository();
        List<HoaDonHocPhi> list = repo.getAll();
        for (HoaDonHocPhi hd : list) {
            String soTienFormat = String.format("%,.0f", hd.getSoTien());

            view.getModelHocPhi().addRow(new Object[]{
                    hd.getMaHd(), hd.getMaSv(), hd.getKyHoc(), soTienFormat, hd.getTrangThai()
            });
        }
    }

    private void loadDataKtx() {
        view.getModelKtx().setRowCount(0);
        PhongKtxRepository repo = new PhongKtxRepository();
        List<PhongKtx> list = repo.getAll();
        for (PhongKtx p : list) {
            view.getModelKtx().addRow(new Object[]{
                    p.getMaPhong(), p.getLoaiPhong(), p.getSucChua(), p.getSoNguoiDangO()
            });
        }
    }

    private void thanhToanHocPhi() {
        int selectedRow = view.getTblHocPhi().getSelectedRow();
        if (selectedRow >= 0) {
            int maHd = (Integer) view.getModelHocPhi().getValueAt(selectedRow, 0);
            String trangThai = (String) view.getModelHocPhi().getValueAt(selectedRow, 4);

            if ("Đã nộp".equals(trangThai)) {
                JOptionPane.showMessageDialog(view, "Hóa đơn này đã thanh toán rồi!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(view, "Xác nhận thu tiền hóa đơn: " + maHd + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                HoaDonHocPhiRepository repo = new HoaDonHocPhiRepository();
                if (repo.xacNhanThuTien(maHd)) {
                    loadDataHocPhi();
                    JOptionPane.showMessageDialog(view, "Thu tiền thành công!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn một hóa đơn chưa thanh toán trên bảng!");
        }
    }

    private void xepPhong() {
        String maSv = JOptionPane.showInputDialog(view, "Nhập Mã Sinh Viên cần xếp phòng:");
        if (maSv == null || maSv.trim().isEmpty()) return;

        String maPhong = JOptionPane.showInputDialog(view, "Nhập Mã Phòng KTX:");
        if (maPhong == null || maPhong.trim().isEmpty()) return;

        PhongKtxRepository repo = new PhongKtxRepository();
        if (repo.xepPhong(maSv.trim(), maPhong.trim())) {
            loadDataKtx();
            JOptionPane.showMessageDialog(view, "Xếp phòng thành công!");
        } else {
            JOptionPane.showMessageDialog(view, "Xếp phòng thất bại! (Kiểm tra lại mã SV, mã Phòng hoặc phòng đã đầy)", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void timHoaDon() {
        String maSv = view.getTxtSearchHP().getText().trim();
        HoaDonHocPhiRepository repo = new HoaDonHocPhiRepository();
        List<HoaDonHocPhi> list = repo.searchByMaSv(maSv);

        view.getModelHocPhi().setRowCount(0);
        for (HoaDonHocPhi hd : list) {
            String soTienFormat = String.format("%,.0f", hd.getSoTien());

            view.getModelHocPhi().addRow(new Object[]{
                    hd.getMaHd(), hd.getMaSv(), hd.getKyHoc(), soTienFormat, hd.getTrangThai()
            });
        }
    }

    private void taoHoaDonMoi() {
        String maSv = JOptionPane.showInputDialog(view, "Nhập Mã Sinh Viên:");
        if (maSv == null || maSv.trim().isEmpty()) return;

        String kyHoc = JOptionPane.showInputDialog(view, "Nhập Kỳ Học (VD: Kỳ 2 - 2026):");
        if (kyHoc == null || kyHoc.trim().isEmpty()) return;

        String soTienStr = JOptionPane.showInputDialog(view, "Nhập Số Tiền:");
        if (soTienStr == null || soTienStr.trim().isEmpty()) return;

        try {
            float soTien = Float.parseFloat(soTienStr.trim());
            HoaDonHocPhi hd = new HoaDonHocPhi(0, maSv.trim(), kyHoc.trim(), soTien, "Chưa nộp");
            HoaDonHocPhiRepository repo = new HoaDonHocPhiRepository();

            if (repo.addHoaDon(hd)) {
                loadDataHocPhi();
                JOptionPane.showMessageDialog(view, "Tạo hóa đơn thành công!");
            } else {
                JOptionPane.showMessageDialog(view, "Lỗi! Vui lòng kiểm tra lại mã sinh viên đã tồn tại chưa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Số tiền phải là số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void themPhongMoi() {
        String maPhong = JOptionPane.showInputDialog(view, "Nhập Mã Phòng (VD: P301):");
        if (maPhong == null || maPhong.trim().isEmpty()) return;

        String loaiPhong = JOptionPane.showInputDialog(view, "Nhập Loại Phòng (VD: Phòng thường Nữ):");
        if (loaiPhong == null || loaiPhong.trim().isEmpty()) return;

        String sucChuaStr = JOptionPane.showInputDialog(view, "Nhập Sức Chứa (Số lượng tối đa):");
        if (sucChuaStr == null || sucChuaStr.trim().isEmpty()) return;

        try {
            int sucChua = Integer.parseInt(sucChuaStr.trim());
            PhongKtx phong = new PhongKtx(maPhong.trim(), loaiPhong.trim(), sucChua, 0);
            PhongKtxRepository repo = new PhongKtxRepository();

            if (repo.addPhong(phong)) {
                loadDataKtx();
                JOptionPane.showMessageDialog(view, "Thêm phòng thành công!");
            } else {
                JOptionPane.showMessageDialog(view, "Thêm phòng thất bại! (Có thể mã phòng đã tồn tại)", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Sức chứa phải là số nguyên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}