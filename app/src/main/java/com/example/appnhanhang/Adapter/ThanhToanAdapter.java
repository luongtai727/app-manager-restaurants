package com.example.appnhanhang.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhanhang.Model.DaOder;
import com.example.appnhanhang.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ThanhToanAdapter   extends  RecyclerView.Adapter<ThanhToanAdapter.ThanhToanHolder>{

    List<DaOder> list;
    public void setData(List<DaOder> list)
    {
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ThanhToanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thanhtoan, parent, false);
        return new ThanhToanHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhToanHolder holder, int position) {
        DaOder daOder = list.get(position);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtTenMon.setText(daOder.getTenMon());
        holder.txtSL.setText(daOder.getSoLuong());
        holder.txtThanhTien.setText(decimalFormat.format((int)Double.parseDouble(daOder.getThanhTien())));
        holder.txtDonGia.setText(decimalFormat.format((int)Double.parseDouble(daOder.getThanhTien()) / Integer.parseInt(daOder.getSoLuong())));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class ThanhToanHolder extends RecyclerView.ViewHolder
    {
        TextView txtTenMon;
        TextView txtSL;
        TextView txtDonGia;
        TextView txtThanhTien;

        public ThanhToanHolder(@NonNull View itemView) {
            super(itemView);
            txtTenMon = itemView.findViewById(R.id.txtTenMonTT);
            txtSL = itemView.findViewById(R.id.txtSoLuongTT);
            txtDonGia = itemView.findViewById(R.id.txtDonGiaTT);
            txtThanhTien = itemView.findViewById(R.id.txtTTienTT);
        }
    }
}
