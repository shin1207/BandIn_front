package com.example.bandin.ui.MyBand

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bandin.R
import com.example.bandin.data.api.RetrofitClient.bandService
import com.example.bandin.ui.Profile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.bandin.data.model.BandMembersResponse
import com.example.bandin.data.model.UserDataResponse

class MyBandMember : AppCompatActivity(){
    // xml의 변수 정의
    private lateinit var btnPrevious   : ImageButton
    private lateinit var btnSetting    : ImageButton
    private lateinit var textBandName  : TextView
    private lateinit var textGenre     : TextView
    private lateinit var textAge       : TextView
    private lateinit var textStyle     : TextView
    private lateinit var textState     : TextView
    private lateinit var textStatus    : TextView
    private lateinit var bandImage     : ImageView

    private lateinit var btnMenu1      : Button
    private lateinit var btnMenu2      : Button
    private lateinit var btnMenu3      : Button

    private lateinit var navChat       : LinearLayout
    private lateinit var navBand       : LinearLayout
    private lateinit var navProfile    : LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_band_member)
        val memberContainer = findViewById<LinearLayout>(R.id.memberContainer)
        //위에서 정의한 변수들의 id 연결
        btnPrevious   = findViewById(R.id.btnPrevious)
        btnSetting    = findViewById(R.id.btnSetting)
        textBandName  = findViewById(R.id.textBandName)
        textGenre     = findViewById(R.id.textGenre)
        textAge       = findViewById(R.id.textAge)
        textStyle     = findViewById(R.id.textStyle)
        textState     = findViewById(R.id.textState)
        textStatus    = findViewById(R.id.textStatus)
        bandImage     = findViewById(R.id.bandImage)

        btnMenu1      = findViewById(R.id.btnMenu1)
        btnMenu2      = findViewById(R.id.btnMenu2)
        btnMenu3      = findViewById(R.id.btnMenu3)

        navChat       = findViewById(R.id.chat)
        navBand       = findViewById(R.id.bandin)
        navProfile    = findViewById(R.id.profile)

        //API호출+멤버가져오기
        val accessToken = intent.getStringExtra("accessToken") ?: ""
        val bandId      = intent.getIntExtra("bandId", -1)
        if (accessToken.isNotBlank() && bandId != -1) {
            val tokenHeader = "Bearer $accessToken"

            bandService.getBandMembers(tokenHeader, bandId)
                .enqueue(object : retrofit2.Callback<BandMembersResponse> {
                    override fun onResponse(
                        call: retrofit2.Call<BandMembersResponse>,
                        response: retrofit2.Response<BandMembersResponse>
                    ) {
                        if (response.isSuccessful) {
                            // TODO: data 처리 & UI 반영
                            val members = response.body()?.data ?: emptyList()
                            //memberID부분 로그인 과정에서 sharedPref에 저장해서 확인해야함
                            val sharedPref = getSharedPreferences("UserPrefs", MODE_PRIVATE)
                            val myMemberId = sharedPref.getString("name", "noName")
                            if (members.any { it.memberId == myMemberId && it.status == "ACCEPTED" }) {
                                textStatus.text = "참여 중"
                            }
                            members.filter { it.status == "ACCEPTED" && it.memberId != myMemberId }.forEach { member ->
                                val memberView = layoutInflater.inflate(R.layout.activity_my_band_member_item, memberContainer, false)

                                val memberName = memberView.findViewById<TextView>(R.id.textMemberName)
                                val memberInfo = memberView.findViewById<TextView>(R.id.textMemberInfo)
                                //리더 표시기능 미구현됨
                                val teamLeaderIcon = memberView.findViewById<ImageView>(R.id.teamLeaderStatus)

                                bandService.getUserData(member.memberId).enqueue(object : Callback<UserDataResponse> {
                                    override fun onResponse(call: Call<UserDataResponse>, response: Response<UserDataResponse>) {
                                        if (response.isSuccessful) {
                                            val user = response.body()
                                            memberName.text = user?.name ?: member.memberId
                                            memberInfo.text = "${user?.instrument ?: ""} / ${user?.experience ?: ""}"
                                        } else {
                                            memberName.text = member.memberId
                                        }
                                    }

                                    override fun onFailure(call: Call<UserDataResponse>, t: Throwable) {
                                        memberName.text = member.memberId
                                    }
                                })


                                memberContainer.addView(memberView)
                            }
                        } else {
                            // TODO: 에러 코드별 처리
                        }
                    }

                    override fun onFailure(
                        call: retrofit2.Call<BandMembersResponse>,
                        t: Throwable
                    ) {
                        // TODO: 네트워크 오류 처리
                    }
                })
        }


        btnPrevious.setOnClickListener { finish() }
        btnSetting.setOnClickListener  {

        /* TODO: 설정 */ }

        btnMenu1.setOnClickListener {  }
        btnMenu2.setOnClickListener {  }
        btnMenu3.setOnClickListener {  }

        navChat   .setOnClickListener { /* TODO: 채팅 화면 */ }
        navBand   .setOnClickListener { /* 현재 화면 */ }
        navProfile.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
    }
}