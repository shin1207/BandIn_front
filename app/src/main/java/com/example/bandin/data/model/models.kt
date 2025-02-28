package com.example.bandin.data.model

//API호출에 따른 요청/응답 데이터 모델 정의


// 로그인 요청 모델
data class LoginRequest(
    val email: String,
    val password: String
)

// 로그인 응답 모델
data class LoginResponse(
    val token: String,
    val userId: Int,
    val nickname: String
)

// 회원가입 요청 모델
data class SignupRequest(
    val email: String,
    val password: String,
    val nickname: String
)