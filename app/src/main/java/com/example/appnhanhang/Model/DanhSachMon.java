package com.example.appnhanhang.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DanhSachMon {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("danhmuc")
    @Expose
    private String danhmuc;
    @SerializedName("trangthai")
    @Expose
    private String trangthai;

    public DanhSachMon(String id, String danhmuc, String trangthai) {
        this.id = id;
        this.danhmuc = danhmuc;
        this.trangthai = trangthai;
    }

    public DanhSachMon()
    {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDanhmuc() {
        return danhmuc;
    }

    public void setDanhmuc(String danhmuc) {
        this.danhmuc = danhmuc;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

}