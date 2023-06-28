package com.example.login_api.fragment;

import static com.example.login_api.activity.SplashScreen.preferences;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;



import com.example.login_api.DataModels.ProductData;
import com.example.login_api.DataModels.Productdatum;
import com.example.login_api.DataModels.UserProduct;
import com.example.login_api.DataModels.retro_class;
import com.example.login_api.R;
import com.example.login_api.User_product_adapter;
import com.example.login_api.activity.Ecommerce_activity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class home_fragment extends Fragment {

List<Productdatum> productdata=new ArrayList<>();
RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_home_fragment, container, false);
        recyclerView=view.findViewById(R.id.Show_user_recycler);
        int uid;
        uid= preferences.getInt("uid",0);
        Log.d("GGG", "onCreateView: "+uid);
        retro_class.callapi().USER_PRODUCT_CALL(uid).enqueue(new Callback<UserProduct>() {
            @Override
            public void onResponse(Call<UserProduct> call, Response<UserProduct> response) {
                int lenth;
                //lenth=response.body().getProductdata().toString().length();

                if (response.body().getConnection()==1)
                {
                            if (response.body().getResult()==1)
                            {
                                lenth=response.body().getProductdata().size();
                                Log.d("GGG", "onResponse: "+lenth);
                                for (int t=0;t<lenth;t++)
                                {
                                    productdata.add(response.body().getProductdata().get(t));
                                }
                                User_product_adapter adapter=new User_product_adapter(getContext(),productdata);
                                LinearLayoutManager manager=new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setAdapter(adapter);
                            }
                            else
                            {
                                Toast.makeText(getContext(), "Can't Get Product", Toast.LENGTH_SHORT).show();
                            }
                }
                else {
                    Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserProduct> call, Throwable t) {
                Log.e("GGG", "onFailure: "+t.getLocalizedMessage() );
            }
        });

        return view;
    }
}