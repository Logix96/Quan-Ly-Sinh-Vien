package model;

public class PhongKtx {
    private String maPhong;
    private String loaiPhong;
    private int sucChua;
    private int soNguoiDangO;

    public PhongKtx(String maPhong, String loaiPhong, int sucChua, int soNguoiDangO) {
        this.maPhong = maPhong;
        this.loaiPhong = loaiPhong;
        this.sucChua = sucChua;
        this.soNguoiDangO = soNguoiDangO;
    }

    public int getSoNguoiDangO() {
        return soNguoiDangO;
    }

    public int getSucChua() {
        return sucChua;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public String getLoaiPhong() {
        return loaiPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public void setLoaiPhong(String loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    public void setSoNguoiDangO(int soNguoiDangO) {
        this.soNguoiDangO = soNguoiDangO;
    }

    public void setSucChua(int sucChua) {
        this.sucChua = sucChua;
    }
}