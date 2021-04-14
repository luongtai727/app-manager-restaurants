package com.example.appnhanhang;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.appnhanhang.Model.Ban;
import com.example.appnhanhang.Model.User;
import com.example.appnhanhang.Retrofit.APIUtils;
import com.example.appnhanhang.Retrofit.DataClien;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    List<User> l;
    EditText editUsername, editPassword;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editUsername = findViewById(R.id.edit_username);//bat su kien
        editPassword = findViewById(R.id.edit_password);
        btnLogin = findViewById(R.id.btn_Login);
               l= new ArrayList<>();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editUsername.getText().toString()) || TextUtils.isEmpty(editPassword.getText().toString()))
                {
                    Dialog dialog = new Dialog(v.getContext());
                    dialog.setContentView(R.layout.dialog_dangnhap);
                    TextView txtTile = dialog.findViewById(R.id.txtNoiDung1);
                    txtTile.setText("Tên đăng nhập và mật khẩu không được trống!");
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    Window window = dialog.getWindow();
                    window.setGravity(Gravity.CENTER);
                    Button btnOK = dialog.findViewById(R.id.btn);
                    btnOK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });

                    dialog.show();
                    return;
                }
                DataClien dataClien = APIUtils.getData();
                Call<List<User>> callback =  dataClien.login(editUsername.getText().toString(), editPassword.getText().toString());
                String a = editUsername.getText().toString();
                String b = editPassword.getText().toString();
                callback.enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        l = response.body();
                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                        ChiTietBanActivity.idnhanvien = Integer.parseInt(l.get(0).getId());
                        ChiTietBanActivity.tenNhanVien = (l.get(0).getHoTen());
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Dialog dialog = new Dialog(v.getContext());
                        dialog.setContentView(R.layout.dialog_dangnhap);
                        TextView txtTile = dialog.findViewById(R.id.txtNoiDung1);
                        txtTile.setText("Tên đăng nhập hoặc mật khẩu sai!");
                        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        Window window = dialog.getWindow();
                        window.setGravity(Gravity.CENTER);
                        Button btnOK = dialog.findViewById(R.id.btn);
                        btnOK.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.cancel();
                            }
                        });
                        dialog.show();
                    }
                });
            }
        });
    }
}