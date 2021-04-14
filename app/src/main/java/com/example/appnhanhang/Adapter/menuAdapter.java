package com.example.appnhanhang.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appnhanhang.Model.itemMenu;
import com.example.appnhanhang.R;

import java.util.List;

public class menuAdapter extends BaseAdapter {
    private Context context;
    private  int layout;
    private List<itemMenu> list;

    public menuAdapter(Context context, int layout, List<itemMenu> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class  ViewHolder
    {
        TextView textView;
        ImageView imageView;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(layout, null);
            viewHolder = new ViewHolder();

            viewHolder.textView = convertView.findViewById(R.id.txtTen);
            viewHolder.imageView = convertView.findViewById(R.id.imageMenu);

            convertView.setTag(viewHolder);
        }
        else
        {

            viewHolder = (ViewHolder) convertView.getTag();

        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)v.getContext()).finish();
            }
        });

        viewHolder.textView.setText(list.get(position).TenItem);
        viewHolder.imageView.setImageResource(list.get(position).icon);

        return convertView;
    }
}
