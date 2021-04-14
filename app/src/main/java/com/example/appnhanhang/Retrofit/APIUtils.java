package com.example.appnhanhang.Retrofit;

public class APIUtils {

    public  static final  String base_url = "http://192.168.0.100//db_NhaHang/";

    public  static  DataClien getData()
    {
        return  RetrofitClien.getClien(base_url).create(DataClien.class);
    }
}
