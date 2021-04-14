package com.example.appnhanhang.Model;

public class order {

    String id;
    String TenMon;
    String SoLuong;
    String Ghichu;
    String TenNhanVien;
    String TenBan;

    public order(String tenMon, String soLuong, String ghichu, String tenNhanVien, String tenBan) {
        TenMon = tenMon;
        SoLuong = soLuong;
        Ghichu = ghichu;
        TenNhanVien = tenNhanVien;
        TenBan = tenBan;
    }

    public order(String id, String tenMon, String soLuong, String ghichu, String tenNhanVien, String tenBan) {
        this.id = id;
        TenMon = tenMon;
        SoLuong = soLuong;
        Ghichu = ghichu;
        TenNhanVien = tenNhanVien;
        TenBan = tenBan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String tenMon) {
        TenMon = tenMon;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String soLuong) {
        SoLuong = soLuong;
    }

    public String getGhichu() {
        return Ghichu;
    }

    public void setGhichu(String ghichu) {
        Ghichu = ghichu;
    }

    public String getTenNhanVien() {
        return TenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        TenNhanVien = tenNhanVien;
    }

    public String getTenBan() {
        return TenBan;
    }

    public void setTenBan(String tenBan) {
        TenBan = tenBan;
    }
}
