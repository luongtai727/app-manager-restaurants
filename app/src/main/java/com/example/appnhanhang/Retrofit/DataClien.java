package com.example.appnhanhang.Retrofit;


import com.example.appnhanhang.Model.Ban;
import com.example.appnhanhang.Model.DaOder;
import com.example.appnhanhang.Model.DanhSachMon;
import com.example.appnhanhang.Model.IdHoaDon;
import com.example.appnhanhang.Model.MonAn;
import com.example.appnhanhang.Model.User;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface DataClien {

    @Multipart
    @POST("image.php")
    Call<String> UploadPhoto(@Part MultipartBody.Part photo);

    @FormUrlEncoded
    @POST("insertChiTietHoaDon.php")
    Call<String> insertChiTietHoaDon(@Field("hoadonid") int hoadonid,
                        @Field("monanid") int monanid,
                        @Field("soluong") int soluong,
                        @Field("thanhtien") double thanhtien,
                        @Field("ghichu") String ghichu,
                        @Field("giamgia") int giamgia);


    @GET("LoadBan.php")
    Call<List<Ban>>  LoadBan();

    @GET("LoadBanTrong.php")
    Call<List<Ban>>  LoadBanTrong();

    @GET("LoadBanDaCoKhach.php")
    Call<List<Ban>>  LoadBanDaCoKhach();

    @GET("LoadDanhMuc.php")
    Call<List<DanhSachMon>>  LoadDanhMuc();

    @GET("LoadAllMonAn.php")
     Call<List<MonAn>> LoadAllMonAn();

    @GET("LoadMonAn1.php")
    Call<List<MonAn>> LoadAllMonAn1();

    @FormUrlEncoded
    @POST("LoadMonAn.php")
    Call<List<MonAn>> LoadMonAn(@Field("idDanhMuc") int IdDanhMuc);

    @FormUrlEncoded
    @POST("LoadChiTietOder.php")
    Call<List<DaOder>> LoadMonAnDaOder(@Field("idhoadon") int idhoadon);

    @FormUrlEncoded
    @POST("updateSoLuong.php")
    Call<String> updateSoLuong(@Field("soluong") int soluong,
                                     @Field("ThanhTien") int ThanhTien,
                                     @Field("Idmon") String Idmon,
                                     @Field("GiamGia") int GiamGia,
                                     @Field("GhiChu") String GhiChu,
                                     @Field("soluongbandau") int soluongbandau,
                                     @Field("Idhoadon") String Idhoadon,
                                     @Field("ThanhTienSau") int ThanhTienSau);

    @GET("delete_chitiethoadonAll.php")
    Call<String>  delete();

    @FormUrlEncoded
    @POST("updateGhiChu.php")
    Call<String> updateghichu(@Field("GhiChuMoi") String GhiChuMoi,
                               @Field("idHoaDon") String idHoaDon,
                               @Field("idMonAn") String idMonAn,
                               @Field("Soluong") int Soluong,
                               @Field("Thanhtien") int Thanhtien,
                               @Field("Ghichu") String Ghichu,
                               @Field("Giamgia") int Giamgia);

    @GET("delete_chitiethoadon.php")
    Call<String>  deleteChiTietHoaDon(@Query("idHoaDon") String idHoaDon,
                                      @Query("idMonAn") String idMonAn,
                                      @Query("Soluong") int Soluong,
                                      @Query("Thanhtien") int Thanhtien,
                                      @Query("Ghichu") String Ghichu,
                                      @Query("Giamgia") int Giamgia);
    @FormUrlEncoded
    @POST("updateGiamGia.php")
    Call<String> updategiamgia(@Field("GiamGiaSau") int GiamGiaSau,
                               @Field("Thanhtiensau") int Thanhtiensau,
                              @Field("Idhoadon") String Idhoadon,
                              @Field("Idmon") String Idmon,
                              @Field("soluong") int soluong,
                              @Field("ThanhTien") int ThanhTien,
                              @Field("GhiChu") String GhiChu,
                              @Field("GiamGia") int GiamGia);

    @GET("delete_allThanhTien.php")
    Call<String>  delete_allThanhTien();

    @FormUrlEncoded
    @POST("insert_HoaDon.php")
    Call<String> insertHoaDon(@Field("ngaylap") String ngaylap,
                               @Field("banid") int banid,
                               @Field("nhanvien") int nhanvien,
                               @Field("trangthai") int trangthai);

    @GET("LoadIdHoaDon.php")
    Call<String>  LoaIdHoaDon();

    @GET("updateTrangThaiBan.php")
    Call<String>  updataTrangThai(@Query("idban") int idban,
                                  @Query("trangthai") int trangthai);

    @GET("updateTrangThaiHoaDon.php")
    Call<String>  updateTrangThaiHoaDon(@Query("idhoadon") int idHoaDon,
                                  @Query("trangthai") int trangthai);


    @GET("LoadTenNhanVien.php")
    Call<String> LoadTenNhanVien(@Query("idnhanvien") int idnhanvien);

    @FormUrlEncoded
    @POST("updateDateHoaDon.php")
    Call<String> updateDateHoaDon(@Field("GhiChuMoi") String GhiChuMoi,
                              @Field("idHoaDon") String idHoaDon,
                              @Field("idMonAn") String idMonAn,
                              @Field("Soluong") int Soluong,
                              @Field("Thanhtien") int Thanhtien,
                              @Field("Ghichu") String Ghichu,
                              @Field("Giamgia") int Giamgia);

    @FormUrlEncoded
    @POST("path.php")
    Call<String> insertToken(@Field("token") String token);

    @FormUrlEncoded
    @POST("login.php")
    Call<List<User>> login(@Field("hoTen") String hoTen,
                           @Field("matKhau") String matKhau
    );

    @FormUrlEncoded
    @POST("updateHoaDon.php")
    Call<String> updateHoaDon(@Field("ID") int ID,
                                  @Field("SoLuong") int SoLuong,
                                  @Field("NgayThanhToan") String NgayThanhToan,
                                  @Field("ThanhTien") long ThanhTien);


}