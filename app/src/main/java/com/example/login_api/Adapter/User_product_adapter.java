package com.example.login_api.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.login_api.DataModels.DeletProduct;
import com.example.login_api.DataModels.Productdatum;
import com.example.login_api.DataModels.retro_class;
import com.example.login_api.R;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class
User_product_adapter extends RecyclerView.Adapter<User_product_adapter.Userholder> {
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
        holder.relativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu popupMenu=new PopupMenu(context,holder.relativeLayout);
                popupMenu.getMenuInflater().inflate(R.menu.up_de,popupMenu.getMenu());
                popupMenu.setGravity(Gravity.END);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId()==R.id.Update)
                        {

                        }
                        if (menuItem.getItemId()==R.id.delet)
                        {
                            retro_class.callapi().DELET_PRODUCT_CALL(Integer.valueOf(productdata.get(holder.getAdapterPosition()).getId())).enqueue(new Callback<DeletProduct>() {
                                @Override
                                public void onResponse(Call<DeletProduct> call, Response<DeletProduct> response) {
                                    if (response.body().getConnection()==1)
                                    {
                                        if (response.body().getResult()==1)
                                        {
                                            Toast.makeText(context, "Product Deleted", Toast.LENGTH_LONG).show();
                                        }
                                        else {
                                            Toast.makeText(context, "Product Not Deleted", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    else
                                    {
                                        Toast.makeText(context, "Somthin Went Wrong", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<DeletProduct> call, Throwable t) {

                                }
                            });
                        }
                        return true;
                    }
                });
                popupMenu.show();
                return true;
            }
        });
    }



    @Override
    public int getItemCount() {
        return productdata.size();
    }

    public class Userholder extends RecyclerView.ViewHolder {
        TextView pname,pdec,price;
        ImageView imageView;
        RelativeLayout relativeLayout;
        public Userholder(@NonNull View itemView) {
            super(itemView);
            pname=itemView.findViewById(R.id.product_show_name);
            pdec=itemView.findViewById(R.id.product_show_desc);
            price=itemView.findViewById(R.id.product_show_price);
            imageView=itemView.findViewById(R.id.product_show_image);
            relativeLayout=itemView.findViewById(R.id.Reletiv_show);
        }
    }
}
