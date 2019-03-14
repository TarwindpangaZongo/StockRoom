package com.example.stockroom.models.datasource;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    RecyclerView recyclerView;
    ProductDatabaseHelper productDatabaseHelper;
    ProductRecyclerVewAdapter productRecyclerVewAdapter;

    Product product;
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent != null) {
            Bundle bundle = intent.getExtras();
            product = bundle.getParcelable("product");
            if(product != null){
                addToRecycler(product);
                addToDatabase(product);
            }
        }

    }

    private void addToRecycler(Product product) {

    }

    private void addToDatabase(Product product) {
    }

}
