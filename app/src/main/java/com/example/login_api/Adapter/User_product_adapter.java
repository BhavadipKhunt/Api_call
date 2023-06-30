package com.example.login_api.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.login_api.DataModels.DeletProduct;
import com.example.login_api.DataModels.Productdatum;
import com.example.login_api.DataModels.retro_class;
import com.example.login_api.GetPosition;
import com.example.login_api.R;
import com.theartofdev.edmodo.cropper.CropImage;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class
User_product_adapter extends RecyclerView.Adapter<User_product_adapter.Userholder>
{
    Context context;
    List<Productdatum> productdata;
    TextView uname,uprice,udec;
    Button update;
    ImageView uimage;
    GetPosition getPosition;

    public User_product_adapter(Context context, List<Productdatum> productdata, GetPosition getPosition) {
        this.productdata=productdata;
        this.context=context;
        this.getPosition=getPosition;
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
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPosition.onRecyclerItemClick(holder.getAdapterPosition());
            }
        });
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

                            Dialog dialog=new Dialog(context);
                            dialog.setContentView(R.layout.update_layout);
                            uname=dialog.findViewById(R.id.update_product_name);
                            udec=dialog.findViewById(R.id.update_product_desc);
                            uprice=dialog.findViewById(R.id.update_product_price);
                            update=dialog.findViewById(R.id.update_button);
                            uimage=dialog.findViewById(R.id.update_imageview);
                            uname.setText(productdata.get(holder.getAdapterPosition()).getPname());
                            uprice.setText(productdata.get(holder.getAdapterPosition()).getPrice());
                            udec.setText(productdata.get(holder.getAdapterPosition()).getDescription());
                            uimage.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    CropImage.activity()
                                            .start(context,new Fragment());
                                }
                            });
                            dialog.show();

                            update.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String name,price,dec,imagename;
                                    int id= Integer.parseInt(productdata.get(holder.getAdapterPosition()).getId());
                                    imagename=productdata.get(holder.getAdapterPosition()).getImage();
                                    name=uname.getText().toString();
                                    price=uprice.getText().toString();
                                    dec=udec.getText().toString();

                                }
                            });
                        }
                        if (menuItem.getItemId()==R.id.delet)
                        {
                            AlertDialog.Builder builder=new AlertDialog.Builder(context);
                            builder.setTitle("DELETE...");
                            builder.setMessage("Are You Sure?");
                            builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
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
                                    dialogInterface.dismiss();
                                }

                            });
                            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getPosition.onRecyclerItemClick(getAdapterPosition());
                }
            });
        }

    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

}
