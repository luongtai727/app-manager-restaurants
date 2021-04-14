package com.example.appnhanhang.Model;

public class GioHang  {

    int idMonAn;
    int MaHoaDon;
    String TenMon;
    double Gia;
    int SoLuong;
    String Ghichu;
    int GiamGia;

    public GioHang()
    {

    }

    public int getIdMonAn() {
        return idMonAn;
    }

    public void setIdMonAn(int idMonAn) {
        this.idMonAn = idMonAn;
    }

    public int getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        MaHoaDon = maHoaDon;
    }

    public GioHang(String tenMon, double gia, int soLuong, String ghichu, int GiamGia) {
        TenMon = tenMon;
        Gia = gia;
        SoLuong = soLuong;
        Ghichu = ghichu;
        this.GiamGia = GiamGia;
    }

    public int getGiamGia() {
        return GiamGia;
    }

    public void setGiamGia(int giamGia) {
        GiamGia = giamGia;
    }

    public String getGhichu() {
        return Ghichu;
    }

    public void setGhichu(String ghichu) {
        Ghichu = ghichu;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String tenMon) {
        TenMon = tenMon;
    }

    public double getGia() {
        return Gia;
    }

    public void setGia(double gia) {
        Gia = gia;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }
}
