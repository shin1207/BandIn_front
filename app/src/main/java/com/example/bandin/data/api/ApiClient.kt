package com.example.bandin.data.api

//백엔드(Spring Boot)와 통신하는 Retrofit API 인터페이스
//Retrofit 인스턴스를 생성하는 파일

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val BASE_URL = "https://your-backend-url.com" // 백엔드 API 주소

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS) // 연결 타임아웃 설정
        .readTimeout(30, TimeUnit.SECONDS) // 읽기 타임아웃 설정
        .writeTimeout(30, TimeUnit.SECONDS) // 쓰기 타임아웃 설정
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL) // 백엔드 API 기본 URL 설정
        .addConverterFactory(GsonConverterFactory.create()) // JSON 변환 설정
        .client(client)
        .build()

    fun <T> createService(service: Class<T>): T {
        return retrofit.create(service) // API 인터페이스 구현체 반환
    }
}