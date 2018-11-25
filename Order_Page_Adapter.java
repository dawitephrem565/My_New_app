package com.example.filenber.my_new_app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by filenber on 30/10/2018.
 */

public class Order_Page_Adapter extends RecyclerView.Adapter<Order_Page_Adapter.OrderViewHolder> {
    List<Order_list_items> adapter_order_list_items;
    Context context;
    public Order_Page_Adapter(List<Order_list_items> order_list_items, Context context) {
        this.adapter_order_list_items = order_list_items;
        this.context = context;
    }


    @Override
    public Order_Page_Adapter.OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.order_page_iteamlist, null);
        return new OrderViewHolder(view);

    }

    @Override
    public void onBindViewHolder(Order_Page_Adapter.OrderViewHolder holder, int position) {
        final Order_list_items order_list_items =adapter_order_list_items .get(position);
        RequestOptions requestOptions= new RequestOptions().placeholder(R.drawable.logo).error(R.drawable.ic_refresh_black_24dp);



        Glide.with(context ).load(order_list_items.getImage()).apply(requestOptions).into(holder.MainImage);

        holder.title.setText(order_list_items.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("MainImage",order_list_items.getImage());
                intent.putExtra("OrderTitle",order_list_items.getTitle());
              intent.setClass(context,OrderActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return adapter_order_list_items.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView MainImage;
        public OrderViewHolder(View itemView) {
            super(itemView);
            title=(TextView) itemView.findViewById(R.id.ringdate);
            MainImage=(ImageView)itemView.findViewById(R.id.OrderMainImage);
        }
    }
}
