package model;

public class SinhVien extends Nguoi {
    private String maSv;
    private String chuyenNganh;
    private float diemTb;

    public SinhVien(String maSv, String hoTen, String ngaySinh, String gioiTinh, String chuyenNganh, float diemTb) {
        super(hoTen, ngaySinh, gioiTinh);
        this.maSv = maSv;
        this.chuyenNganh = chuyenNganh;
        this.diemTb = diemTb;
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("Mã SV: " + maSv + " | Tên: " + hoTen + " | Chuyên ngành: " + chuyenNganh);
    }

    public String getMaSv() {
        return maSv;
    }

    public void setMaSv(String maSv) {
        this.maSv = maSv;
    }

    public String getChuyenNganh() {
        return chuyenNganh;
    }

    public void setChuyenNganh(String chuyenNganh) {
        this.chuyenNganh = chuyenNganh;
    }

    public float getDiemTb() {
        return diemTb;
    }

    public void setDiemTb(float diemTb) {
        this.diemTb = diemTb;
    }
}