package com.zsg.address;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zzc on 2015/8/28.
 */
public class AddressAdapter extends BaseAdapter{

    ArrayList<AddressBook> data;
    Context context;
    int colors[]={Color.rgb(69,233,241),Color.rgb(253,40,72),Color.rgb(237,180,27)
    ,Color.rgb(52,221,72),Color.rgb(154,218,179)};
    //加载xml布局文件的系统服务
    LayoutInflater inflater;
    public AddressAdapter(ArrayList data, Context context){
        this.data=data;
        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return data.get(i).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.item_list,parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        AddressBook book=data.get(position);
        Random random=new Random();
        int a=random.nextInt(colors.length);
        holder.xing.setBackgroundColor(colors[a]);
        holder.xing.setText(book.getName());
        holder.name.setText(book.getName());



        return convertView;
    }

    class ViewHolder{
        @Bind(R.id.id_xing)
        TextView xing;
        @Bind(R.id.id_name)
        TextView name;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }


    }
}
