package com.example.login_api.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.login_api.DataModels.Productdatum;
import com.example.login_api.R;

import java.util.ArrayList;
import java.util.List;


public class Show_all_product extends Fragment {

List<Productdatum> allproduct=new ArrayList<>();
RecyclerView recyclerView;
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

        return view;
    }
}