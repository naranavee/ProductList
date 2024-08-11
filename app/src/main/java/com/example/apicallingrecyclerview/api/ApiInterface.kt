package com.example.apicallingrecyclerview.api

import retrofit2.Call
import retrofit2.http.GET
import com.example.apicallingrecyclerview.model.DataModel

interface ApiInterface {

    @GET("/products")
    fun getData(): Call<DataModel>

}