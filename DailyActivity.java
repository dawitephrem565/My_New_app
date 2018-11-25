package com.example.filenber.my_new_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by filenber on 08/11/2018.
 */

public class DailyActivity extends AppCompatActivity {
    ImageView imageView;
    TextView Title,Desc,Price;
    FloatingActionButton book;
    BottomSheetBehavior bottomSheetBehavior;
    TextView orderPrice,value;
    ImageView cartImage;
    Button minus,plus;
    EditText orderTitle,Username,phone;
    Button order;
    int counter;
    List<Order_list_items> order_list_items;
    Main_Page_listIteam ring_item_list;
    ConstraintLayout coordinatorLayout;
    private static final String URL_PRODUCTS = "http://therobustacoffee.com/admin/mobile_insertdata.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dailyactivity);
        book=(FloatingActionButton)findViewById(R.id.dailybookorder);
        // Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
       // coordinatorLayout=(ConstraintLayout) findViewById(R.id.bottomsheet);
       // bottomSheetBehavior = BottomSheetBehavior.from(coordinatorLayout);
        imageView=(ImageView) findViewById(R.id.dailyprofileimage);
        Title = (TextView)findViewById(R.id.dailytitleviewactivity);
        Price=(TextView)findViewById(R.id.dailypriceviewactivity);
        RequestOptions requestOptions= new RequestOptions().placeholder(R.drawable.logo).error(R.drawable.ic_refresh_black_24dp);

        String image_url = getIntent().getExtras().getString("gimages");
        // set image using Glide
        Glide.with(DailyActivity.this).load(image_url).apply(requestOptions).into(imageView);
        //String image_url = getIntent().getExtras().getString("anime_img") ;
        Title.setText(getIntent().getStringExtra("gname"));
        Price.setText(getIntent().getStringExtra("price"));
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Intent intent = new Intent(View_Activuty.this,Order_Activity.class);
                intent.putExtra("profileimage",image_url);
                intent.putExtra("Title",Title.getText().toString());
                intent.putExtra("price",Price.getText().toString());
                startActivity(intent);*/
shareContent();
              //  bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });}

    private Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null) {
            bgDrawable.draw(canvas);
        }   else{
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;

    }
        private void shareContent(){

            Bitmap bitmap =getBitmapFromView(imageView);
            try {
                File file = new File(this.getExternalCacheDir(),"logicchip.png");
                FileOutputStream fOut = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                fOut.flush();
                fOut.close();
                file.setReadable(true, false);
                final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                String value = "Robusta Coffe " + Title.getText() + " ልጋብዝህ/ሽ";
                intent.putExtra(Intent.EXTRA_TEXT, value);
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                intent.setType("image/png");
                startActivity(Intent.createChooser(intent, "Share image via"));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

}




