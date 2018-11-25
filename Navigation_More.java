package com.example.filenber.my_new_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Navigation_More extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPageradapter viewPageradapter;

    SwipeRefreshLayout swipeRefreshLayout;
    SharedPreferences sharedPreferences;
    //private static final String URL_PRODUCTS = "http://192.168.0.110/Apple.php";
    String URL_username="http://192.168.137.1/Dailyenbla/Apple.php";
    float d ;
    //Weedding_Activity weedding_activity;
    @SuppressLint("NewApi")

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_navigation__more);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Robusta Coffee");
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swiprefersh);


      //toolbar.ele
        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewPager = (ViewPager)findViewById(R.id.viewpage_id);
      //  save_User_data();


       /* tabLayout.getTabAt(0).setIcon(R.drawable.weeding);
        tabLayout.getTabAt(1).setIcon(R.drawable.ring);
        tabLayout.getTabAt(2).setIcon(R.drawable.family);*/

        viewPageradapter = new ViewPageradapter(getSupportFragmentManager());
        viewPageradapter.AddFragment(new Main_Page(), "Daily");
        viewPageradapter.AddFragment(new Order_Page(),"Orderable ");
        final List<Main_Page_listIteam> productList;
        viewPager.setAdapter(viewPageradapter);
        tabLayout.setupWithViewPager(viewPager);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
Main_Page activity = new Main_Page();


            }


        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation__more, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //SharedPrefManager.getInstance(this).logout();
            //finish();
            startActivity(new Intent(this, MainActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
           Intent intent = new Intent(Navigation_More.this,Comment.class);
           startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(Navigation_More.this,Navigation_More.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
     Intent intent = new Intent(Navigation_More.this,Robusta.class);
     startActivity(intent);
        }  else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            Intent intent = new Intent(Navigation_More.this,Developers.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
