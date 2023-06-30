package com.example.login_api.activity;

import static com.example.login_api.activity.SplashScreen.editor;
import static com.example.login_api.activity.SplashScreen.preferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.login_api.DataModels.ProductData;
import com.example.login_api.DataModels.retro_class;
import com.example.login_api.R;
import com.example.login_api.fragment.Show_all_product;
import com.example.login_api.fragment.home_fragment;
import com.google.android.material.navigation.NavigationView;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Ecommerce_activity extends AppCompatActivity {
NavigationView navigationView;
DrawerLayout drawerLayout;
Toolbar toolbar;
Button add;
EditText pname,pdecs,pprice;
ImageView imageView,headerImg;
String image,imgPath;
String imgName;
int cnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecommerce);
        navigationView=findViewById(R.id.navigation);
        drawerLayout=findViewById(R.id.Drawer);
        toolbar=findViewById(R.id.toolbar);
        TextView nametext,emailtext;
        View headerView = navigationView.getHeaderView(0);

        nametext=headerView.findViewById(R.id.hader_name);
        emailtext=headerView.findViewById(R.id.hader_email);
        String uname,uemail;
        int uid;
        uid= preferences.getInt("uid",0);
        uname=preferences.getString("name","");
        uemail=preferences.getString("email","");
        Log.d("TTT", "onCreate: "+uname);
        Log.d("TTT", "onCreate: "+uemail);
        headerImg=headerView.findViewById(R.id.HeaderImage);
        nametext.setText(""+uname);
        emailtext.setText(""+uemail);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        addfragment(new home_fragment());
        headerImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(Ecommerce_activity.this);
                cnt=1;

            }
        });
        imgName=imgName+new Random().nextInt(10000)+".jpg";
        Bitmap bitmap= ((BitmapDrawable)headerImg.getDrawable()).getBitmap();
        imgPath=saveToInternalStorage(bitmap);
                            imgPath=imgPath+"/"+imgName;
                            loadImageFromStorage(imgPath);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.home_menu)
                {
                    addfragment(new home_fragment());
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
                if (item.getItemId()==R.id.logout)
                {
                    AlertDialog.Builder builder=new AlertDialog.Builder(Ecommerce_activity.this);
                    builder.setTitle("Logout");
                    builder.setMessage("Are you sure ?");
                    builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent=new Intent(Ecommerce_activity.this, MainActivity.class);
                            startActivity(intent);
                            editor.putBoolean("isLogin",false);
                            editor.commit();
                            finish();
                        }
                    });
                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.show();
                }
                if (item.getItemId()==R.id.add_product)
                {
                    Dialog dialog=new Dialog(Ecommerce_activity.this);
                    dialog.setContentView(R.layout.dailog_layout);
                    add=dialog.findViewById(R.id.Add_button);
                    pname=dialog.findViewById(R.id.product_name);
                    pdecs=dialog.findViewById(R.id.product_desc);
                    pprice=dialog.findViewById(R.id.product_price);
                    imageView=dialog.findViewById(R.id.product_image);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CropImage.activity()
                                    .setGuidelines(CropImageView.Guidelines.ON)
                                    .start(Ecommerce_activity.this);
                            cnt=2;
                        }
                    });
                    add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view)
                        {
                            String name,descri,price;
                            name=pname.getText().toString();
                            descri=pdecs.getText().toString();
                            price=pprice.getText().toString();
                            int r=new Random().nextInt(100);
                            Bitmap bitmap= ((BitmapDrawable)imageView.getDrawable()).getBitmap();
//                            imgName=imgName+new Random().nextInt(10000)+".jpg";
//                            imgPath=saveToInternalStorage(bitmap);
//                            imgPath=imgPath+"/"+imgName;
//                            loadImageFromStorage(imgPath);
                            ByteArrayOutputStream bos=new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG,30,bos);
                            byte[] byteArray = bos.toByteArray();
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                image= Base64.getEncoder().encodeToString(byteArray);
                            }
                            retro_class.callapi().PRODUCT_DATA_CALL(uid,name,price,descri,image).enqueue(new Callback<ProductData>() {
                                @Override
                                public void onResponse(Call<ProductData> call, Response<ProductData> response) {
                                    Log.d("ttt", "onResponse: "+response.body().toString());
                                    if (response.body().getConnection() == 1)
                                    {
                                        if (response.body().getProductaddd() == 1)
                                        {
                                            Toast.makeText(Ecommerce_activity.this, "Product Add", Toast.LENGTH_LONG).show();
                                        }
                                        else
                                        {
                                            Toast.makeText(Ecommerce_activity.this, "Product Not Add", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    else {
                                        Toast.makeText(Ecommerce_activity.this, "Somthing Went Wrong", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ProductData> call, Throwable t) {
                                    Log.e("ttt", "onFailure: "+t.getLocalizedMessage() );
                                }
                            });
                        dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
                if (item.getItemId()==R.id.show_all)
                {
                    addfragment(new Show_all_product());
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
                return true;
            }
        });
    }

    private void addfragment(Fragment fragment) {
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction= manager.beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                Uri resultUri = result.getUri();

               if(cnt==1)
               {
                   headerImg.setImageURI(resultUri);
               }
               else if(cnt==2)
               {
                   imageView.setImageURI(resultUri);
               }

            }

            else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
    private String saveToInternalStorage(Bitmap bitmapImage){
        //ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory=getApplicationContext().getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir

        File mypath=new File(directory,imgName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }
    private void loadImageFromStorage(String path)
    {

        try {
            File f=new File(path);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));

            headerImg.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}