package com.example.appnhanhang;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.andremion.counterfab.CounterFab;
import com.example.appnhanhang.Adapter.ViewPagerApdater;
import com.example.appnhanhang.FragmenActivity.Fragment_DSMon;
import com.example.appnhanhang.FragmenActivity.Fragment_DaOder;
import com.example.appnhanhang.FragmenActivity.Fragment_DangOrder;
import com.example.appnhanhang.FragmenActivity.Fragment_TimKim;
import com.example.appnhanhang.Model.DanhSachMon;
import com.example.appnhanhang.Model.GioHang;
import com.example.appnhanhang.Model.IdHoaDon;
import com.example.appnhanhang.Model.order;
import com.example.appnhanhang.Retrofit.APIUtils;
import com.example.appnhanhang.Retrofit.DataClien;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietBanActivity extends AppCompatActivity {

    public static Button btnthanhtoan;
    public  static  boolean checkFloating = false;
    public  static  int MaHoaDon;
    public  static  String values;
    public  static  int idnhanvien;
    SearchView searchView;
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    Button btnLuu;
    Button btnThanhToan;

    public  static  CounterFab floatingActionButton;
    public static int coutCart;

    public static ArrayList<GioHang> arrGioHang;
    public  static  int ThanhToanl;
     public  static  String tenNhanVien;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietban);

        //Thieu set du lieu khi nhan vao ban
        arrGioHang = new ArrayList<>();
        coutCart = 0;
        ThanhToanl = 0;
        btnthanhtoan  = findViewById(R.id.btnThanhToan);
        floatingActionButton = findViewById(R.id.fab);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        toolbar = findViewById(R.id.toolbarChiTietBan);
        setSupportActionBar(toolbar);
        btnLuu = findViewById(R.id.btnLuuOder);
        btnThanhToan = findViewById(R.id.btnThanhToan);

        ViewPagerApdater viewPagerApdater = new ViewPagerApdater(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerApdater);
        tabLayout.setupWithViewPager(viewPager);
        Drawable drawable= getDrawable(R.drawable.ic_action_x);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        values = intent.getStringExtra("values");
        String []str = values.split("/");
        toolbar.setTitle(str[1]);
        if (!str[4].equals("null"))
        {
            MaHoaDon = Integer.parseInt(str[4]);
        }
        else
        {
            MaHoaDon = -1;
        }


        btnLuu.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (ChiTietBanActivity.MaHoaDon == -1)
                {
                    str[6] = java.time.LocalDateTime.now().toString();
                    TaoHoaDon();
                    DataClien dataClien1 = APIUtils.getData();
                    Call<String>  callback1 =  dataClien1.LoaIdHoaDon();
                    callback1.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (ChiTietBanActivity.MaHoaDon == -1)
                            {
                                ChiTietBanActivity.MaHoaDon = Integer.parseInt(response.body());
                                str[4] = response.body();
                                str[7] = idnhanvien + "";
                                Log.d("test", ChiTietBanActivity.MaHoaDon + "");
                                clickLuu();
                                UpdataTrangthaiban(Integer.parseInt(str[0]), 1);
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            ChiTietBanActivity.MaHoaDon = 1;
                            clickLuu();
                            UpdataTrangthaiban(Integer.parseInt(str[0]), 1);
                        }
                    });
                }
                else
                {
                    clickLuu();
                }

            }
        });

        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Fragment_DaOder.listOder.size() <= 0)
                {
                    return;
                }

                Intent intent = new Intent(v.getContext(), ThanhToanActivity.class);
                intent.putExtra("NgayVao", str[6]);
                intent.putExtra("TenBan", str[1]);
                intent.putExtra("idNhanVien", str[7]);
                intent.putExtra("idban", str[0]);
                intent.putExtra("idHoaDon", str[4]);
                startActivity(intent);
                finish();
            }
        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFloating == false) {
                    replaceFragment();
                    floatingActionButton.setImageDrawable(getDrawable(R.drawable.ic_action_shoppi));
                    checkFloating = true;
                }
                else {
                    removeFragment();
                    Fragment_DangOrder.setDaTaDangOder();
                    floatingActionButton.setImageDrawable(getDrawable(R.drawable.ic_action_plus1));
                    checkFloating = false;
                }
            }
        });
    }

    private void UpdataTrangthaiban(int idban, int trangthai) {

        DataClien dataClien1 = APIUtils.getData();
        Call<String>  callback1 =  dataClien1.updataTrangThai(idban, trangthai);
        callback1.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("aaaa",response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("aa", "khong co du lieu");
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void clickLuu() {

        String []str = values.split("/");
        if (arrGioHang.size()== 0)
        {
            return;
        }

        int lengthArrGioHang = arrGioHang.size();

        for(int i = 0; i < lengthArrGioHang; ++i)
        {
            sendDataFireBase(arrGioHang.get(i).getTenMon(), arrGioHang.get(i).getSoLuong() +"", arrGioHang.get(i).getGhichu(), str[1], tenNhanVien);
            arrGioHang.get(i).setMaHoaDon(ChiTietBanActivity.MaHoaDon);
            DataClien dataClien = APIUtils.getData();
            Call<String> callback =  dataClien.insertChiTietHoaDon(ChiTietBanActivity.MaHoaDon,
                                                                     arrGioHang.get(i).getIdMonAn(),
                                                                    arrGioHang.get(i).getSoLuong(),
                                                                    arrGioHang.get(i).getGia(),
                                                                    arrGioHang.get(i).getGhichu(),
                                                                    arrGioHang.get(i).getGiamGia());


            callback.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.d("test", response.body());
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.d("test", t.getMessage());
                }
            });

        }

        Fragment_DaOder.InserData(arrGioHang);
        arrGioHang.clear();
        Fragment_DangOrder.remove();
    }

    private void sendDataFireBase(String tenMon, String soLuong, String ghichu, String s, String tenNhanVien) {


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("oder");

        String id = myRef.push().getKey();
        order ord = new order(id,tenMon, soLuong, ghichu, s, tenNhanVien);

        myRef.child(id).setValue(ord).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                //nếu thêm thành công sẽ nhảy vào đây
                Toast.makeText(getApplicationContext(),"Thêm thành công!",Toast.LENGTH_LONG).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //nếu thêm thất bại sẽ nhảy vào đây
                Toast.makeText(getApplicationContext(),"Thêm thất bại! " + e.toString(),Toast.LENGTH_LONG).show();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void TaoHoaDon() {
        String []str = values.split("/");
        DataClien dataClien = APIUtils.getData();

        Call<String> callback =  dataClien.insertHoaDon(java.time.LocalDateTime.now().toString(),
                Integer.parseInt(str[0]),
                idnhanvien,
                0);


        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("test", response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("test", t.getMessage());
            }
        });
    }

    public  void replaceFragment()
    {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmenttmp, new Fragment_DSMon(),"a");
        fragmentTransaction.commitNow();
    }

    public  void removeFragment()
    {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag("a");
        if (fragment != null) {
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commitNow();
        }
    }


    @Override
    public void onBackPressed() {
        if (!searchView.isIconified())
        {
            searchView.setIconified(true);
            return;
        }

        super.onBackPressed();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @SuppressLint("ResourceAsColor")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        searchView = (SearchView) menuItem.getActionView();

        searchView.setQueryHint("Tên món ăn...");


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Fragment_TimKim.monAnSearchAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Fragment_TimKim.monAnSearchAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
