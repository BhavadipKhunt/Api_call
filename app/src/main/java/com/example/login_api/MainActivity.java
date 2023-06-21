package com.example.login_api;

import static com.example.login_api.SplashScreen.editor;

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
                Log.d("EEE", "onClick: email="+email+"/tPass="+password);
                retro_class.callapi().LOGINMODEL_CALL(email, password).enqueue(new Callback<LoginData>() {
                    @Override
                    public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                        Log.d("EEE", "onResponse: " + response.body().getUserdata());
                        Log.d("EEE", "onResponse: " + response.body().getResult());
                        if(response.body().getResult()==1)
                        {
                            editor.putBoolean("isLogin",true);
                            editor.commit();
                        }
                        Toast.makeText(MainActivity.this, "Data=" + response.body(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<LoginData> call, Throwable t) {
                        Log.e("EEE", "onFailure: "+t.getLocalizedMessage());
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

    private boolean Connectinternet() {
        ConnectivityManager manager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=manager.getActiveNetworkInfo();
        return(info!=null && info.isConnected());
    }
}