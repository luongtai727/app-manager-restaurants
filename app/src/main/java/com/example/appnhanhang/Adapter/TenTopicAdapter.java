package com.example.appnhanhang.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhanhang.R;

import java.util.List;

public class TenTopicAdapter extends RecyclerView.Adapter<TenTopicAdapter.TopicHolder> {

    List<String> listTen;
    EditText edt;
    OnTextClickListener listener;



    public  TenTopicAdapter(EditText ed, OnTextClickListener listener)
    {
        this.edt = ed;
        this.listener = listener;
    }

    void getData(List<String> list_ten)
    {
        listTen = list_ten;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TopicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tenghichu, parent, false);
        TopicHolder viewHolder = new TopicHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TopicHolder holder, int position) {
        String str = listTen.get(position);
        holder.textView.setText(str);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.setText(edt.getText().toString()+ " " + str);
                listener.onTextClick(str);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listTen.size();
    }


    public class TopicHolder extends RecyclerView.ViewHolder
    {
        TextView textView;

        public TopicHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txtTenTopingGhiChu);
        }
    }
}
