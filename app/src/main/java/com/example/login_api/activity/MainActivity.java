package com.example.login_api.activity;

import static com.example.login_api.activity.SplashScreen.editor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login_api.DataModels.LoginData;
import com.example.login_api.DataModels.retro_class;
import com.example.login_api.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button login;
    EditText nametext, passwordtext;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nametext = findViewById(R.id.login_name);
        passwordtext = findViewById(R.id.login_password);
        login = findViewById(R.id.login_button);
        register = findViewById(R.id.register_text);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = nametext.getText().toString();
                String password = passwordtext.getText().toString();
                Log.d("EEE", "onClick: email=" + email + "/tPass=" + password);
                retro_class.callapi().LOGINMODEL_CALL(email, password).enqueue(new Callback<LoginData>() {
                    @Override
                    public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                        Log.d("EEE", "onResponse: " + response.body().getUserdata().getId());
                        Log.d("EEE", "onResponse: " + response.body().getResult());
                        if (response.body().getResult() == 1) {
                            editor.putBoolean("isLogin", true);
                            editor.putString("name", response.body().getUserdata().getName());
                            editor.putString("email", response.body().getUserdata().getEmail());
                            editor.putInt("uid", Integer.parseInt(response.body().getUserdata().getId()));

                            editor.commit();
                            Intent intent = new Intent(MainActivity.this, Ecommerce_activity.class);
                            startActivity(intent);
                            finish();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginData> call, Throwable t) {
                        Log.e("EEE", "onFailure: " + t.getLocalizedMessage());
                    }
                });
            }
        });




        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, Register.class);
                startActivity(intent);
                finish();
            }
        });
    }


}