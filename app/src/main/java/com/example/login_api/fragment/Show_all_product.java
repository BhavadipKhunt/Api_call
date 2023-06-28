package com.example.login_api.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.login_api.DataModels.AlluserProduct;
import com.example.login_api.DataModels.Productdatum;
import com.example.login_api.DataModels.retro_class;
import com.example.login_api.R;
import com.example.login_api.User_product_adapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Show_all_product extends Fragment {


RecyclerView recyclerView;
List<Productdatum> getAllproduct=new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_show_all_product, container, false);
       recyclerView=view.findViewById(R.id.show_all_recycler);
        LinearLayoutManager manager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        retro_class.callapi().ALLUSER_PRODUCT_CALL(1).enqueue(new Callback<AlluserProduct>() {
            @Override
            public void onResponse(Call<AlluserProduct> call, Response<AlluserProduct> response) {
                for (int i=0;i<response.body().getProductdata().size();i++)
                {
                    getAllproduct.add(response.body().getProductdata().get(i));
                }
                User_product_adapter adapter =new User_product_adapter(getContext(),getAllproduct);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<AlluserProduct> call, Throwable t) {
                Log.d("GGG", "onFailure: "+t.getLocalizedMessage());
            }
        });
        return view;
    }
}