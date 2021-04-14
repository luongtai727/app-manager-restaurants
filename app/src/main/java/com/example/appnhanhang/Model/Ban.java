package com.example.appnhanhang.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ban {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("TenBan")
    @Expose
    private String tenBan;
    @SerializedName("TenLoaiBan")
    @Expose
    private String tenLoaiBan;
    @SerializedName("TrangThai")
    @Expose
    private String trangThai;
    @SerializedName("IdHoaDon")
    @Expose
    private String idHoaDon;
    @SerializedName("TrangThaiHD")
    @Expose
    private String trangThaiHD;

    @SerializedName("NgayLap")
    @Expose
    private String NgayLap;

    @SerializedName("IdNhanVien")
    @Expose
    private String IdNhanVien;

    public Ban(String id, String tenBan, String tenLoaiBan, String trangThai, String idHoaDon, String trangThaiHD) {
        this.id = id;
        this.tenBan = tenBan;
        this.tenLoaiBan = tenLoaiBan;
        this.trangThai = trangThai;
        this.idHoaDon = idHoaDon;
        this.trangThaiHD = trangThaiHD;
    }

    public Ban(String id, String tenBan, String tenLoaiBan, String trangThai, String idHoaDon, String trangThaiHD, String ngayLap) {
        this.id = id;
        this.tenBan = tenBan;
        this.tenLoaiBan = tenLoaiBan;
        this.trangThai = trangThai;
        this.idHoaDon = idHoaDon;
        this.trangThaiHD = trangThaiHD;
        NgayLap = ngayLap;
    }

    public String getIdNhanVien() {
        return IdNhanVien;
    }

    public void setIdNhanVien(String idNhanVien) {
        IdNhanVien = idNhanVien;
    }

    public String getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(String ngayLap) {
        NgayLap = ngayLap;
    }

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getTrangThaiHD() {
        return trangThaiHD;
    }

    public void setTrangThaiHD(String trangThaiHD) {
        this.trangThaiHD = trangThaiHD;
    }

    public Ban(String id, String tenBan, String tenLoaiBan, String trangThai) {
        this.id = id;
        this.tenBan = tenBan;
        this.tenLoaiBan = tenLoaiBan;
        this.trangThai = trangThai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public String getTenLoaiBan() {
        return tenLoaiBan;
    }

    public void setTenLoaiBan(String tenLoaiBan) {
        this.tenLoaiBan = tenLoaiBan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}