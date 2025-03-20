package com.example.bandin.data.api

//로그인, 회원가입 등 API 요청 정의

import com.example.bandin.data.model.*  //데이터모델 불러오기
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

//API 요청 정의
    // 로그인 요청
    @POST("/api/member/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    // 회원가입 요청
    @POST("/api/member/signup")
    fun signup(@Body request: SignUpRequest): Call<Void> // 성공 시 응답 본문 없음

    // 이메일 전송 요청
    @POST("/api/member/email")
    fun emailSend(@Body request: EmailSendRequest): Call<Void> // 성공 시 응답 본문 없음

    // 이메일 전송 요청
    @POST("/api/member/verify")
    fun emailVerify(@Body request: EmailVerifyRequest): Call<Void> // 성공 시 응답 본문 없음

}
