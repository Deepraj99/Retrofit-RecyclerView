package com.example.retrofitrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitrecyclerview.data.ApiInterface
import com.example.retrofitrecyclerview.data.UserItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://api.github.com"
class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var userData: List<UserItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        getAllData()

    }
    private fun getAllData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
        val retrofitData = retrofit.getData()
        retrofitData.enqueue(object : Callback<List<UserItem>> {
            override fun onResponse(
                call: Call<List<UserItem>>,
                response: Response<List<UserItem>>
            ) {
                userData = response.body()!!
                recyclerView.adapter = MyAdapter(this@MainActivity, userData)
            }

            override fun onFailure(call: Call<List<UserItem>>, t: Throwable) {
                Log.d("ERROR", "It's an error", t)
            }
        })
    }
}