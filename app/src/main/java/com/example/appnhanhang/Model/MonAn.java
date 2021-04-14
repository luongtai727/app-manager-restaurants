package com.example.appnhanhang.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MonAn {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("tenmon")
    @Expose
    private String tenmon;
    @SerializedName("anh")
    @Expose
    private String anh;
    @SerializedName("dongia")
    @Expose
    private String dongia;
    @SerializedName("chitiet")
    @Expose
    private String chitiet;
    @SerializedName("tendanhmuc")
    @Expose
    private String tendanhmuc;
    @SerializedName("tendonvitinh")
    @Expose
    private String tendonvitinh;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getDongia() {
        return dongia;
    }

    public void setDongia(String dongia) {
        this.dongia = dongia;
    }

    public String getChitiet() {
        return chitiet;
    }

    public void setChitiet(String chitiet) {
        this.chitiet = chitiet;
    }

    public String getTendanhmuc() {
        return tendanhmuc;
    }

    public void setTendanhmuc(String tendanhmuc) {
        this.tendanhmuc = tendanhmuc;
    }

    public String getTendonvitinh() {
        return tendonvitinh;
    }

    public void setTendonvitinh(String tendonvitinh) {
        this.tendonvitinh = tendonvitinh;
    }

}