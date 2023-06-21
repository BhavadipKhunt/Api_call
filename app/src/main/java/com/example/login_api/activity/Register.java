package com.example.login_api.activity;

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
import android.widget.Toast;

import com.example.login_api.DataModels.RegisterData;
import com.example.login_api.DataModels.retro_class;
import com.example.login_api.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
EditText nametxt,emailtxt,passwordtxt,conformpasstxt;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nametxt=findViewById(R.id.register_name);
        emailtxt=findViewById(R.id.register_email);
        passwordtxt=findViewById(R.id.register_password);
        conformpasstxt=findViewById(R.id.register_conformpassword);
        button=findViewById(R.id.regi_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (netwotk()) {
                    String name, email, password, conpass;
                    name = nametxt.getText().toString();
                    email = emailtxt.getText().toString();
                    password = passwordtxt.getText().toString();
                    conpass = conformpasstxt.getText().toString();
                    if (nametxt.getText().toString().equals(" ")) {
                        nametxt.setError("Name can't empty!");
                    } else if (emailtxt.getText().toString().equals(" ")) {
                        emailtxt.setError("Enter Email");
                    } else if (passwordtxt.getText().toString().equals(" ")) {
                        passwordtxt.setError("Enter passeord");

                    } else if (conformpasstxt.getText().toString().equals(" ")) {
                        conformpasstxt.setError("Enter confirmpassword");
                    } else {
                        if (!password.equals(conpass)) {
                            conformpasstxt.setError("please enter same password");
                        }
                        else {

                            retro_class.callapi().REGISTER_DATA_CALL(name, email, password).enqueue(new Callback<RegisterData>() {
                                @Override
                                public void onResponse(Call<RegisterData> call, Response<RegisterData> response) {
                                    Log.d("EEE", "onResponse: "+response.body().toString());
                                    if (response.body().getConnection() == 1) {
                                        if (response.body().getResult() == 1) {
                                            Intent intent = new Intent(Register.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        } else if (response.body().getResult() == 2) {
                                            Toast.makeText(Register.this, "Already Registered", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(Register.this, "User not Registered", Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Toast.makeText(Register.this, "Something went Wrong", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<RegisterData> call, Throwable t) {
                                    Log.e("EEE", "onFailure: "+t.getLocalizedMessage());
                                }
                            });
                        }
                    }
                }
                else {

                }
            }
        });
    }

    private boolean netwotk() {
        ConnectivityManager manager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=manager.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }
}