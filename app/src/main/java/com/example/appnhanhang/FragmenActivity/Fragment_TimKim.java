package com.example.appnhanhang.FragmenActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.appnhanhang.Adapter.MonAnAdapter;
import com.example.appnhanhang.Adapter.MonAnSearchAdapter;
import com.example.appnhanhang.Animation.AnimationUtil;
import com.example.appnhanhang.ChiTietBanActivity;
import com.example.appnhanhang.Model.MonAn;
import com.example.appnhanhang.R;
import com.example.appnhanhang.Retrofit.APIUtils;
import com.example.appnhanhang.Retrofit.DataClien;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_TimKim#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_TimKim extends Fragment {

    public static RecyclerView rec;
    public static MonAnSearchAdapter monAnSearchAdapter;
    List<MonAn> listMonAnSearch;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_TimKim() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_TimKim.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_TimKim newInstance(String param1, String param2) {
        Fragment_TimKim fragment = new Fragment_TimKim();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listMonAnSearch = new ArrayList<>();
        monAnSearchAdapter = new MonAnSearchAdapter();
        rec = view.findViewById(R.id.recTimKim);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        rec.addItemDecoration(itemDecoration);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL, false);
        rec.setLayoutManager(linearLayoutManager);

        listMonAnSearch.clear();
        DataClien dataClien = APIUtils.getData();
        Call<List<MonAn>> callback =  dataClien.LoadAllMonAn();
        callback.enqueue(new Callback<List<MonAn>>() {
            @Override
            public void onResponse(Call<List<MonAn>> call, Response<List<MonAn>> response) {
                listMonAnSearch = response.body();
                monAnSearchAdapter.setData(listMonAnSearch);
                rec.setAdapter(monAnSearchAdapter);

            }

            @Override
            public void onFailure(Call<List<MonAn>> call, Throwable t) {
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
        return inflater.inflate(R.layout.fragment__tim_kim, container, false);
    }
}