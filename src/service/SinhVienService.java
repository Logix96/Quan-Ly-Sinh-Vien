package service;

import exception.InvalidDataException;
import model.SinhVien;
import repository.SinhVienRepository;
import java.util.List;

public class SinhVienService {
    private SinhVienRepository repository;

    public SinhVienService(SinhVienRepository repository) {
        this.repository = repository;
    }

    public List<SinhVien> getAllSinhVien() {
        return repository.getAllSinhVien();
    }

    // Kiểm tra dữ liệu trước
    public boolean addSinhVien(SinhVien sv) throws InvalidDataException {
        if (sv.getMaSv() == null || sv.getMaSv().trim().isEmpty()) {
            throw new InvalidDataException("Mã sinh viên không được để trống!");
        }
        if (sv.getHoTen() == null || sv.getHoTen().trim().isEmpty()) {
            throw new InvalidDataException("Họ tên không được để trống!");
        }
        if (sv.getDiemTb() < 0.0 || sv.getDiemTb() > 4.0) {
            throw new InvalidDataException("Điểm trung bình phải nằm trong khoảng 0.0 - 4.0!");
        }

        return repository.addSinhVien(sv);
    }

    public boolean updateSinhVien(SinhVien sv) throws InvalidDataException {
        if (sv.getDiemTb() < 0.0 || sv.getDiemTb() > 4.0) {
            throw new InvalidDataException("Điểm trung bình phải nằm trong khoảng 0.0 - 4.0!");
        }
        return repository.updateSinhVien(sv);
    }

    public boolean deleteSinhVien(String maSv) {
        return repository.deleteSinhVien(maSv);
    }

    public List<SinhVien> searchSinhVien(String keyword) {
        return repository.searchSinhVien(keyword);
    }
}