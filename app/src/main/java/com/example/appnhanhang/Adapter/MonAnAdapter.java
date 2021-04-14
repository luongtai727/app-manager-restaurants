package com.example.appnhanhang.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhanhang.ChiTietBanActivity;
import com.example.appnhanhang.FragmenActivity.Fragment_Mon;
import com.example.appnhanhang.Model.GioHang;
import com.example.appnhanhang.Model.MonAn;
import com.example.appnhanhang.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class MonAnAdapter extends RecyclerView.Adapter<MonAnAdapter.ViewHolder> {

    List<MonAn> listMonAn;
    iClickAddToCartListener iclickaddToCartListener;


    public interface iClickAddToCartListener
    {
        void OnClickAddToCart(ImageView img ,MonAn monAn);

    }

    public void setData(List<MonAn> list, iClickAddToCartListener iclick)
    {
        this.listMonAn = list;
        this.iclickaddToCartListener = iclick;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mon, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MonAn monAn = listMonAn.get(position);
        DecimalFormat decimalForma = new DecimalFormat("###,###,###");

        holder.txtTenMon.setText(monAn.getTenmon());
        holder.txtGia.setText(decimalForma.format((int)Double.parseDouble(monAn.getDongia())));
        holder.txtChiTiet.setText(monAn.getChitiet());
        Picasso
                .get()
                .load(monAn.getAnh())
                .into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChiTietBanActivity.coutCart++;

                GioHang gh = new GioHang();
                gh.setTenMon(monAn.getTenmon());
                gh.setGia(Double.parseDouble(monAn.getDongia()));
                gh.setIdMonAn(Integer.parseInt(monAn.getId()));
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                ChiTietBanActivity.ThanhToanl = ChiTietBanActivity.ThanhToanl + (int)Double.parseDouble(monAn.getDongia());
                ChiTietBanActivity.btnthanhtoan.setText(decimalFormat.format(ChiTietBanActivity.ThanhToanl));

                int length = ChiTietBanActivity.arrGioHang.size();
                boolean check = false;
                for (int i = 0; i < length; i++)
                {
                    if (ChiTietBanActivity.arrGioHang.get(i).getTenMon().equals(gh.getTenMon()))
                    {
                        check = true;
                        ChiTietBanActivity.arrGioHang.get(i).setSoLuong(ChiTietBanActivity.arrGioHang.get(i).getSoLuong() + 1);
                        ChiTietBanActivity.arrGioHang.get(i).setGia( ChiTietBanActivity.arrGioHang.get(i).getSoLuong() * gh.getGia());
                        break;
                    }
                }

                if (!check)
                {
                    gh.setSoLuong(1);
                    ChiTietBanActivity.arrGioHang.add(gh);
                }

                iclickaddToCartListener.OnClickAddToCart(holder.img, monAn);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMonAn.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtTenMon;
        TextView txtChiTiet;
        TextView txtGia;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTenMon = itemView.findViewById(R.id.txtTenMonAn);
            txtChiTiet = itemView.findViewById(R.id.txtChiTietMon);
            txtGia = itemView.findViewById(R.id.txtGiaMon);
            img = itemView.findViewById(R.id.imgMon);

        }
    }

}
