package com.example.filenber.my_new_app;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Comment extends AppCompatActivity {
  EditText username;
  EditText comment;
  Button send;
    final String URL_PRODUCTS = "http://therobustacoffee.com/admin/comment.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);


        comment = (EditText) findViewById(R.id.editText3);
        username = (EditText) findViewById(R.id.editText4);
        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new recivedata().execute();
            }
        });


    }

    class recivedata extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PRODUCTS,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if (response.toString().equals("got")) {
                                final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(Comment.this).create();
                                alertDialog.setTitle("Success");

                                alertDialog.setMessage("Your Order sent Successfully");

                                alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                alertDialog.show();
                            } else {
                                new Check_internet(Comment.this).execute();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //  Toast.makeText(getContext(),"Some thing is error",Toast.LENGTH_LONG);
                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                                Toast.makeText(getBaseContext(), "Connection Error Dave", Toast.LENGTH_LONG).show();
                            }
                        }
                    }) {
                @Override
                public Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    //params.put("username",Username.getText().toString());

                    params.put("username", username.getText().toString());
                    params.put("comment", username.getText().toString());


                    return params;
                }
            };

            //adding our stringrequest to queue
            Volley.newRequestQueue(getBaseContext().getApplicationContext()).add(stringRequest);
            //bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);


            return null;
        }}
}
