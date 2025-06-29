package com.example.bandin.data.api

import com.example.bandin.data.model.BandInfoResponse
import com.example.bandin.data.model.BandMembersResponse
import com.example.bandin.data.model.UserDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface BandService {
    //밴드 가입 멤버 조회 API
    @GET("/api/member/bands/{bandId}/members")
    fun getBandMembers(
        @Header("Authorization") authHeader: String,
        @Path("bandId") bandId: Int
    ): Call<BandMembersResponse>

    //유저 프로필 정보 조회 API
    @GET("/api/member/user/{userId}")
    fun getUserData(
        @Path("userId") userId: String
    ): Call<UserDataResponse>

    //밴드 정보 조회 API
    @GET("/api/member/bands/{bandId}")
    fun getBandInfo(
        @Header("Authorization") authHeader: String,
        @Path("bandId") bandId: Int
    ): Call<BandInfoResponse>
}