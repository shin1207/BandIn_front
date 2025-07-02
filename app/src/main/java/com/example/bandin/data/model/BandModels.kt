package com.example.bandin.data.model

data class BandMembersResponse(
    val resultCode: String,
    val data: List<BandMemberDto>,
    val message: String
)

data class BandMemberDto(
    val id: Int,
    val memberId: String,
    val name: String,
    val bandId: Int,
    val status: String,
    val bandRole: String,
    val instrument: InstrumentDto,
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

data class BandInfoResponse(
    val name: String,
    val genre: String,
    val ageGroup: String,
    val style: String,
    val location: String
    //추후 BE API 명세서 보고 수정
)