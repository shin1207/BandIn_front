package com.example.bandin.data.api

//로그인, 회원가입 등 API 요청 정의

import com.example.bandin.data.model.*  //데이터모델 불러오기
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

//API 요청 정의
    // 로그인
    @POST("/api/member/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    // 회원가입
    @POST("/api/member/signup")
    fun signup(@Body request: SignUpRequest): Call<SignUpResponse> // 성공 시 응답 본문 없음

    // 회원가입 > 이메일로 인증번호 전송
    @POST("/api/member/email")
    fun emailSend(@Body request: EmailSendRequest): Call<EmailSendResponse>

    // 회원가입 > 인증번호 검증
    @POST("/api/member/verify")
    fun emailVerify(@Body request: EmailVerifyRequest): Call<EmailVerifyResponse>

    // 로그아웃
    @POST("/api/logout")
    fun logout(@Body request: LogoutRequest): Call<LogoutResponse>

    // 밴드 만들기
    //@POST("/api/member/createBand)
    //fun bandCreate(@Body request: BandCreateRequest): Call<BandCreateResponse>

}
