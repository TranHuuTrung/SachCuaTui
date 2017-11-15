package com.huutrung.sachcuatui.Book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.huutrung.sachcuatui.R;

/**
 * Created by Admin on 11/1/2017.
 */


public class CustomAdapter extends BaseAdapter {
    String[] result;
    Context context;
    int [] imgId;
    public CustomAdapter (Context context, String[] result, int[] imgId){
        this.context = context;
        this.result = result;
        this.imgId = imgId;
    }
    //Trả về độ dài của mảng chứa nội dung list Item
    @Override
    public int getCount() {
        return result.length;
    }
    //Trả về vị trí của mảng chưa nội dung List Item
    @Override
    public Object getItem(int position) {
        return position;
    }
    //trả về vị trí của mảng image list item
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_for_navigation, parent, false);

        TextView tvNoiDung = (TextView) rowView.findViewById(R.id.tvNoiDung);
        ImageView imgAvatar = (ImageView) rowView.findViewById(R.id.imgAvatar);

        //lấy nội dung của Item ra để thiết lập nội dung item cho đúng
        tvNoiDung.setText(result[position]);
        //lấy ImageView ra để thiết lập hình ảnh cho đúng
        imgAvatar.setImageResource(imgId[position]);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"You clicked "+result[position],Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
        // return convertView;
    }
}

