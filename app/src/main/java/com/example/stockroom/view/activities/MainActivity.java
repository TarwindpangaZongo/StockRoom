package com.example.stockroom.view.activities;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.stockroom.R;
import com.example.stockroom.models.datasource.MyBroadcastReceiver;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    public static final String SEND_BROADCAST = "example.stockroom.view.activities.SEND_BROADCAST";
    MyBroadcastReceiver myBroadcastReceiver;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myBroadcastReceiver = new MyBroadcastReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction(SEND_BROADCAST);

        //Bind RecyclerView
        recyclerView = findViewById(R.id.rvRecyclerView);
    }
}
