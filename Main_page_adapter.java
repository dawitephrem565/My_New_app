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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by filenber on 17/10/2018.
 */

public class Main_page_adapter extends RecyclerView.Adapter<Main_page_adapter.Myholder> {
     List<Main_Page_listIteam> listIteams;
    //Main_Page_listIteam main_page_listIteam;
    View view;
     Context context;
    public Main_page_adapter(Context context , List<Main_Page_listIteam> listIteams)
    {
        this.context=context;
        this.listIteams = listIteams;

    }

    @Override
    public Main_page_adapter.Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.main_page,null);
        return new Myholder(view);

    }

    @Override
    public void onBindViewHolder(final Main_page_adapter.Myholder holder, int position) {
      final Main_Page_listIteam  main_page_listIteam = listIteams.get(position);
       // RequestOptions requestOptions= new RequestOptions().placeholder(R.drawable.logo).error(R.drawable.ic_refresh_black_24dp);

        //Glide.with(context).load(addip).into(holder.gimage);
        Glide.with(context).load(main_page_listIteam.getGimages()).into(holder.gimage);

        //holder.gid.setText(main_page_listIteam.getGid());
        holder.aid.setText(main_page_listIteam.getAid());
        holder.gname.setText(main_page_listIteam.getGname());
        holder.date.setText(main_page_listIteam.getDate());
        holder.status.setText(main_page_listIteam.getStatus());
        holder.price.setText(main_page_listIteam.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

     //   Glide.with(context).load(main_page_listIteam.getGimages()).into(holder.gimage);
                intent.putExtra("gid",main_page_listIteam.getGid());
                intent.putExtra("aid",main_page_listIteam.getAid());
                intent.putExtra("gname",main_page_listIteam.getGname());
                intent.putExtra("gimages",main_page_listIteam.getGimages());
                intent.putExtra("date",main_page_listIteam.getDate());
                intent.putExtra("status",main_page_listIteam.getStatus());
                intent.putExtra("price",main_page_listIteam.getPrice());
                intent.setClass(context,DailyActivity.class);
                context.startActivity(intent);

            }
        });
        holder.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("gid",main_page_listIteam.getGid());
                intent.putExtra("aid",main_page_listIteam.getAid());
                intent.putExtra("gname",main_page_listIteam.getGimages());
                intent.putExtra("gimages",main_page_listIteam.getDate());
                intent.putExtra("date",main_page_listIteam.getDate());
                intent.putExtra("status",main_page_listIteam.getStatus());
                intent.putExtra("price",main_page_listIteam.getPrice());

            }
        });

    }

    @Override
    public int getItemCount() {
              return listIteams.size();
    }
    public class Myholder extends RecyclerView.ViewHolder {
        TextView gid,aid,gname,date,status,price;
        Button fav , book , share;
        ImageView gimage;

        public Myholder(View view) {
            super(view);

            fav = (Button)view.findViewById(R.id.button6);
            book = (Button)view.findViewById(R.id.button5);
            share = (Button)view.findViewById(R.id.button4);
            gid=(TextView)view.findViewById(R.id.gid);
            aid=(TextView)view.findViewById(R.id.aid);
            gname=(TextView)view.findViewById(R.id.gname);
            gimage=(ImageView)view.findViewById(R.id.gimage);
            date=(TextView)view.findViewById(R.id.Date);
            status=(TextView)view.findViewById(R.id.status);
            price=(TextView)view.findViewById(R.id.price);

          //  fovarite=(TextView)view.findViewById(R.id.textView3);


        }
    }
}
