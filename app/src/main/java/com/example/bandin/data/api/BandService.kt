package com.example.bandin.data.api

import com.example.bandin.data.model.BandMembersResponse
import com.example.bandin.data.model.UserDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface BandService {
    @GET("/api/member/bands/{bandId}/members")
    fun getBandMembers(
        @Header("Authorization") authHeader: String,
        @Path("bandId") bandId: Int
    ): Call<BandMembersResponse>

    @GET("/api/member/user/{userId}")
    fun getUserData(
        @Path("userId") userId: String
    ): Call<UserDataResponse>
}