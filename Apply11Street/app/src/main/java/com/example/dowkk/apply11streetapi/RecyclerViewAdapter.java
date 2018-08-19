package com.example.dowkk.apply11streetapi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Product> mProducts = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<Product> products) {
        this.mProducts = products;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        Glide.with(mContext)
                .asBitmap()
                .load(mProducts.get(position).getProductImage())
                .into(holder.product_img2);
        holder.product_productName2.setText(mProducts.get(position).getProductName());
        holder.product_price2.setText(mProducts.get(position).getProductPrice());
        holder.product_seller2.setText(mProducts.get(position).getProductCode());

        holder.parentLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "clicked on : " + mProducts.get(position).getProductName());
                Toast.makeText(mContext, mProducts.get(position).getProductName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView product_img2;
        TextView product_productName2,product_price2,product_seller2;
        RelativeLayout parentLayout2;

        public ViewHolder(View itemView) {
            super(itemView);
            parentLayout2 = itemView.findViewById(R.id.parent_layout2);
            product_img2 = itemView.findViewById(R.id.product_img2);
            product_productName2 = itemView.findViewById(R.id.product_productName2);
            product_price2 = itemView.findViewById(R.id.product_price2);
            product_seller2 = itemView.findViewById(R.id.product_seller2);

        }
    }
}
