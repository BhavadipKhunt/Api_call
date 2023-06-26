package com.example.login_api.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.login_api.R;

public class SplashScreen extends AppCompatActivity {

    private Runnable runnable;
    public static SharedPreferences preferences;
    public static SharedPreferences.Editor editor;
    boolean isLogin=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        preferences=getSharedPreferences("myPref",MODE_PRIVATE);
        editor=preferences.edit();

        isLogin=preferences.getBoolean("isLogin",false);
        Log.e("EEE", "onCreate: "+isLogin );
        if (netwotk()) {
            runnable = new Runnable() {
                @Override
                public void run() {
                    if (isLogin) {
                        Intent intent = new Intent(SplashScreen.this, Ecommerce_activity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Log.e("EEE", "onCreate: " + isLogin);
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            };
            new Handler().postDelayed(runnable, 5000);
        }
        else
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(SplashScreen.this);
            builder.setTitle("Alert...");
            builder.setMessage("Please Check Your Internet Connection");
            builder.setPositiveButton("TurnOn", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (netwotk()) {
                        runnable = new Runnable() {
                            @Override
                            public void run() {
                                if (isLogin) {
                                    Intent intent = new Intent(SplashScreen.this, Ecommerce_activity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Log.e("EEE", "onCreate: " + isLogin);
                                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        };
                        new Handler().postDelayed(runnable, 5000);
                    }
                }
            });
            builder.show();
        }
    }
    private boolean netwotk() {
        ConnectivityManager manager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=manager.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }
}