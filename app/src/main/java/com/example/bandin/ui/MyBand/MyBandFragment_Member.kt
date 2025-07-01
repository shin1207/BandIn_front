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
import com.example.bandin.data.model.BandInfoResponse
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

        // 🔗 ID 연결
        btnPrevious = view.findViewById(R.id.btnPrevious)
        btnSetting = view.findViewById(R.id.btnSetting)

        textBandName = view.findViewById(R.id.textBandName)
        textGenre = view.findViewById(R.id.textGenre)
        textAge = view.findViewById(R.id.textAge)
        textStyle = view.findViewById(R.id.textStyle)
        textState = view.findViewById(R.id.textState)
        textStatus = view.findViewById(R.id.textStatus)
        bandImage = view.findViewById(R.id.bandImage)

        btnMenu1 = view.findViewById(R.id.btnMenu1)
        btnMenu2 = view.findViewById(R.id.btnMenu2)
        btnMenu3 = view.findViewById(R.id.btnMenu3)

        navChat = view.findViewById(R.id.chat)
        navBand = view.findViewById(R.id.bandin)
        navProfile = view.findViewById(R.id.profile)

        val memberContainer = view.findViewById<LinearLayout>(R.id.memberContainer)

        // 🔐 사용자 인증 정보 가져오기
        // SharedPreferences에서 accessToken, memberName 가져오기
        val sharedPref = requireContext().getSharedPreferences("UserPrefs", AppCompatActivity.MODE_PRIVATE)
        val accessToken = sharedPref.getString("accessToken", "") ?: ""
        val myMemberId = sharedPref.getString("memberName", "noName") ?: "noName"
        val bandId = arguments?.getInt("bandId", -1) ?: -1

        // 디버깅
        Log.d("나의밴드 (멤버Pg)", "이름: $myMemberId")
        Log.d("나의밴드 (멤버Pg)", "bandId: $bandId")


        // 액세스 토큰이 유효할 때만 실행
        if (accessToken.isNotBlank() && bandId != -1) {
            val tokenHeader = "Bearer $accessToken"

            // ---- 1. 밴드 정보 ----
            bandService.getBandInfo(tokenHeader, bandId).enqueue(object : Callback<BandInfoResponse> {
                override fun onResponse(call: Call<BandInfoResponse>, response: Response<BandInfoResponse>) {
                    if (response.isSuccessful) {
                        val band = response.body()
                        textBandName.text = band?.name ?: "(밴드명 없음)"
                        textGenre.text    = "장르 : ${band?.genre ?: "-"}"
                        textAge.text      = "연령대 : ${band?.ageGroup ?: "-"}"
                        textStyle.text    = "지향 : ${band?.style ?: "-"}"
                        textState.text    = "지역 : ${band?.location ?: "-"}"
                    } else {
                        Log.e("밴드정보 실패", "응답 코드: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<BandInfoResponse>, t: Throwable) {
                    Log.e("밴드정보 오류", "네트워크 오류: ${t.message}")
                }
            })

            // ---- 2. 밴드 멤버  ----
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

                            //getUserData대신 사용
                            memberName.text = member.memberId
                            memberInfo.text = "악기: ${member.instrument.instrument} / 경력: ${member.instrument.experience}"
                            val teamLeaderIcon = memberView.findViewById<ImageView>(R.id.teamLeaderStatus)

                            //리더 아이콘 표시 유무
                            if (member.bandRole == "LEADER") {
                                teamLeaderIcon.visibility = View.VISIBLE
                            } else {
                                teamLeaderIcon.visibility = View.GONE
                            }

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



        //---- 버튼 동작 구현 ----
        btnPrevious.setOnClickListener {
            // 이전 화면으로 이동
        }
        btnSetting.setOnClickListener  {
            // 설정 화면으로 이동
            (activity as? MyBand)?.goToNextFragment(MyBandFragment_Setting())

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