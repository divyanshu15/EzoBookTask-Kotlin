package com.divyanshu.ezobookkotlin.Api

import com.divyanshu.ezobookkotlin.ResponseDataClass
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("list")
    fun getData():Call<ResponseDataClass>

}