package com.example.filenber.my_new_app;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
 * Created by filenber on 30/10/2018.
 */

public class Order_Page extends android.support.v4.app.Fragment {
    private static final String URL_PRODUCTS = "http://therobustacoffee.com/admin/mobile_album.php";

    View view;
    RecyclerView recyclerView;
    List<Order_list_items> order_list_items;
    public Order_Page(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.order_page_recycleview,container,false);
        recyclerView =(RecyclerView)view.findViewById(R.id.orderrecycleview);
        Order_Page_Adapter adapter = new Order_Page_Adapter(order_list_items,getContext());
        recyclerView.setAdapter(adapter);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        // recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        order_list_items = new ArrayList<>();

 new Dave().execute();


    }
    class Dave extends AsyncTask<String,String,Void>
    {
        @Override
        protected Void doInBackground(String... strings) {
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
                                    order_list_items.add(new Order_list_items(

                                            product.getInt("albumid"),
                                            product.getString("name"),
                                            product.getString("adesc"),
                                            product.getString("status"),
                                            product.getString("date"),
                                            product.getString("image"))
                                    );
                                }

                                //creating adapter object and setting it to recyclerview
                                Order_Page_Adapter adapter = new Order_Page_Adapter(order_list_items,getContext());
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
                            // Toast.makeText(getContext(),"Some thing is error",Toast.LENGTH_LONG);
                        }
                    }) {
                @Override
                public Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    //params.put("username",Username.getText().toString());

                    //params.put("user", SharedPrefManager.getInstance(getContext()).getUsername());;
                    return params;
                }
            } ;

            //adding our stringrequest to queue
            Volley.newRequestQueue(getActivity().getApplicationContext()).add(stringRequest);
            return null;
        }


    }
    }
   /* public  void loadProducts() {

        /*
        * Creating a String Request
        * The request type is GET defined by first parameter
        * The URL is defined in the second parameter
        * Then we have a Response Listener and a Error Listener
        * In response listener we will get the JSON response as a String

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
                                order_list_items.add(new Order_list_items(

                                                product.getInt("albumid"),
                                                product.getString("name"),
                                                product.getString("adesc"),
                                                product.getString("status"),
                                                product.getString("date"),
                                                product.getString("image"))
                                );
                            }

                            //creating adapter object and setting it to recyclerview
                            Order_Page_Adapter adapter = new Order_Page_Adapter(order_list_items,getContext());
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
                        Toast.makeText(getContext(),"Some thing is error",Toast.LENGTH_LONG);
                    }
                }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //params.put("username",Username.getText().toString());

                //params.put("user", SharedPrefManager.getInstance(getContext()).getUsername());;
                return params;
            }
        } ;

        //adding our stringrequest to queue
        Volley.newRequestQueue(getActivity().getApplicationContext()).add(stringRequest);

    }*/

