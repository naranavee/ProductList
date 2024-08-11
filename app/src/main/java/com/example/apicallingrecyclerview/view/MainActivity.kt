package com.example.apicallingrecyclerview.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apicallingrecyclerview.api.ApiInterface
import com.example.apicallingrecyclerview.R
import com.example.apicallingrecyclerview.data.ProductDatabase
import com.example.apicallingrecyclerview.data.ProductEntity
import com.example.apicallingrecyclerview.model.DataModel
import com.example.apicallingrecyclerview.model.DataModelItem
import com.example.apicallingrecyclerview.ui.RecyclerAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var list: ArrayList<DataModelItem>
    private lateinit var database: ProductDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        list = ArrayList()

        database = ProductDatabase.getDatabase(this)

        val layoutManager = LinearLayoutManager(this)
        val adapter = RecyclerAdapter(list, this)
        recyclerView.layoutManager = layoutManager

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com")
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

                    // Store data in local database
                    lifecycleScope.launch(Dispatchers.IO) {
                        // Clear previous data
                        database.productDao().deleteAll()

                        // Insert new data
                        val productEntities = list.map { item ->
                            ProductEntity(
                                title = item.title,
                                category = item.category,
                                description = item.description
                            )
                        }
                        database.productDao().insertAll(productEntities)

                        // Retrieve data from local database and update UI
                        val storedProducts = database.productDao().getAllProducts()
                        withContext(Dispatchers.Main) {
                            list.clear()
                            list.addAll(storedProducts.map { item ->
                                DataModelItem(
                                    id = item.id,
                                    title = item.title,
                                    category = item.category,
                                    description = item.description
                                )
                            })
                            adapter.notifyDataSetChanged()
                            recyclerView.adapter = adapter
                        }
                    }
                }
            }

            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}