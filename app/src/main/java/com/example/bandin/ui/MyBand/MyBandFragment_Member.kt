package com.example.bandin.ui.MyBand

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import android.util.Log
import com.example.bandin.R
import com.example.bandin.data.api.RetrofitClient.bandService
import com.example.bandin.ui.Profile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.bandin.data.model.BandMembersResponse
import com.example.bandin.data.model.UserDataResponse
import com.example.bandin.ui.BandCreate.BandCreate

class MyBandFragment_Member : Fragment(){
    // xml의 변수 정의
    lateinit var btnPrevious   : ImageButton
    lateinit var btnSetting    : ImageButton

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

    @SuppressLint("MissingInflatedId")  //xml에서 찾을 수 없는 id 에러는 무시
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //fragment_my_band_member을 컨테이너에 띄우도록 view로 설정
        val view = inflater.inflate(R.layout.fragment_my_band_member, container, false)

        //밴드 정보



        // 밴드 멤버
        val memberContainer = view.findViewById<LinearLayout>(R.id.memberContainer)

        // SharedPreferences에서 accessToken, memberName 가져오기
        val sharedPref = requireContext().getSharedPreferences("UserPrefs", AppCompatActivity.MODE_PRIVATE)
        val accessToken = sharedPref.getString("accessToken", "") ?: ""
        val myMemberId = sharedPref.getString("memberName", "noName") ?: "noName"
        val bandId = arguments?.getInt("bandId", -1) ?: -1

        Log.d("밴드멤버 요청", "accessToken: $accessToken")
        Log.d("밴드멤버 요청", "bandId: $bandId")

        if (accessToken.isNotBlank() && bandId != -1) {
            val tokenHeader = "Bearer $accessToken"

            bandService.getBandMembers(tokenHeader, bandId).enqueue(object : Callback<BandMembersResponse> {
                override fun onResponse(call: Call<BandMembersResponse>, response: Response<BandMembersResponse>) {
                    if (response.isSuccessful) {
                        val members = response.body()?.data ?: emptyList()

                        // 내 멤버 상태 확인
                        if (members.any { it.memberId == myMemberId && it.status == "ACCEPTED" }) {
                            textStatus.text = "참여 중"
                        }

                        // 멤버 목록 표시
                        members.filter { it.status == "ACCEPTED" && it.memberId != myMemberId }.forEach { member ->
                            val memberView = layoutInflater.inflate(R.layout.activity_my_band_member_item, memberContainer, false)

                            val memberName = memberView.findViewById<TextView>(R.id.textMemberName)
                            val memberInfo = memberView.findViewById<TextView>(R.id.textMemberInfo)
                            val teamLeaderIcon = memberView.findViewById<ImageView>(R.id.teamLeaderStatus)

                            // 개별 멤버 상세정보 요청
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
                        Log.d("밴드멤버 오류", "응답 코드: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<BandMembersResponse>, t: Throwable) {
                    Log.d("밴드멤버 오류", "네트워크 오류: ${t.message}")
                }
            })
        }


        /*위에서 정의한 변수들의 id 연결
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
        navProfile    = findViewById(R.id.profile)*/

        /*API호출+멤버가져오기
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
*/

        btnPrevious.setOnClickListener {
            // 이전 화면으로 이동
        }
        btnSetting.setOnClickListener  {
            // 설정 화면으로 이동

        }

        btnMenu1.setOnClickListener { //Main 이동
            (activity as? MyBand)?.goToNextFragment(MyBandFragment_Main())
        }
        btnMenu2.setOnClickListener { //Member 이동
            (activity as? MyBand)?.goToNextFragment(MyBandFragment_Member())

        }
        btnMenu3.setOnClickListener { //Info 이동
            (activity as? MyBand)?.goToNextFragment(MyBandFragment_Info())

        }

        navChat   .setOnClickListener { /* TODO: 채팅 activity로 이동 */ }
        navBand   .setOnClickListener { // Main 이동
            (activity as? MyBand)?.goToNextFragment(MyBandFragment_Main())
        }
        navProfile.setOnClickListener{ //프로필 activity로 이동
            val intent = Intent(requireContext(), Profile::class.java)
            startActivity(intent)
        }
        return view
    }
}