package com.example.darazonline;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.darazonline.URL.Url;
import com.example.darazonline.adapter.ProductsAdapter;
import com.example.darazonline.api.Api;
import com.example.darazonline.model.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    Context context;

    View view;
    private RecyclerView recyclerView_;
    public List<Products> productsList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView_ =view.findViewById(R.id.recyclerView);
        getProduct();
        return view;
    }

    public void getProduct() {
        Api retrofitProductAPI = Url.getRetrofit().create(Api.class);
        Call<List<Products>> ProductsCall = retrofitProductAPI.getAllproducts();
        Toast.makeText(getActivity(), "Here", Toast.LENGTH_SHORT).show();
        ProductsCall.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                System.out.println("Product list " + response.body());
                Toast.makeText(getActivity(), "Sample", Toast.LENGTH_SHORT).show();
                ProductsAdapter recyclerviewAdapter = new ProductsAdapter(getActivity(), response.body());
                RecyclerView.LayoutManager mlayoutManager = new GridLayoutManager(getActivity(), 3);
                recyclerView_.setLayoutManager(mlayoutManager);
                recyclerView_.setHasFixedSize(true);
                recyclerView_.setAdapter(recyclerviewAdapter);
                recyclerviewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }
        });
    }
}