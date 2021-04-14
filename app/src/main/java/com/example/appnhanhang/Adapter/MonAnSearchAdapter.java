package com.example.appnhanhang.Adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhanhang.ChiTietBanActivity;
import com.example.appnhanhang.FragmenActivity.Fragment_DangOrder;
import com.example.appnhanhang.FragmenActivity.Fragment_TimKim;
import com.example.appnhanhang.Model.GioHang;
import com.example.appnhanhang.Model.MonAn;
import com.example.appnhanhang.R;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MonAnSearchAdapter  extends RecyclerView.Adapter<MonAnSearchAdapter.MonAnHolder> implements Filterable {

    public static List<MonAn> listMonAn;
    public static List<MonAn> listMonAntmp;

    public void setData(List<MonAn> list)
    {
        this.listMonAn = new ArrayList<>(list);
        listMonAntmp = new ArrayList<>(list);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MonAnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_monan_search, parent, false);
        return new  MonAnHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonAnHolder holder, int position) {
        MonAn monAn = listMonAn.get(position);
        DecimalFormat decimalForma = new DecimalFormat("###,###,###");

        holder.txtTenMon.setText(monAn.getTenmon());
        holder.txtGia.setText(decimalForma.format((int)Double.parseDouble(monAn.getDongia())));
        holder.txtSoLuong.setText("");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(holder.txtSoLuong.getText()))
                {
                    holder.txtSoLuong.setText("0");
                }
                holder.txtSoLuong.setText((Integer.parseInt(holder.txtSoLuong.getText().toString()) + 1) + "");

                ChiTietBanActivity.coutCart++;
                ChiTietBanActivity.floatingActionButton.setCount(ChiTietBanActivity.coutCart);
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
                Fragment_DangOrder.setDaTaDangOder();

            }
        });
    }

    @Override
    public int getItemCount() {
        return listMonAn.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                Fragment_TimKim.rec.setVisibility(View.VISIBLE);
                String strSearch = constraint.toString();
                if (strSearch.isEmpty())
                {
                    listMonAn.clear();

                }
                else
                {
                    List<MonAn> listold = new ArrayList<>();
                    for (MonAn monan: listMonAntmp )
                    {
                        if (monan.getTenmon().toLowerCase().contains(strSearch.toLowerCase()))
                        {
                            listold.add(monan);
                        }
                    }


                    listMonAn = listold;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listMonAn;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listMonAn = (List<MonAn>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MonAnHolder extends RecyclerView.ViewHolder
    {
        TextView txtTenMon;
        TextView txtSoLuong;
        TextView txtGia;
        ImageView img;

        public MonAnHolder(@NonNull View itemView) {
            super(itemView);

            txtTenMon = itemView.findViewById(R.id.txtTenMonSearch);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuongSearch);
            txtGia = itemView.findViewById(R.id.txtGiaMonSearch);
            img = itemView.findViewById(R.id.ImgSearch);

        }
    }
}
