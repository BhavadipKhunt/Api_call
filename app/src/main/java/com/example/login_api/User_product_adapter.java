package com.example.login_api;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.login_api.DataModels.Productdatum;


import java.util.List;

public class User_product_adapter extends RecyclerView.Adapter<User_product_adapter.Userholder> {
    Context context;
    List<Productdatum> productdata;




    public User_product_adapter(Context context, List<Productdatum> productdata) {
        this.productdata=productdata;
        this.context=context;
    }


    @NonNull
    @Override
    public Userholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.product_show_layout,parent,false);
        Userholder userholder=new Userholder(view);
        return userholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Userholder holder, int position) {
        holder.pname.setText(""+productdata.get(position).getPname());
        holder.pdec.setText(""+productdata.get(position).getDescription());
        holder.price.setText(""+productdata.get(position).getPrice());
        Glide.with(context).load("https://bhavadipandroid.000webhostapp.com/android/"+productdata.get(position).getImage()).into(holder.imageView);
        Log.d("GGG", "onBindViewHolder: "+productdata.get(position).getImage());
    }



    @Override
    public int getItemCount() {
        return productdata.size();
    }

    public class Userholder extends RecyclerView.ViewHolder {
        TextView pname,pdec,price;
        ImageView imageView;
        public Userholder(@NonNull View itemView) {
            super(itemView);
            pname=itemView.findViewById(R.id.product_show_name);
            pdec=itemView.findViewById(R.id.product_show_desc);
            price=itemView.findViewById(R.id.product_show_price);
            imageView=itemView.findViewById(R.id.product_show_image);
        }
    }
}
