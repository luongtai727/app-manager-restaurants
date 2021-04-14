package com.example.appnhanhang.FragmenActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import com.example.appnhanhang.Adapter.GioHangAdapter;
import com.example.appnhanhang.ChiTietBanActivity;
import com.example.appnhanhang.R;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_DangOrder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_DangOrder extends Fragment {

    static RecyclerView rec;
    static GioHangAdapter gioHangAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_DangOrder() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_DangOrder.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_DangOrder newInstance(String param1, String param2) {
        Fragment_DangOrder fragment = new Fragment_DangOrder();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static void remove()
    {
            gioHangAdapter.notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rec = view.findViewById(R.id.recGH);
        gioHangAdapter = new GioHangAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL, false);
        rec.setLayoutManager(linearLayoutManager);

    }

    public static void setDaTaDangOder()
    {
        gioHangAdapter.setData(ChiTietBanActivity.arrGioHang);

        AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(gioHangAdapter);
        alphaInAnimationAdapter.setDuration(500);
        alphaInAnimationAdapter.setHasStableIds(false);
        alphaInAnimationAdapter.setInterpolator(new OvershootInterpolator(.100f));
        alphaInAnimationAdapter.setFirstOnly(false);
        //   alphaInAnimationAdapter.setInterpolator(new BounceInterpolator());
        rec.setAdapter(alphaInAnimationAdapter);
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
        return inflater.inflate(R.layout.fragment__dang_order, container, false);
    }
}