package com.example.filenber.my_new_app;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by filenber on 31/10/2018.
 */

public class OrderActivity extends AppCompatActivity {
    private static final String URL_PRODUCTS = "http://therobustacoffee.com/admin/mobile_orderlist.php";
Context context;
    View view;
    RecyclerView recyclerView;
    List<Order_Detail_listitems> order_detail_listitems;
    Order_detail_adapter order_detail_adapter;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detail_recycleview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView =(RecyclerView)findViewById(R.id.orderdetailrecycle);
       // Order_detail_adapter adapter = new Order_detail_adapter(order_detail_listitems, this);
       //recyclerView.setAdapter(order_detail_adapter);

        order_detail_listitems = new ArrayList<>();
          loadProducts();
        //order_detail_listitems.add(new Order_Detail_listitems(1,"001","002","002","002","002","002"));
        // recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
         order_detail_adapter = new Order_detail_adapter(order_detail_listitems,this);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setAdapter(order_detail_adapter);

       // recyclerView.setAdapter(order_detail_adapter);
    }
    public  void loadProducts() {

        /*
        * Creating a String Request
        * The request type is GET defined by first parameter
        * The URL is defined in the second parameter
        * Then we have a Response Listener and a Error Listener
        * In response listener we will get the JSON response as a String
*/
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //
//                              String val = getArguments().getString("mykey");
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                order_detail_listitems.add(new Order_Detail_listitems(
                                        product.getInt("gid"),
                                        product.getString("aid"),
                                        product.getString("gname"),
                                        product.getString("gimages"),
                                        product.getString("ging"),
                                        product.getString("date"),
                                        product.getString("status"),
                                        product.getString("price")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                           // order_detail_listitems order_detail_listitems=new
                            Order_detail_adapter adapter = new Order_detail_adapter(order_detail_listitems,OrderActivity.this);
                            recyclerView.setAdapter(adapter);
                            //Toast.makeText(con,"Displayed",Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       Toast.makeText(getBaseContext(),"Some thing is error",Toast.LENGTH_LONG);

                    }
                }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //params.put("username",Username.getText().toString());
            String data=getIntent().getStringExtra("gname");
                params.put("user",getIntent().getStringExtra("OrderTitle") );
                return params;
            }
        } ;

        //adding our stringrequest to queue
        Volley.newRequestQueue(getBaseContext().getApplicationContext()).add(stringRequest);

    }
}
