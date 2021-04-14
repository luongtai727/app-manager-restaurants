package com.example.appnhanhang.Adapter;

import android.app.Dialog;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhanhang.ChiTietBanActivity;
import com.example.appnhanhang.FragmenActivity.Fragment_DangOrder;
import com.example.appnhanhang.Model.GioHang;
import com.example.appnhanhang.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHoldelGioHang>{

    List<GioHang> lisGioHang;
    String str  = "";
    public void setData(List<GioHang> lis)
    {
        lisGioHang = lis;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHoldelGioHang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang, parent, false);
        ViewHoldelGioHang viewHolder = new ViewHoldelGioHang(view);
        return viewHolder;
    }

    void thaydoisoluong( Dialog dialog, final int dongia, final int soluong, ViewHoldelGioHang holder, int i)
    {
        TextView txtsoluong = dialog.findViewById(R.id.txtSoLuongsl);
        TextView txtgiatri = dialog.findViewById(R.id.txtGiaTri);
        TextView txtTongTien = dialog.findViewById(R.id.txtTongTien);

        txtgiatri.setText(String.valueOf(dongia));
        txtsoluong.setText(String.valueOf(soluong));
        txtTongTien.setText(String.valueOf(soluong * dongia));

        Button btndy = dialog.findViewById(R.id.btnDongY);
        Button btnkhong = dialog.findViewById(R.id.btnKhong);
        Button btnmot = dialog.findViewById(R.id.btnMot);
        Button btnhai = dialog.findViewById(R.id.btnHai);
        Button btnba = dialog.findViewById(R.id.btnBa);
        Button btnbon = dialog.findViewById(R.id.btnBon);
        Button btnnam = dialog.findViewById(R.id.btnNam);
        Button btnsau = dialog.findViewById(R.id.btnSau);
        Button btnbay = dialog.findViewById(R.id.btnBay);
        Button btntam = dialog.findViewById(R.id.btnTam);
        Button btnchin = dialog.findViewById(R.id.btnChin);
        Button btndel = dialog.findViewById(R.id.btnDel);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);
        Button btnDongY = dialog.findViewById(R.id.btnDongY);
        Button btndongyin = dialog.findViewById(R.id.btnDongYVaIn);

        btndy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                holder.txtSoLuong.setText((txtsoluong.getText().toString()));
                holder.txtGia.setText(decimalFormat.format(Integer.parseInt(txtTongTien.getText().toString())));
                ChiTietBanActivity.arrGioHang.get(i).setSoLuong(Integer.parseInt(txtsoluong.getText().toString()));
                ChiTietBanActivity.arrGioHang.get(i).setGia(Double.parseDouble(txtTongTien.getText().toString()));
                if (Integer.parseInt(holder.txtSoLuong.getText().toString()) == 0)
                {
                    ChiTietBanActivity.arrGioHang.remove(i);
                    Fragment_DangOrder.setDaTaDangOder();
                }
                ChiTietBanActivity.ThanhToanl += Integer.parseInt(txtTongTien.getText().toString()) - (dongia * soluong);
                ChiTietBanActivity.btnthanhtoan.setText(decimalFormat.format( ChiTietBanActivity.ThanhToanl));
                ChiTietBanActivity.coutCart += Integer.parseInt(txtsoluong.getText().toString()) - soluong;
                ChiTietBanActivity.floatingActionButton.setCount(ChiTietBanActivity.coutCart);
                dialog.cancel();
            }
        });

        Button btnhuy = dialog.findViewById(R.id.btnHuy);
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tmp = Integer.parseInt(txtsoluong.getText().toString())  / 10;
                txtsoluong.setText( String.valueOf(tmp));
                txtTongTien.setText(String.valueOf(tmp * Integer.parseInt(txtgiatri.getText().toString())));
            }
        });

        btnkhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tmp = Integer.parseInt(txtsoluong.getText().toString())  * 10;
                txtsoluong.setText( String.valueOf(tmp));
                txtTongTien.setText(String.valueOf(tmp * Integer.parseInt(txtgiatri.getText().toString())));
            }
        });

        btnmot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tmp = Integer.parseInt(txtsoluong.getText().toString())  * 10 + 1;
                txtsoluong.setText( String.valueOf(tmp));
                txtTongTien.setText(String.valueOf(tmp * Integer.parseInt(txtgiatri.getText().toString())));
            }
        });

        btnhai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tmp = Integer.parseInt(txtsoluong.getText().toString())  * 10 + 2;
                txtsoluong.setText( String.valueOf(tmp));
                txtTongTien.setText(String.valueOf(tmp * Integer.parseInt(txtgiatri.getText().toString())));
            }
        });

        btnba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tmp = Integer.parseInt(txtsoluong.getText().toString())  * 10 + 3;
                txtsoluong.setText( String.valueOf(tmp));
                txtTongTien.setText(String.valueOf(tmp * Integer.parseInt(txtgiatri.getText().toString())));
            }
        });

        btnbon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tmp = Integer.parseInt(txtsoluong.getText().toString())  * 10 + 4;
                txtsoluong.setText( String.valueOf(tmp));
                txtTongTien.setText(String.valueOf(tmp * Integer.parseInt(txtgiatri.getText().toString())));
            }
        });

        btnnam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tmp = Integer.parseInt(txtsoluong.getText().toString())  * 10 + 5;
                txtsoluong.setText( String.valueOf(tmp));
                txtTongTien.setText(String.valueOf(tmp * Integer.parseInt(txtgiatri.getText().toString())));
            }
        });

        btnsau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tmp = Integer.parseInt(txtsoluong.getText().toString())  * 10 + 6;
                txtsoluong.setText( String.valueOf(tmp));
                txtTongTien.setText(String.valueOf(tmp * Integer.parseInt(txtgiatri.getText().toString())));
            }
        });

        btnbay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tmp = Integer.parseInt(txtsoluong.getText().toString())  * 10 + 7;
                txtsoluong.setText( String.valueOf(tmp));
                txtTongTien.setText(String.valueOf(tmp * Integer.parseInt(txtgiatri.getText().toString())));
            }
        });

        btntam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tmp = Integer.parseInt(txtsoluong.getText().toString())  * 10 + 8;
                txtsoluong.setText( String.valueOf(tmp));
                txtTongTien.setText(String.valueOf(tmp * Integer.parseInt(txtgiatri.getText().toString())));
            }
        });

        btnchin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tmp = Integer.parseInt(txtsoluong.getText().toString())  * 10 + 9;
                txtsoluong.setText( String.valueOf(tmp));
                txtTongTien.setText(String.valueOf(tmp * Integer.parseInt(txtgiatri.getText().toString())));
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoldelGioHang holder, int position) {

        GioHang gh = lisGioHang.get(position);
        DecimalFormat dc = new DecimalFormat("###,###,###");
        holder.txtTenMonAn.setText(gh.getTenMon());
        holder.txtGia.setText(dc.format(gh.getGia()));
        holder.txtSoLuong.setText(String.valueOf(gh.getSoLuong()));
        holder.GhiChu.setText(gh.getGhichu());

        if (!TextUtils.isEmpty(gh.getGhichu()))
        {
            holder.linearLayoutGhiChu.setVisibility(View.VISIBLE);
        }
        if (gh.getGiamGia()> 0)
        {
            holder.txtgiamgiagh.setText(dc.format(gh.getGiamGia()));
            holder.linearLayoutGiamGia.setVisibility(View.VISIBLE);
        }

        final  int dongia =((int)gh.getGia() /(gh.getSoLuong()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(holder.itemView.getContext());
                dialog.setContentView(R.layout.dialog_tuychon);
                TextView txt = dialog.findViewById(R.id.txtTenMontmp);
                txt.setText(gh.getTenMon());
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.CENTER);
                window.getAttributes().windowAnimations = R.style.DiaglogAniamtion;

                str = "";
                Button btnSoLuong = dialog.findViewById(R.id.btnSLtmp);
                btnSoLuong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialogSL = new Dialog(holder.itemView.getContext());
                        dialogSL.setContentView(R.layout.dialog_soluong);
                        dialogSL.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                        int length = ChiTietBanActivity.arrGioHang.size();
                        int i;
                        for (i = 0; i < length; i++)
                        {
                            if (ChiTietBanActivity.arrGioHang.get(i).getTenMon().equals(gh.getTenMon()))
                            {
                                break;
                            }
                        }

                        thaydoisoluong(dialogSL, dongia, gh.getSoLuong(), holder, i);
                        Window window = dialogSL.getWindow();

                        window.setGravity(Gravity.CENTER);
                        window.getAttributes().windowAnimations = R.style.DiaglogAniamtion;
                        dialogSL.show();
                        dialog.cancel();
                    }
                });

                Button btnTopic = dialog.findViewById(R.id.btnGhichutmp);
                btnTopic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialogtopic = new Dialog(holder.itemView.getContext());
                        dialogtopic.setContentView(R.layout.dialog_ghichu);
                        dialogtopic.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        Window window = dialogtopic.getWindow();
                        window.setGravity(Gravity.CENTER);
                        window.getAttributes().windowAnimations = R.style.DiaglogAniamtion;

                        EditText editText = dialogtopic.findViewById(R.id.editTextTextPersonName);

                        if (!TextUtils.isEmpty(gh.getGhichu()))
                        {
                            str = gh.getGhichu();
                        }
                        editText.setText(str);

                        int length = ChiTietBanActivity.arrGioHang.size();

                        TextView txtxoa = dialogtopic.findViewById(R.id.btnXoa);
                        txtxoa.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                editText.setText("");
                                str = "";
                            }
                        });

                        Button btnbreakline = dialogtopic.findViewById(R.id.btnxuongdong);
                        btnbreakline.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                editText.setText(editText.getText().toString() + "\n");
                            }
                        });

                        Button btnphay = dialogtopic.findViewById(R.id.btnphay);
                        btnphay.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                editText.setText(editText.getText().toString() + ", ");
                            }
                        });

                        ImageView imgout = dialogtopic.findViewById(R.id.imguot);
                        imgout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogtopic.cancel();
                                dialog.cancel();
                            }
                        });

                        Button btndongy = dialogtopic.findViewById(R.id.btndongyGC);
                        btndongy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                        ChiTietBanActivity.arrGioHang.get(position).setGhichu(str);
                                        holder.GhiChu.setText(str);
                                        if (str.length() > 0)
                                        {

                                            holder.linearLayoutGhiChu.setVisibility(View.VISIBLE);

                                        }
                                        else
                                        {

                                            holder.linearLayoutGhiChu.setVisibility(View.GONE);

                                        }
                                dialogtopic.cancel();
                                dialog.cancel();
                            }
                        });

                        RecyclerView recyclerView = dialogtopic.findViewById(R.id.recTopic);
                        TenTopicAdapter tenTopicAdapter = new TenTopicAdapter(editText, new OnTextClickListener() {
                            @Override
                            public void onTextClick(String data) {
                                str+= data + ", ";
                            }
                        });
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.itemView.getContext()
                                , recyclerView.VERTICAL,false);

                        recyclerView.setLayoutManager(linearLayoutManager);
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList.add("ít đường");
                        arrayList.add("không đá");
                        arrayList.add("ít sữa");
                        arrayList.add("nhiều sữa");
                        arrayList.add("không đường");
                        arrayList.add("muối");
                        arrayList.add("nóng");
                        arrayList.add("lạnh");
                        arrayList.add("ngọt");
                        tenTopicAdapter.getData(arrayList);
                        recyclerView.setAdapter(tenTopicAdapter);
                        dialogtopic.show();
                        dialog.cancel();
                    }
                });

                Button btngiamgia = dialog.findViewById(R.id.btnGiamGiatmp);
                btngiamgia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialoggiamgia = new Dialog(holder.itemView.getContext());
                        dialoggiamgia.setContentView(R.layout.dialog_giamgia);
                        dialoggiamgia.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        Window window = dialoggiamgia.getWindow();
                        window.setGravity(Gravity.CENTER);
                        window.getAttributes().windowAnimations = R.style.DiaglogAniamtion;

                        EditText edtphantram = dialoggiamgia.findViewById(R.id.edtphantram);
                        EditText edttong = dialoggiamgia.findViewById(R.id.edttong);
                        final int[] giamgia = new int[1];
                        edtphantram.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                                int tongtien = (int) ChiTietBanActivity.arrGioHang.get(position).getGia();
                                if (edtphantram.getText().toString().length()  <= 0)
                                {

                                    edttong.setText( decimalFormat.format(tongtien));
                                   return;
                                }
                                int g = (int) (tongtien * (((Integer.parseInt(edtphantram.getText().toString())) * 10.0) / 1000));
                                 giamgia[0] = (tongtien - g);
                                edttong.setText( decimalFormat.format(giamgia[0]));
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });

                        Button btnDongY = dialoggiamgia.findViewById(R.id.btnDongYGG);
                        btnDongY.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (edtphantram.getText().toString().equals("0"))
                                {
                                    return;
                                }
                                if (edttong.getText().toString().equals("0"))
                                {
                                    int tongtien = (int) ChiTietBanActivity.arrGioHang.get(position).getGia();
                                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                                    ChiTietBanActivity.arrGioHang.get(position).setGia(giamgia[0]);
                                    ChiTietBanActivity.ThanhToanl =  (ChiTietBanActivity.ThanhToanl - tongtien) + giamgia[0];
                                    ChiTietBanActivity.btnthanhtoan.setText(decimalFormat.format( ChiTietBanActivity.ThanhToanl));
                                    ChiTietBanActivity.arrGioHang.get(position).setGiamGia(tongtien - giamgia[0]);
                                    ChiTietBanActivity.arrGioHang.remove(position);
                                    Fragment_DangOrder.setDaTaDangOder();
                                    dialog.cancel();
                                    dialoggiamgia.cancel();
                                    return;
                                }
                                int tongtien = (int) ChiTietBanActivity.arrGioHang.get(position).getGia();
                                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                                ChiTietBanActivity.arrGioHang.get(position).setGia(giamgia[0]);
                                ChiTietBanActivity.ThanhToanl =  (ChiTietBanActivity.ThanhToanl - tongtien) + giamgia[0];
                                ChiTietBanActivity.btnthanhtoan.setText(decimalFormat.format( ChiTietBanActivity.ThanhToanl));
                                ChiTietBanActivity.arrGioHang.get(position).setGiamGia(tongtien - giamgia[0]);
                                notifyItemChanged(position);
                                dialog.cancel();
                                dialoggiamgia.cancel();
                            }
                        });


                        Button btnhuybo = dialoggiamgia.findViewById(R.id.txtHuyBoGG);
                        btnhuybo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialoggiamgia.cancel();
                                dialog.cancel();
                            }
                        });

                        dialoggiamgia.show();
                        dialog.cancel();
                    }
                });

                Button btnhuy = dialog.findViewById(R.id.btnHuyMontmp);
                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                        ChiTietBanActivity.ThanhToanl -=   ChiTietBanActivity.arrGioHang.get(position).getGia();
                        ChiTietBanActivity.btnthanhtoan.setText(decimalFormat.format( ChiTietBanActivity.ThanhToanl));
                        ChiTietBanActivity.coutCart -= ChiTietBanActivity.arrGioHang.get(position).getSoLuong();
                        ChiTietBanActivity.floatingActionButton.setCount(ChiTietBanActivity.coutCart);
                        ChiTietBanActivity.arrGioHang.remove(position);
                        Fragment_DangOrder.setDaTaDangOder();
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });

        holder.btnGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int soluong = Integer.parseInt((String) holder.txtSoLuong.getText()) - 1;
                holder.txtSoLuong.setText(String.valueOf(soluong));
                int length = ChiTietBanActivity.arrGioHang.size();
                int tmp = 0;
                int i;

                for (i = 0; i < length; i++)
                {
                    if (ChiTietBanActivity.arrGioHang.get(i).getTenMon().equals(gh.getTenMon()))
                    {
                        tmp = i;
                        ChiTietBanActivity.arrGioHang.get(i).setSoLuong(soluong);
                        ChiTietBanActivity.arrGioHang.get(i).setGia(soluong * dongia);
                        break;
                    }
                }

                DecimalFormat decimalForma = new DecimalFormat("###,###,###");
                holder.txtGia.setText(decimalForma.format(soluong * dongia));
                ChiTietBanActivity.ThanhToanl -= dongia;
                ChiTietBanActivity.btnthanhtoan.setText(decimalForma.format(ChiTietBanActivity.ThanhToanl));
                --ChiTietBanActivity.coutCart;
                ChiTietBanActivity.floatingActionButton.setCount(ChiTietBanActivity.coutCart);

                if (soluong == 0)
                {
                    ChiTietBanActivity.arrGioHang.remove(i);
                    Fragment_DangOrder.setDaTaDangOder();
                }
            }
        });

        holder.btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt((String) holder.txtSoLuong.getText()) + 1;
                holder.txtSoLuong.setText(String.valueOf(soluong));
                int length = ChiTietBanActivity.arrGioHang.size();
                for (int i = 0; i < length; i++)
                {
                    if (ChiTietBanActivity.arrGioHang.get(i).getTenMon().equals(gh.getTenMon()))
                    {
                        ChiTietBanActivity.arrGioHang.get(i).setSoLuong(soluong);
                        ChiTietBanActivity.arrGioHang.get(i).setGia(soluong * dongia);

                        break;
                    }
                }

                DecimalFormat decimalForma = new DecimalFormat("###,###,###");
                holder.txtGia.setText(decimalForma.format(soluong * dongia));
                ChiTietBanActivity.ThanhToanl += dongia;
                ChiTietBanActivity.btnthanhtoan.setText(decimalForma.format(ChiTietBanActivity.ThanhToanl));
                ++ChiTietBanActivity.coutCart;
                ChiTietBanActivity.floatingActionButton.setCount(ChiTietBanActivity.coutCart);
            }
        });

    }
    @Override
    public int getItemCount() {
        return lisGioHang.size();
    }

    public static class ViewHoldelGioHang extends RecyclerView.ViewHolder
    {
        TextView txtTenMonAn;
        TextView txtGia;
        TextView GhiChu;
        TextView txtgiamgiagh;
        Button btnGiam;
        Button btnCong;
        TextView txtSoLuong;
        LinearLayout linearLayoutGhiChu;
        LinearLayout linearLayoutGiamGia;

        public ViewHoldelGioHang(@NonNull View itemView) {
            super(itemView);

            txtTenMonAn = itemView.findViewById(R.id.txtTenMonGH);
            txtGia = itemView.findViewById(R.id.txtGiaMonGH);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong);
            btnCong = itemView.findViewById(R.id.btnTangGH);
            btnGiam = itemView.findViewById(R.id.btnGiamGH);
            GhiChu = itemView.findViewById(R.id.txtGhiChuGH);
            txtgiamgiagh = itemView.findViewById(R.id.txtGiamGiaGH);

            linearLayoutGhiChu = itemView.findViewById(R.id.linearLayoutGhiChuGH);
            linearLayoutGiamGia = itemView.findViewById(R.id.linearLayoutGiamGiaGH);

        }
    }
}
