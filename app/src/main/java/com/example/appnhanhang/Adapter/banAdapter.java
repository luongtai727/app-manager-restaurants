package com.example.appnhanhang.Adapter;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhanhang.ChiTietBanActivity;
import com.example.appnhanhang.Model.Ban;
import com.example.appnhanhang.R;

import java.util.List;

public class banAdapter  extends  RecyclerView.Adapter<banAdapter.ViewHolder>{


    List<Ban> list;

    public banAdapter() {
    }

    public void setData(List<Ban> l)
    {
        this.list = l;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phong, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ban ban = list.get(position);
        if (ban == null)
            return;

        holder.txtTenBan.setText(ban.getTenBan());
        holder.txtLoaiban.setText(ban.getTenLoaiBan());
        String tt = "";
        if (Integer.parseInt(ban.getTrangThai()) == 0)
        {
            tt = "Trống";
        }
        else if(Integer.parseInt(ban.getTrangThai()) == 1)
        {
            tt = "Đã có khách";
            holder.txtTenBan.setBackgroundColor((Color.rgb(25, 155, 252)));
            holder.txtTinhTrang.setBackgroundColor((Color.rgb(41, 147, 160)));
            holder.txtLoaiban.setBackgroundColor((Color.rgb(32, 187, 252)));
        }
        else
        {
            tt = "Chưa dọn";
            holder.txtTenBan.setBackgroundColor((Color.rgb(244, 67, 54)));
        }
        holder.txtTinhTrang.setText(tt);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), ChiTietBanActivity.class);
                String s = ban.getId()+"/"+ban.getTenBan()+"/"+ban.getTenLoaiBan()+"/"+ban.getTrangThai()+"/"+ban.getIdHoaDon()+"/"+ban.getTrangThaiHD()+"/"+ban.getNgayLap()+"/"+ban.getIdNhanVien();
                intent.putExtra("values", s);

                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    public static class  ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTinhTrang;
        private TextView txtTenBan;
        private TextView txtLoaiban;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            txtTinhTrang = itemView.findViewById(R.id.txttinhtrang);
            txtTenBan = itemView.findViewById(R.id.txttenban);
            txtLoaiban = itemView.findViewById(R.id.txtLoaiBan);

        }
    }
}
