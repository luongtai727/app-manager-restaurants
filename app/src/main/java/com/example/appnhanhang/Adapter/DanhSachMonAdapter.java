package com.example.appnhanhang.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhanhang.FragmenActivity.Fragment_Mon;
import com.example.appnhanhang.FragmenActivity.Fragment_Right;
import com.example.appnhanhang.Model.DanhSachMon;
import com.example.appnhanhang.Model.ValueColor;
import com.example.appnhanhang.Model.color;
import com.example.appnhanhang.R;

import java.util.List;
import java.util.Random;
import java.util.Vector;

public class DanhSachMonAdapter extends RecyclerView.Adapter<DanhSachMonAdapter.ViewHolder> {

    List<DanhSachMon> List_DanhMucMon;
    Random rd = new Random();
    ValueColor valueColor = new ValueColor();
    Vector<color> v = new Vector();
    OnTextClickListener listener;
    Fragment_Mon fragment_mon;
    String  tmp;

    public DanhSachMonAdapter()
    {

    }

    public  DanhSachMonAdapter(Fragment_Mon fragmentmon)
     {
         this.fragment_mon = fragmentmon;
     }
    public void setData( List<DanhSachMon> ListDanhMucMon)
    {
        this.List_DanhMucMon = ListDanhMucMon;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhsachmon, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DanhSachMon dsm = List_DanhMucMon.get(position);
        if (dsm == null)
            return;
        holder.txtTenDM.setText(dsm.getDanhmuc());
        color iNew;
        for (int i = 0; i < 10;  ) {
            iNew = valueColor.getListColor(rd.nextInt(10));
            if (!v.contains(iNew)){
                i++;
                holder.txtTenDM.setBackgroundColor((Color.rgb(iNew.getR(), iNew.getG(), iNew.getB())));
                v.add(iNew);
                break;
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm;
                Fragment_Mon.loadData(dsm.getId());
            }
        });
    }
    @Override
    public int getItemCount() {
        return List_DanhMucMon.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtTenDM;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTenDM = itemView.findViewById(R.id.txtTenDSM);

        }
    }

}
