package com.example.appnhanhang.FragmenActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;

import com.example.appnhanhang.Adapter.DaOderAdapter;
import com.example.appnhanhang.Adapter.DanhSachMonAdapter;
import com.example.appnhanhang.ChiTietBanActivity;
import com.example.appnhanhang.Model.DaOder;
import com.example.appnhanhang.Model.DanhSachMon;
import com.example.appnhanhang.Model.GioHang;
import com.example.appnhanhang.R;
import com.example.appnhanhang.Retrofit.APIUtils;
import com.example.appnhanhang.Retrofit.DataClien;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_DaOder extends Fragment {

    static DaOderAdapter daOderAdapter;
    public  static List<DaOder> listOder;
    static RecyclerView rec;
    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_DaOder() {
        // Required empty public constructor
    }

    public  static  void setdata()
    {
        daOderAdapter.setData(listOder);
        rec.setAdapter(daOderAdapter);
    }


    public static void InserData(List<GioHang> listGH)
    {
        int length = listGH.size();
        //String hoaDonsId, String soLuong, String thanhTien, String ghiChu, String giamGia, String tenMon, String id
        for (int i = 0; i < length; ++i)
        {
            DaOder tmp = new DaOder(String.valueOf(listGH.get(i).getMaHoaDon()),
                                    String.valueOf(listGH.get(i).getSoLuong()),
                                    String.valueOf(listGH.get(i).getGia()),
                                    listGH.get(i).getGhichu(),
                                    String.valueOf(listGH.get(i).getGiamGia()),
                                      listGH.get(i).getTenMon(),
                                    String.valueOf(listGH.get(i).getIdMonAn()));
            listOder.add(tmp);
        }
        daOderAdapter.setData(listOder);
        rec.setAdapter(daOderAdapter);
    }

    public static void setDaTaDaOder()
    {
        daOderAdapter.setData(listOder);
    }

    // TODO: Rename and change types and number of parameters
    public static Fragment_DaOder newInstance(String param1, String param2) {
        Fragment_DaOder fragment = new Fragment_DaOder();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rec = view.findViewById(R.id.recDaOder);
        listOder = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()
                , rec.VERTICAL,false);

        rec.setLayoutManager(linearLayoutManager);
        daOderAdapter  = new DaOderAdapter();

        listOder.clear();
        String []arr = ChiTietBanActivity.values.split("/");
        if (arr[4].equals("null"))
        {
            return;
        }
        DataClien dataClien = APIUtils.getData();
        Call<List<DaOder>> callback =  dataClien.LoadMonAnDaOder(Integer.parseInt(arr[4]));
        callback.enqueue(new Callback<List<DaOder>>() {
            @Override
            public void onResponse(Call<List<DaOder>> call, Response<List<DaOder>> response) {

                listOder = response.body();
                daOderAdapter.setData(listOder);

                AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(daOderAdapter);
                alphaInAnimationAdapter.setDuration(500);
                alphaInAnimationAdapter.setHasStableIds(false);
              //  alphaInAnimationAdapter.setInterpolator(new OvershootInterpolator(.100f));
                alphaInAnimationAdapter.setFirstOnly(false);
                alphaInAnimationAdapter.setInterpolator(new BounceInterpolator());

                rec.setAdapter(alphaInAnimationAdapter);
                Log.d("aa", String.valueOf(listOder.size()));
                int length = listOder.size();

                for (int i = 0; i < length; ++i)
                {
                    ChiTietBanActivity.coutCart += Integer.parseInt(listOder.get(i).getSoLuong());
                    ChiTietBanActivity.ThanhToanl += Integer.parseInt(listOder.get(i).getThanhTien());
                }

                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                ChiTietBanActivity.btnthanhtoan.setText(decimalFormat.format(ChiTietBanActivity.ThanhToanl));
                ChiTietBanActivity.floatingActionButton.setCount(ChiTietBanActivity.coutCart);
            }

            @Override
            public void onFailure(Call<List<DaOder>> call, Throwable t) {
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
        View view = inflater.inflate(R.layout.fragment__da_oder, container, false);
        return view;
    }
}