package com.example.filenber.my_new_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import static java.security.AccessController.getContext;

public class View_Activuty extends AppCompatActivity {
ImageView imageView;
 TextView Title,Desc,Price;
 FloatingActionButton book;
BottomSheetBehavior bottomSheetBehavior;
    TextView orderPrice,value;
    ImageView cartImage;
    Button minus,plus;
    EditText orderTitle,Username,phone;
    Button order;
    ProgressBar progressBar;
    int counter;
    Main_Page_listIteam ring_item_list;
    ConstraintLayout coordinatorLayout;
    private static final String URL_PRODUCTS = "http://therobustacoffee.com/admin/mobile_insertdata.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__activuty);
        book=(FloatingActionButton)findViewById(R.id.bookorder);
       // Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        coordinatorLayout=(ConstraintLayout) findViewById(R.id.bottomsheet);
bottomSheetBehavior = BottomSheetBehavior.from(coordinatorLayout);
progressBar = (ProgressBar)findViewById(R.id.progressbar) ;
  progressBar.setVisibility(View.INVISIBLE);
        imageView=(ImageView) findViewById(R.id.profileimage);
        Title = (TextView)findViewById(R.id.titleviewactivity);
        Desc=(TextView)findViewById(R.id.detail);
        Price=(TextView)findViewById(R.id.priceviewactivity);
        RequestOptions requestOptions= new RequestOptions().placeholder(R.drawable.logo).error(R.drawable.ic_refresh_black_24dp);

         String image_url = getIntent().getExtras().getString("gimages");
        // set image using Glide
        Glide.with(View_Activuty.this).load(image_url).apply(requestOptions).into(imageView);
        //String image_url = getIntent().getExtras().getString("anime_img") ;
        Title.setText(getIntent().getStringExtra("gname"));
        Desc.setText(getIntent().getStringExtra("ging"));
        Price.setText(getIntent().getStringExtra("price"));
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(View_Activuty.this,Order_Activity.class);
                intent.putExtra("profileimage",image_url);
                intent.putExtra("Title",Title.getText().toString());
                intent.putExtra("price",Price.getText().toString());
                startActivity(intent);*/

                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });


       orderTitle = (EditText)findViewById(R.id.Detail_Type);
        Price = (TextView)findViewById(R.id.textView6);
        value = (TextView)findViewById(R.id.value);
        Username=(EditText)findViewById(R.id.editText);
        phone=(EditText)findViewById(R.id.editText2);
        //cartImage=(ImageView)findViewById(R.id.imageView3);
        order=(Button)findViewById(R.id.button);
        minus=(Button) findViewById(R.id.minus);
        plus=(Button) findViewById(R.id.plus);
counter=0;
Price.setText( getIntent().getStringExtra("price"));

value.setText(Integer.toString(counter));

         plus.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 counter++;

              String pricevalue=   Price.getText().toString();
              int pricenumvalue=Integer.parseInt(pricevalue);
                 int mainresult =Integer.parseInt(getIntent().getStringExtra("price"));
                 int val = mainresult * counter;
                 Price.setText(Integer.toString(val));
                 value.setText(Integer.toString(counter));

             }
         });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--;
                String pricevalue=   Price.getText().toString();

                int pricenumvalue=Integer.parseInt(pricevalue);


                int source =Integer.parseInt(getIntent().getStringExtra("price"));
                int minsvalue=pricenumvalue-source;
                Price.setText(Integer.toString(minsvalue));
                value.setText(Integer.toString(counter));

            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
new recivedata().execute();

        /*
        * Creating a String Request
        * The request type is GET defined by first parameter
        * The URL is defined in the second parameter
        * Then we have a Response Listener and a Error Listener
        * In response listener we will get the JSON response as a String
*/}

        });
    }
 class  recivedata extends AsyncTask<String,String,String>
 {

     @Override
     protected String doInBackground(String... strings) {

         StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PRODUCTS,
                 new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response) {

                         if(response.toString().equals("got"))
                         {
                             final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(View_Activuty.this).create();
                             alertDialog.setTitle("Success");

                             alertDialog.setMessage("Your Order sent Successfully");

                             alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
                                 @Override
                                 public void onClick(DialogInterface dialog, int which) {

                                 }
                             });
                             alertDialog.show();
                         }
                         else {
                             new Check_internet(View_Activuty.this).execute();
                         }
                     }
                 },
                 new Response.ErrorListener() {
                     @Override
                     public void onErrorResponse(VolleyError error) {
                         //  Toast.makeText(getContext(),"Some thing is error",Toast.LENGTH_LONG);
                         if(error instanceof TimeoutError || error instanceof NoConnectionError)
                         {

                             Toast.makeText(getBaseContext(),"Connection Error Dave",Toast.LENGTH_LONG).show();
                         }
                     }
                 }) {
             @Override
             public Map<String, String> getParams() throws AuthFailureError {
                 Map<String, String> params = new HashMap<>();
                 //params.put("username",Username.getText().toString());

                 params.put("username", Username.getText().toString());
                 params.put("phone", phone.getText().toString());
                 params.put("orderprice", Price.getText().toString());
                 params.put("ordername", getIntent().getStringExtra("gname"));
                 params.put("ordernumber",value.getText().toString());

                 return params;
             }
         };

         //adding our stringrequest to queue
         Volley.newRequestQueue(getBaseContext().getApplicationContext()).add(stringRequest);
         bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);


         return null;
     }


 }
}

