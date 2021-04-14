package com.example.appnhanhang;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhanhang.Adapter.banAdapter;
import com.example.appnhanhang.Adapter.menuAdapter;
import com.example.appnhanhang.FragmenActivity.Fragment_Right;
import com.example.appnhanhang.Model.Ban;
import com.example.appnhanhang.Model.itemMenu;
import com.example.appnhanhang.Retrofit.APIUtils;
import com.example.appnhanhang.Retrofit.DataClien;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ListView lv;
    ArrayList<itemMenu> arrayList_menu;
    menuAdapter adaptermenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        actionBar();
        actionMenu();

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String token = instanceIdResult.getToken();
                Log.d("token",token);

                DataClien dataClien = APIUtils.getData();
                Call<String> callback =  dataClien.insertToken(token);

                callback.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d("token","thanh cong");
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                    }
                });

            }
        });



        //getSupportFragmentManager().beginTransaction().replace(R.id.maincontainer, new Fragment_Right()).commit();
    }

    private void actionMenu() {
        arrayList_menu = new ArrayList<>();
        arrayList_menu.add(new itemMenu("Đăng xuất", R.drawable.logout));
        adaptermenu = new menuAdapter(this, R.layout.itemmenu, arrayList_menu);
        lv.setAdapter(adaptermenu);
    }

    private void actionBar() {
        setSupportActionBar(toolbar);
        toolbar.setTitle(ChiTietBanActivity.tenNhanVien);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_action_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav);
        lv = findViewById(R.id.lv);

    }
}