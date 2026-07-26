package model;

public class HoaDonHocPhi {
    private int maHd;
    private String maSv;
    private String kyHoc;
    private float soTien;
    private String trangThai;

    public HoaDonHocPhi(int maHd, String maSv, String kyHoc, float soTien, String trangThai) {
        this.maHd = maHd;
        this.maSv = maSv;
        this.kyHoc = kyHoc;
        this.soTien = soTien;
        this.trangThai = trangThai;
    }

    public float getSoTien() {
        return soTien;
    }

    public int getMaHd() {
        return maHd;
    }

    public String getMaSv() {
        return maSv;
    }

    public String getKyHoc() {
        return kyHoc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setKyHoc(String kyHoc) {
        this.kyHoc = kyHoc;
    }

    public void setMaHd(int maHd) {
        this.maHd = maHd;
    }

    public void setMaSv(String maSv) {
        this.maSv = maSv;
    }

    public void setSoTien(float soTien) {
        this.soTien = soTien;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}