package com.example.appnhanhang;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.Serializable;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhanhang.Adapter.ThanhToanAdapter;
import com.example.appnhanhang.FragmenActivity.Fragment_DaOder;
import com.example.appnhanhang.FragmenActivity.Fragment_Right;
import com.example.appnhanhang.Model.DaOder;
import com.example.appnhanhang.Retrofit.APIUtils;
import com.example.appnhanhang.Retrofit.DataClien;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThanhToanActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView txtNgay;
    TextView txtVao;
    TextView txtBan;
    TextView txtNhanVien;
    TextView txtTongCong;
    TextView txtTienMat;
    RecyclerView recyclerView;
    ThanhToanAdapter thanhToanAdapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhtoan);
        toolbar = findViewById(R.id.toolbarThanhToan);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtNgay = findViewById(R.id.txtNgay);
        txtVao = findViewById(R.id.txtvao);
        txtBan = findViewById(R.id.txtBan);
        txtNhanVien = findViewById(R.id.txtNhanVien);
        txtTongCong = findViewById(R.id.txtTongCong);
        txtTienMat = findViewById(R.id.txtTienMat);
        recyclerView = findViewById(R.id.recThanhToan);

        txtNgay.setText(java.time.LocalDate.now().toString());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Intent intent = getIntent();
        txtVao.setText(intent.getStringExtra("NgayVao") + " ~ "+dateFormat.format(date).toString());
        txtBan.setText(intent.getStringExtra("TenBan"));

        int tmp = Integer.parseInt(intent.getStringExtra("idNhanVien"));
        setTenNhanVien(Integer.parseInt(intent.getStringExtra("idNhanVien")));


        List<DaOder> l = null;
        try {
            l = GomThanToan();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        thanhToanAdapter = new ThanhToanAdapter();
        thanhToanAdapter.setData(l);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(thanhToanAdapter);

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        int length = l.size();
        long TongTien = 0;
        for (int i = 0 ; i < l.size(); ++i)
        {
            TongTien += (int) Double.parseDouble(l.get(i).getThanhTien());
        }
        txtTongCong.setText(decimalFormat.format(TongTien));

        UpdateTrangthaiban(Integer.parseInt(intent.getStringExtra("idban")), 0);
        UpdateTrangThaiHoaDon(Integer.parseInt(intent.getStringExtra("idHoaDon")), 1);
        updateHoaDon(Integer.parseInt(intent.getStringExtra("idHoaDon")),ChiTietBanActivity.coutCart,dateFormat.format(date).toString(), TongTien);
        Fragment_Right.LoadTatCaBan();

    }

    private void  updateHoaDon(int id, int SoLuong, String NgayThanhToan, long ThanhTien)
    {
        DataClien dataClien = APIUtils.getData();
        Call<String>  callback =  dataClien.updateHoaDon(id, SoLuong,NgayThanhToan, ThanhTien);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("aaa",response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("aaa", "khong co du lieu");
            }
        });
    }

    private void UpdateTrangThaiHoaDon(int idHoaDon, int trangthai)
    {
        DataClien dataClien = APIUtils.getData();
        Call<String>  callback =  dataClien.updateTrangThaiHoaDon(idHoaDon, trangthai);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("aa",response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("aa", "khong co du lieu");
            }
        });

    }

    private void UpdateTrangthaiban(int idban, int trangthai) {

        DataClien dataClien = APIUtils.getData();
        Call<String>  callback =  dataClien.updataTrangThai(idban, trangthai);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("aa",response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("aa", "khong co du lieu");
            }
        });

    }

    private List<DaOder> GomThanToan() throws CloneNotSupportedException
    {
        List<DaOder> list = new ArrayList<>();
        int length = Fragment_DaOder.listOder.size();

        List<DaOder> listtmp = new ArrayList<>();
        for (int k = 0; k < length; ++k )
        {
            listtmp.add((DaOder) (Fragment_DaOder.listOder.get(k)).clone());
        }

        Collections.sort(listtmp, new Comparator<DaOder>() {
            @Override
            public int compare(DaOder o1, DaOder o2) {
                if (o1.getTenMon().equals(o2.getTenMon()))
                {
                    if  (Integer.parseInt(o1.getSoLuong()) >= Integer.parseInt(o2.getSoLuong()))
                    {
                        return  -1;
                    }
                    return  1;

                }

                if (o1.getTenMon().compareTo(o2.getTenMon()) > 0)
                {
                    return 1;
                }
                return  -1;
            }
        });

        int i = 0, j = 1;

        int tmp = 0;
        list.add(listtmp.get(i));
        while (j < length )
        {
            while (listtmp.get(i).getTenMon().equals(listtmp.get(j).getTenMon())
                    &&  (int)Double.parseDouble(listtmp.get(i).getThanhTien()) / (int)Double.parseDouble(listtmp.get(i).getSoLuong())
                    == (int)Double.parseDouble(listtmp.get(j).getThanhTien()) / Integer.parseInt(listtmp.get(j).getSoLuong()))
            {
                int sl = Integer.parseInt(listtmp.get(i).getSoLuong()) + Integer.parseInt(listtmp.get(j).getSoLuong());
                int tt = (int)Double.parseDouble(listtmp.get(i).getThanhTien()) + (int)Double.parseDouble(listtmp.get(j).getThanhTien());
                list.get(tmp).setThanhTien(tt + "");
                list.get(tmp).setSoLuong(sl + "");
                ++j;
                if (j >= length)
                {
                    break;
                }
            }
            if (j <length)
            {
                list.add(listtmp.get(j));
                ++tmp;
            }
            i = j;
            ++j;
        }

        return list;
    }



    private void setTenNhanVien(int idNhanVien) {
        DataClien dataClien = APIUtils.getData();
        Call<String> callback =  dataClien.LoadTenNhanVien(idNhanVien);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                txtNhanVien.setText(response.body() + "");
                Log.d("tennhanvien","" + response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
}