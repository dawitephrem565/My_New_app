package com.example.filenber.my_new_app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
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
 * Created by filenber on 17/10/2018.
 */

public class Main_Page extends Fragment {
    private static final String URL_PRODUCT = "http://therobustacoffee.com/admin/Apple.php";
    private static final String URL_PRODUCTS = "http://therobustacoffee.com/admin/Apple.php";

Dialog dialog;
    View view;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    List<Main_Page_listIteam> ring_item_list;
    public Main_Page(){

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_recycler,container,false);
        recyclerView =(RecyclerView)view.findViewById(R.id.ring_recycle_view);
       // progressBar =(ProgressBar)view.findViewById(R.id.progreessbar);

        Main_page_adapter adapter = new Main_page_adapter(getContext(),ring_item_list);
        recyclerView.setAdapter(adapter);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        // recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ring_item_list = new ArrayList<>();
   //new Dave().execute();

loadProducts();

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
                                ring_item_list.add(new Main_Page_listIteam(
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
                            Main_page_adapter adapter = new Main_page_adapter(getContext(), ring_item_list);
                            recyclerView.setAdapter(adapter);
                            //Toast.makeText(con,"Displayed",Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            Toast.makeText(getContext(),"Network Error",Toast.LENGTH_LONG);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error instanceof TimeoutError || error instanceof NoConnectionError)
                        {
                            dialog = new Dialog(getContext());
                           dialog.setContentView(R.layout.dialog);
                            Button btn = (Button)dialog.findViewById(R.id.retry);
                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                   
                                }
                            });
                           dialog.show();
                        }// Toast.makeText(getContext(),"Some thing is error",Toast.LENGTH_LONG);
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

    }


        }






