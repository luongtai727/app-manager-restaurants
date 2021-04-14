package com.example.appnhanhang.Adapter;

import android.app.Dialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
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
import com.example.appnhanhang.FragmenActivity.Fragment_DaOder;
import com.example.appnhanhang.FragmenActivity.Fragment_DangOrder;
import com.example.appnhanhang.Model.DaOder;
import com.example.appnhanhang.Model.DanhSachMon;
import com.example.appnhanhang.Model.mapGhiChu;
import com.example.appnhanhang.R;
import com.example.appnhanhang.Retrofit.APIUtils;
import com.example.appnhanhang.Retrofit.DataClien;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaOderAdapter  extends  RecyclerView.Adapter<DaOderAdapter.DaoderHolder>{

    List<DaOder> listDaoder;
    mapGhiChu mapghichu = new mapGhiChu();
    String str  = "";
    public void setData(List<DaOder> list)
    {
        this.listDaoder =  list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DaoderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daoder, parent, false);
        DaoderHolder viewHolder = new DaoderHolder(view);
        return viewHolder;
    }

    void thaydoisoluong(Dialog dialog, final int dongia, final int soluong, DaoderHolder holderr , int i)
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

                int gg = 0;
                String gc = "";
                if (holderr.txtGhiChu.getText().toString().length() > 0)
                {
                    gc = holderr.txtGhiChu.getText().toString();
                }
                if (holderr.txtGiam.getText().toString().length() > 0)
                {
                    gg = Integer.parseInt(holderr.txtGiam.getText().toString());
                }

                DataClien dataClien = APIUtils.getData();
                Call<String> callback =  dataClien.updateSoLuong(
                        Integer.parseInt(txtsoluong.getText().toString()),
                        (int)Double.parseDouble(listDaoder.get(i).getThanhTien()),
                        listDaoder.get(i).getId(),
                        gg,
                        gc,
                        Integer.parseInt(listDaoder.get(i).getSoLuong()),
                        listDaoder.get(i).getHoaDonsId() ,
                        Integer.parseInt(txtTongTien.getText().toString()));


                callback.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d("aa",response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("aa", "khong co du lieu");
                    }
                });

                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                holderr.txtSoLuong.setText((txtsoluong.getText().toString()));
                holderr.txtGia.setText(decimalFormat.format(Integer.parseInt(txtTongTien.getText().toString())));
                listDaoder.get(i).setSoLuong(txtsoluong.getText().toString());
                listDaoder.get(i).setThanhTien(txtTongTien.getText().toString());

                if (Integer.parseInt(holderr.txtSoLuong.getText().toString()) == 0)
                {

                    DataClien dataClien1 = APIUtils.getData();
                    Call<String>  callback1 =  dataClien1.delete();
                    callback1.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Log.d("aa",response.body());
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.d("aa", "khong co du lieu");
                        }
                    });
                    listDaoder.remove(i);
                    notifyDataSetChanged();
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
    public void onBindViewHolder(@NonNull DaoderHolder holder, int position) {

        DaOder daOder = listDaoder.get(position);
        DecimalFormat decimalForma = new DecimalFormat("###,###,###");
        holder.txtTenMon.setText(daOder.getTenMon());
        holder.txtGia.setText(decimalForma.format(Double.parseDouble(daOder.getThanhTien())));
        holder.txtSoLuong.setText(daOder.getSoLuong());

        Boolean check = TextUtils.isEmpty(daOder.getGiamGia());
        if (!check && Double.parseDouble(daOder.getGiamGia()) != 0)
        {
            holder.linearLayoutGiamGia.setVisibility(View.VISIBLE);
            holder.txtGiam.setText(decimalForma.format(Double.parseDouble(daOder.getGiamGia())));
        }

        if (!TextUtils.isEmpty(daOder.getGhiChu()))
        {
            holder.linearLayoutghichu.setVisibility(View.VISIBLE);
            int length = daOder.getGhiChu().length();
            String value = daOder.getGhiChu();
           /* String str = "";
            for (int i = 0; i < length;++i)
            {
                str += mapghichu.getValue(Integer.parseInt(String.valueOf(value.charAt(i)))) + ", ";
            }*/
            holder.txtGhiChu.setText(value);
        }

        final  int dongia = (int) (Double.parseDouble(daOder.getThanhTien()) /( Double.parseDouble(daOder.getSoLuong())));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = "";
                Dialog dialog = new Dialog(holder.itemView.getContext());
                dialog.setContentView(R.layout.dialog_tuychon);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.CENTER);

                TextView txt = dialog.findViewById(R.id.txtTenMontmp);
                txt.setText(daOder.getTenMon());

                Button btnSoLuong = dialog.findViewById(R.id.btnSLtmp);
                btnSoLuong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialogSL = new Dialog(holder.itemView.getContext());
                        dialogSL.setContentView(R.layout.dialog_soluong);
                        dialogSL.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                        int length = listDaoder.size();
                        int i;
                        for (i = 0; i < length; i++)
                        {
                            if (listDaoder.get(i).getTenMon().equals(daOder.getTenMon()))
                            {
                                break;
                            }
                        }

                        thaydoisoluong(dialogSL, dongia,Integer.parseInt( daOder.getSoLuong()), holder, i);
                        Window window = dialog.getWindow();


                        window.setGravity(Gravity.CENTER);
                        window.getAttributes().windowAnimations = R.style.DiaglogAniamtion;
                        dialogSL.show();
                        dialog.cancel();
                    }
                });

                Button btnghichu = dialog.findViewById(R.id.btnGhichutmp);
                btnghichu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        str = "";
                        Dialog dialoggc = new Dialog(holder.itemView.getContext());
                        dialoggc.setContentView(R.layout.dialog_ghichu);
                        dialoggc.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                        EditText editText = dialoggc.findViewById(R.id.editTextTextPersonName);

                        if (!TextUtils.isEmpty(daOder.getGhiChu()))
                        {
                            str = daOder.getGhiChu();
                        }
                        editText.setText(str);

                        TextView txtxoa = dialoggc.findViewById(R.id.btnXoa);
                        txtxoa.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                editText.setText("");
                                str = "";
                            }
                        });

                        Button btnbreakline = dialoggc.findViewById(R.id.btnxuongdong);
                        btnbreakline.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                editText.setText(editText.getText().toString() + "\n");
                            }
                        });

                        Button btnphay = dialoggc.findViewById(R.id.btnphay);
                        btnphay.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                editText.setText(editText.getText().toString() + ", ");
                            }
                        });

                        ImageView imgout = dialoggc.findViewById(R.id.imguot);
                        imgout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialoggc.cancel();
                                dialog.cancel();
                            }
                        });

                        Button btndongy = dialoggc.findViewById(R.id.btndongyGC);
                        btndongy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DataClien dataClien = APIUtils.getData();
                                Call<String> callback =  dataClien.updateghichu(
                                        str,
                                        listDaoder.get(position).getHoaDonsId()+"",
                                        listDaoder.get(position).getId() + "",
                                        Integer.parseInt(listDaoder.get(position).getSoLuong()),
                                        (int) Double.parseDouble(listDaoder.get(position).getThanhTien()),
                                        listDaoder.get(position).getGhiChu(),
                                        (int) Double.parseDouble(listDaoder.get(position).getGiamGia()));

                                Log.d("aa",  editText.getText().toString() + " " +
                                        listDaoder.get(position).getHoaDonsId()+" " +
                                        listDaoder.get(position).getId() + " " +
                                        listDaoder.get(position).getSoLuong() + " "+
                                        listDaoder.get(position).getThanhTien() +" " +
                                        listDaoder.get(position).getGhiChu()+ " " +
                                        listDaoder.get(position).getGiamGia() + " ");

                                callback.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {
                                        Log.d("aa",response.body());
                                    }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {
                                        Log.d("aa", "khong co du lieu");
                                    }
                                });
                                        listDaoder.get(position).setGhiChu(str);
                                        holder.txtGhiChu.setText(str);
                                        if (str.length() > 0)
                                        {

                                            holder.linearLayoutghichu.setVisibility(View.VISIBLE);

                                        }
                                        else {

                                            holder.linearLayoutghichu.setVisibility(View.GONE);

                                        }


                                dialoggc.cancel();
                                dialog.cancel();
                            }
                        });

                        RecyclerView recyclerView = dialoggc.findViewById(R.id.recTopic);
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

                        Window window = dialoggc.getWindow();
                        window.setGravity(Gravity.CENTER);
                        window.getAttributes().windowAnimations = R.style.DiaglogAniamtion;
                        dialoggc.show();
                        dialog.cancel();
                    }
                });

                Button btnhuymonoder = dialog.findViewById(R.id.btnHuyMontmp);
                btnhuymonoder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       DataClien dataClien = APIUtils.getData();
                        Call<String> callback =  dataClien.deleteChiTietHoaDon(
                                listDaoder.get(position).getHoaDonsId()+"",
                                listDaoder.get(position).getId() + "",
                                Integer.parseInt(listDaoder.get(position).getSoLuong()),
                                (int) Double.parseDouble(listDaoder.get(position).getThanhTien()),
                                listDaoder.get(position).getGhiChu(),
                                (int) Double.parseDouble(listDaoder.get(position).getGiamGia()));

                        callback.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Log.d("aa",response.body());
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Log.d("aa", "khong co du lieu");
                            }
                        });

                        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                        ChiTietBanActivity.ThanhToanl -=   Double.parseDouble(listDaoder.get(position).getThanhTien());
                        ChiTietBanActivity.btnthanhtoan.setText(decimalFormat.format( ChiTietBanActivity.ThanhToanl));
                        ChiTietBanActivity.coutCart -= Integer.parseInt(listDaoder.get(position).getSoLuong());
                        ChiTietBanActivity.floatingActionButton.setCount(ChiTietBanActivity.coutCart);
                        Fragment_DaOder.listOder.remove(position);

                        Fragment_DaOder.setdata();

                     //   notifyDataSetChanged();
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
                                    int tongtien = (int)Double.parseDouble(listDaoder.get(position).getThanhTien());
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
                                        DataClien dataClien = APIUtils.getData();
                                        Call<String> callback =  dataClien.updategiamgia(
                                                (int)((Double.parseDouble(listDaoder.get(position).getThanhTien())) - giamgia[0]),
                                                giamgia[0],
                                                listDaoder.get(position).getHoaDonsId()+"",
                                                listDaoder.get(position).getId() + "",
                                                Integer.parseInt(listDaoder.get(position).getSoLuong()),
                                                (int) Double.parseDouble(listDaoder.get(position).getThanhTien()),
                                                listDaoder.get(position).getGhiChu(),
                                                (int) Double.parseDouble(listDaoder.get(position).getGiamGia()));

                                        callback.enqueue(new Callback<String>() {
                                            @Override
                                            public void onResponse(Call<String> call, Response<String> response) {
                                                Log.d("aa",response.body());
                                            }

                                            @Override
                                            public void onFailure(Call<String> call, Throwable t) {
                                                Log.d("aa", "khong co du lieu");
                                            }
                                        });
                                        int tongtien = (int)Double.parseDouble(listDaoder.get(position).getThanhTien());
                                        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                                        listDaoder.get(position).setThanhTien(giamgia[0] + "");
                                        ChiTietBanActivity.ThanhToanl =  (ChiTietBanActivity.ThanhToanl - tongtien) + giamgia[0];
                                        ChiTietBanActivity.btnthanhtoan.setText(decimalFormat.format( ChiTietBanActivity.ThanhToanl));
                                        ChiTietBanActivity.coutCart -= Integer.parseInt(listDaoder.get(position).getSoLuong());
                                        ChiTietBanActivity.floatingActionButton.setCount(ChiTietBanActivity.coutCart);
                                        listDaoder.remove(position);

                                        DataClien dataClien1 = APIUtils.getData();
                                        Call<String>  callback1 =  dataClien1.delete_allThanhTien();
                                        callback1.enqueue(new Callback<String>() {
                                            @Override
                                            public void onResponse(Call<String> call, Response<String> response) {
                                                Log.d("aa",response.body());
                                            }

                                            @Override
                                            public void onFailure(Call<String> call, Throwable t) {
                                                Log.d("aa", "khong co du lieu");
                                            }
                                        });

                                        notifyDataSetChanged();
                                        dialog.cancel();
                                        dialoggiamgia.cancel();
                                        return;
                                    }

                                    DataClien dataClien = APIUtils.getData();
                                    Call<String> callback =  dataClien.updategiamgia(
                                            (int)((Double.parseDouble(listDaoder.get(position).getThanhTien())) - giamgia[0]),
                                            giamgia[0],
                                            listDaoder.get(position).getHoaDonsId()+"",
                                            listDaoder.get(position).getId() + "",
                                            Integer.parseInt(listDaoder.get(position).getSoLuong()),
                                            (int) Double.parseDouble(listDaoder.get(position).getThanhTien()),
                                            listDaoder.get(position).getGhiChu(),
                                            (int) Double.parseDouble(listDaoder.get(position).getGiamGia()));

                                    callback.enqueue(new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {
                                            Log.d("aa",response.body());
                                        }

                                        @Override
                                        public void onFailure(Call<String> call, Throwable t) {
                                            Log.d("aa", "khong co du lieu");
                                        }
                                    });

                                    int tongtien = (int)Double.parseDouble(listDaoder.get(position).getThanhTien());
                                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                                    listDaoder.get(position).setThanhTien(giamgia[0] + "");
                                    ChiTietBanActivity.ThanhToanl =  (ChiTietBanActivity.ThanhToanl - tongtien) + giamgia[0];
                                    ChiTietBanActivity.btnthanhtoan.setText(decimalFormat.format( ChiTietBanActivity.ThanhToanl));
                                    listDaoder.get(position).setGiamGia((tongtien - giamgia[0])+"");
                                    notifyDataSetChanged();
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

                window.getAttributes().windowAnimations = R.style.DiaglogAniamtion;
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDaoder.size();
    }

    public class DaoderHolder extends RecyclerView.ViewHolder
    {
        TextView txtSoLuong;
        TextView txtTenMon;
        TextView txtGhiChu;
        TextView txtGiam;
        TextView txtGia;
        LinearLayout linearLayoutghichu;
        LinearLayout linearLayoutGiamGia;
        ImageView imgbad;

        public DaoderHolder(@NonNull View itemView) {
            super(itemView);

            linearLayoutghichu = itemView.findViewById(R.id.linearLayoutGhiChu);
            linearLayoutGiamGia = itemView.findViewById(R.id.linearLayoutGiamGia);
            imgbad = itemView.findViewById(R.id.imgbad);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuongoder);
            txtTenMon = itemView.findViewById(R.id.txtTenMonoder);
            txtGhiChu = itemView.findViewById(R.id.txtGhiChuoder);
            txtGiam = itemView.findViewById(R.id.txtGiamGiaoder);
            txtGia = itemView.findViewById(R.id.txtGiaMonoder);
        }
    }

}
