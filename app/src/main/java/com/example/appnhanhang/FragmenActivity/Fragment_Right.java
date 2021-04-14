package com.example.appnhanhang.FragmenActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appnhanhang.Adapter.banAdapter;
import com.example.appnhanhang.Model.Ban;
import com.example.appnhanhang.R;
import com.example.appnhanhang.Retrofit.APIUtils;
import com.example.appnhanhang.Retrofit.DataClien;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.ScaleInAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Right extends Fragment {

    static RecyclerView recyclerView_ban;
    static banAdapter banadapter;
    static List<Ban> l = new ArrayList<>();
    TextView txt;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView_ban = view.findViewById(R.id.rec);
        banadapter = new banAdapter();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView_ban.setLayoutManager(gridLayoutManager);


        LoadTatCaBan();

    }

    public static void LoadTatCaBan()
    {
        l.clear();
        DataClien dataClien = APIUtils.getData();
        Call<List<Ban>> callback =  dataClien.LoadBan();

        callback.enqueue(new Callback<List<Ban>>() {
            @Override
            public void onResponse(Call<List<Ban>> call, Response<List<Ban>> response) {
                l = response.body();
                banadapter.setData(l);
              // recyclerView_ban.setAdapter(banadapter);
                recyclerView_ban.setAdapter(new ScaleInAnimationAdapter(banadapter));
            }

            @Override
            public void onFailure(Call<List<Ban>> call, Throwable t) {
                Log.d("aa",t.getMessage());
            }
        });

    }

    public  void LoadBanTrong()
    {
        l.clear();
        DataClien dataClien = APIUtils.getData();
        Call<List<Ban>> callback =  dataClien.LoadBanTrong();

        callback.enqueue(new Callback<List<Ban>>() {
            @Override
            public void onResponse(Call<List<Ban>> call, Response<List<Ban>> response) {
                l = response.body();
                banadapter.setData(l);
                recyclerView_ban.setAdapter(banadapter);
            }

            @Override
            public void onFailure(Call<List<Ban>> call, Throwable t) {
                Log.d("aa",t.getMessage());
            }
        });
    }

    public void LoadBanDaCoKhach()
    {
        l.clear();
        DataClien dataClien = APIUtils.getData();
        Call<List<Ban>> callback =  dataClien.LoadBanDaCoKhach();

        callback.enqueue(new Callback<List<Ban>>() {
            @Override
            public void onResponse(Call<List<Ban>> call, Response<List<Ban>> response) {
                l = response.body();
                banadapter.setData(l);
                recyclerView_ban.setAdapter(banadapter);
            }

            @Override
            public void onFailure(Call<List<Ban>> call, Throwable t) {
                Log.d("aa",t.getMessage());
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__right, container, false);


        return view;
    }
}