package com.example.login_api.activity;

import static com.example.login_api.activity.SplashScreen.editor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import com.example.login_api.R;
import com.example.login_api.fragment.home_fragment;
import com.google.android.material.navigation.NavigationView;

public class Ecommerce_activity extends AppCompatActivity {
NavigationView navigationView;
DrawerLayout drawerLayout;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecommerce);
        navigationView=findViewById(R.id.navigation);
        drawerLayout=findViewById(R.id.Drawer);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
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
}