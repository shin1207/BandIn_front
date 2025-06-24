package com.example.bandin.data.api

import com.example.bandin.data.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface MainService {

    //이미지 업로드
    @Multipart
    @POST("/api/member/uploadImage")
    fun uploadImage(
        @Part image: MultipartBody.Part,
        @Part("username") userName: RequestBody
    ): Call<String>

    @GET("/image")
    fun getImage(
        @Query("fileName") fileName: String
    ): Call<ImageResponse>
}
    