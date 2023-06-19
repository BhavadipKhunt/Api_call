package com.example.login_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
Button login;
EditText nametext,passwordtext;
TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nametext=findViewById(R.id.login_name);
        passwordtext=findViewById(R.id.login_password);
        login=findViewById(R.id.login_button);
        register=findViewById(R.id.register_text);
        String email=nametext.getText().toString();
        String password=passwordtext.getText().toString();

login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

    }
});
        if (Connectinternet())
        {
            retro_class.callapi().LOGINMODEL_CALL(email,password).enqueue(new Callback<loginmodel>() {
                @Override
                public void onResponse(Call<loginmodel> call, Response<loginmodel> response) {
                }

                @Override
                public void onFailure(Call<loginmodel> call, Throwable t) {

                }
            });
        }
        else
        {

        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, Register.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean Connectinternet() {
        ConnectivityManager manager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=manager.getActiveNetworkInfo();
        return(info!=null && info.isConnected());
    }
}