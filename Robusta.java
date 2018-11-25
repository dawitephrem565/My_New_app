package com.example.filenber.my_new_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class Robusta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robusta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toll);
        toolbar.setTitle("Robusta Coffee");
    }
}
