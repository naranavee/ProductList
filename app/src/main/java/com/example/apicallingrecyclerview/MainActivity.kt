package com.example.apicallingrecyclerview

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var list: ArrayList<DataModelItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        list = ArrayList()

        val layoutManager = LinearLayoutManager(this)
        val adapter = RecyclerAdapter(list, this)
        recyclerView.layoutManager = layoutManager

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api: ApiInterface = retrofit.create(ApiInterface::class.java)
        val call: Call<DataModel> = api.getData()

        call.enqueue(object : Callback<DataModel> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                if (response.isSuccessful) {
                    list.clear()
                    response.body()?.let { data ->
                        list.addAll(data)
                    }
                    adapter.notifyDataSetChanged()
                    recyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
