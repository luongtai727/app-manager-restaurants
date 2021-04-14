package com.example.appnhanhang.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class DaOder  implements Cloneable
{

    @SerializedName("hoa_dons_id")
    @Expose
    private String hoaDonsId;
    @SerializedName("SoLuong")
    @Expose
    private String soLuong;
    @SerializedName("ThanhTien")
    @Expose
    private String thanhTien;
    @SerializedName("GhiChu")
    @Expose
    private String ghiChu;
    @SerializedName("GiamGia")
    @Expose
    private String giamGia;
    @SerializedName("TenMon")
    @Expose
    private String tenMon;
    @SerializedName("id")
    @Expose
    private String id;

    public DaOder()
    {

    }

    public DaOder(String hoaDonsId, String soLuong, String thanhTien, String ghiChu, String giamGia, String tenMon, String id) {
        this.hoaDonsId = hoaDonsId;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
        this.ghiChu = ghiChu;
        this.giamGia = giamGia;
        this.tenMon = tenMon;
        this.id = id;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getHoaDonsId() {
        return hoaDonsId;
    }

    public void setHoaDonsId(String hoaDonsId) {
        this.hoaDonsId = hoaDonsId;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(String thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(String giamGia) {
        this.giamGia = giamGia;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
