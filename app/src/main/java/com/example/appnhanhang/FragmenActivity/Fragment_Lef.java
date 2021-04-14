package com.example.appnhanhang.FragmenActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.appnhanhang.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Lef#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Lef extends Fragment  {


    Button btnTatca;
    Button btnTrong;
    Button btnDaCoKhach;

    public Fragment_Lef() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnTatca = view.findViewById(R.id.btntatca);
        btnTrong = view.findViewById(R.id.btntrong);
        btnDaCoKhach = view.findViewById(R.id.btncokhach);

        btnTatca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Fragment_Right fragment_right = (Fragment_Right) fragmentManager.findFragmentById(R.id.fragment);
                fragment_right.LoadTatCaBan();
            }
        });

        btnTrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Fragment_Right fragment_right = (Fragment_Right) fragmentManager.findFragmentById(R.id.fragment);

                fragment_right.LoadBanTrong();

            }
        });

        btnDaCoKhach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Fragment_Right fragment_right = (Fragment_Right) fragmentManager.findFragmentById(R.id.fragment);
                fragment_right.LoadBanDaCoKhach();
            }
        });

    }

    public static Fragment_Lef newInstance(String param1, String param2) {
        Fragment_Lef fragment = new Fragment_Lef();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment__lef, container, false);
    }
}