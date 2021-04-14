package com.example.appnhanhang.FragmenActivity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.appnhanhang.Adapter.DanhSachMonAdapter;
import com.example.appnhanhang.Model.Ban;
import com.example.appnhanhang.Model.DanhSachMon;
import com.example.appnhanhang.R;
import com.example.appnhanhang.Retrofit.APIUtils;
import com.example.appnhanhang.Retrofit.DataClien;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_DanhMucMon#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_DanhMucMon extends Fragment{

    RecyclerView recyclerView;
    DanhSachMonAdapter danhSachMonAdapter;
    List<DanhSachMon> listDSM;
    FragmentManager fragmentManager;
    Fragment_Mon fragment_mon;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_DanhMucMon() {
        // Required empty public constructor

    }

    // TODO: Rename and change types and number of parameters
    public static Fragment_DanhMucMon newInstance(String param1, String param2) {
        Fragment_DanhMucMon fragment = new Fragment_DanhMucMon();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       // btn = view.findViewById(R.id.button1);
        recyclerView = view.findViewById(R.id.recy_dmm);
        listDSM = new ArrayList<>();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()
                , recyclerView.HORIZONTAL,false);

        recyclerView.setLayoutManager(linearLayoutManager);


        danhSachMonAdapter = new DanhSachMonAdapter();

        listDSM.clear();
        DataClien dataClien = APIUtils.getData();
        Call<List<DanhSachMon>> callback =  dataClien.LoadDanhMuc();
        callback.enqueue(new Callback<List<DanhSachMon>>() {
            @Override
            public void onResponse(Call<List<DanhSachMon>> call, Response<List<DanhSachMon>> response) {

                listDSM = response.body();
                listDSM.add(0,new DanhSachMon("0","Tất cả", "0"));
                danhSachMonAdapter.setData(listDSM);
                recyclerView.setAdapter(danhSachMonAdapter);
                Log.d("aa",listDSM.get(0).getDanhmuc());
            }

            @Override
            public void onFailure(Call<List<DanhSachMon>> call, Throwable t) {
                Log.d("aa",t.getMessage());
            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment__danh_muc_mon, container, false);
    }

}