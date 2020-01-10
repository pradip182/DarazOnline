package com.example.darazonline.api;

import com.example.darazonline.model.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("daraz_products")
    Call<List<Products>> getAllproducts();

}