package com.example.filenber.my_new_app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by filenber on 31/10/2018.
 */

public class Order_detail_adapter extends RecyclerView.Adapter<Order_detail_adapter.OrderViewAdapter> {
    List<Order_Detail_listitems> order_detail_listitems;
    Context context;

    public Order_detail_adapter(List<Order_Detail_listitems> order_detail_listitems, Context context) {
        this.order_detail_listitems = order_detail_listitems;
        this.context = context;
    }

    @Override
    public Order_detail_adapter.OrderViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.order_detail_list,null);
        return new OrderViewAdapter(view);

    }

    @Override
    public void onBindViewHolder(Order_detail_adapter.OrderViewAdapter holder, int position) {
        final Order_Detail_listitems order_list_items =order_detail_listitems.get(position);
        RequestOptions requestOptions= new RequestOptions().placeholder(R.drawable.logo).error(R.drawable.ic_refresh_black_24dp);

        String ip = "http://192.168.137.1/hotel/admin/gupload";
        //String addip = ip+order_detail_listitems.get(position).getGimages();
        //Glide.with(context).load(addip).into(holder.gimage);
        Glide.with(context).load(order_list_items.getGimages()).into(holder.gimage);

        //holder.gid.setText(main_page_listIteam.getGid());
        holder.gname.setText(order_list_items.getGname());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("gid",order_list_items.getGid());
                intent.putExtra("aid",order_list_items.getAid());
                intent.putExtra("gname",order_list_items.getGname());
                intent.putExtra("gimages",order_list_items.getGimages());
                intent.putExtra("ging",order_list_items.getDesc());
                intent.putExtra("date",order_list_items.getDate());
                intent.putExtra("status",order_list_items.getStatus());
                intent.putExtra("price",order_list_items.getPrice());
                intent.setClass(context,View_Activuty.class);
                context.startActivity(intent);
            }
        });
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return order_detail_listitems.size();
    }

    public class OrderViewAdapter extends RecyclerView.ViewHolder {
        TextView gid,aid,gname,date,status,price;
        Button fav , book , share;
        ImageView gimage;
        public OrderViewAdapter(View view) {
            super(view);

             gname = (TextView)view.findViewById(R.id.orderdetailtitle);
             book=(Button)view.findViewById(R.id.orderdetailbook);
             share=(Button)view.findViewById(R.id.orderdetailshare);
             gimage=(ImageView)view.findViewById(R.id.orderdetailmainimage);
        }
    }
}
