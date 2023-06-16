package com.divyanshu.ezobookkotlin

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.divyanshu.ezobookkotlin.Api.RetrofitInstance
import com.divyanshu.ezobookkotlin.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val authorNameHeading =  binding.authorNameHeading


        binding.btnShowAuthor.setOnClickListener {
            getData()
            authorNameHeading.isVisible=true
        }

    }

    private fun getData() {

        RetrofitInstance.apiInterface.getData().enqueue(object : Callback<ResponseDataClass?> {
            override fun onResponse(
                call: Call<ResponseDataClass?>,
                response: Response<ResponseDataClass?>
            ) {
                val authorname= response.body()!![0]?.author
                binding.authorName.text=authorname
                Glide.with(this@MainActivity).load(response.body()!![0]?.download_url).into(binding.authorimg)
            }

            override fun onFailure(call: Call<ResponseDataClass?>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Unable to get response from server",Toast.LENGTH_SHORT).show()
            }
        })
    }
}
