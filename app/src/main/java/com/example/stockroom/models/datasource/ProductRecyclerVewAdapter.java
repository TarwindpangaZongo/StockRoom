package com.example.stockroom.models.datasource;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.stockroom.R;

import java.util.ArrayList;

public class ProductRecyclerVewAdapter extends RecyclerView.Adapter<ProductRecyclerVewAdapter.ViewHolder> {
    //List of animal that will be populated into the recycler view
    ArrayList<Product> productArrayList;

    //Constructor for the Adapter
    public ProductRecyclerVewAdapter(ArrayList<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }

    @NonNull
    @Override
    public ProductRecyclerVewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.product_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRecyclerVewAdapter.ViewHolder viewHolder, final int i) {
        //Get the item's information which we wish to populate for that viewholder
        Product currentProductBeingPopulated = productArrayList.get(i);
        //use the passed viewholder to access the items view and populate
        viewHolder.tvId.setText(currentProductBeingPopulated.getId());
        viewHolder.tvName.setText(currentProductBeingPopulated.getName());
        viewHolder.tvCount.setText(currentProductBeingPopulated.getInventoryCount());
        viewHolder.tvDescription.setText(currentProductBeingPopulated.getDescription());
        //viewHolder.ivImage.setText(currentAnimalBeingPopulated.getImageUrl());
        Log.d("TAG", "onBindViewHolder: item being rendered = " + i);
    }

    @Override
    public int getItemCount() {
        return  productArrayList.size();
    }
    //Add to list, notify the adapter that the info in the list has changed
    public void addAnimalToList(Product product) {
        productArrayList.add(product);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvId;
        TextView tvName;
        TextView tvCount;
        TextView tvDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvName = itemView.findViewById(R.id.tvName);
            tvCount = itemView.findViewById(R.id.tvCount);
            tvDescription = itemView.findViewById(R.id.tvDescription);

        }
    }


}
