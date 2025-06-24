package com.example.bandin.data.model

data class BandMembersResponse(
    val resultCode: String,
    val data: List<BandMemberDto>,
    val message: String
)

data class BandMemberDto(
    val id: Int,
    val memberId: String,
    val bandId: Int,
    val status: String,
    val createdAt: String
)

data class UserDataResponse(
    val name: String,
    val state: String,
    val style: String,
    val genre: String,
    val instrument: List<InstrumentDto>,
    val experience: String
)

data class InstrumentDto(
    val instrument: String,
    val experience: String
)