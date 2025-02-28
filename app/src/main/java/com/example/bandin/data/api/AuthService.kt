package com.example.bandin.data.api

//로그인, 회원가입 등 API 요청 정의

import com.example.bandin.data.model.*  //데이터모델 불러오기
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

//API 요청 정의
    // 로그인 요청
    @POST("/users/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    // 회원가입 요청
    @POST("/users/signup")
    fun signup(@Body request: SignupRequest): Call<Void> // 성공 시 응답 본문 없음
}
