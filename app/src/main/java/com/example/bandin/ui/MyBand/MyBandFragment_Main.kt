package com.example.bandin.ui.MyBand

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.bandin.R
import com.example.bandin.data.api.RetrofitClient.bandService
import com.example.bandin.data.model.BandInfoResponse
import com.example.bandin.data.model.BandMembersResponse
import com.example.bandin.ui.BandCreate.BandCreate
import com.example.bandin.ui.BandCreate.BandCreateStep2Fragment
import com.example.bandin.viewmodel.MyBandViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyBandFragment_Main : Fragment() {

    private val MyBandViewModel: MyBandViewModel by activityViewModels() // ViewModel 공유

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //fragment_my_band_main을 컨테이너에 띄우도록 view로 설정
        val view = inflater.inflate(R.layout.fragment_my_band_main, container, false)

        val textBandName = view.findViewById<TextView>(R.id.textBandName)
        val btnSetting = view.findViewById<ImageButton>(R.id.btnSetting)
        val btnPrevious = view.findViewById<ImageButton>(R.id.btnPrevious)


        val bandImage = view.findViewById<ImageView>(R.id.bandImage)
        val textGenre = view.findViewById<TextView>(R.id.textGenre)
        val textAge = view.findViewById<TextView>(R.id.textAge)
        val textStyle = view.findViewById<TextView>(R.id.textStyle)
        val textState = view.findViewById<TextView>(R.id.textState)
        val textStatus = view.findViewById<TextView>(R.id.textStatus)

        val btnMenu1 = view.findViewById<Button>(R.id.btnMenu1)
        val btnMenu2 = view.findViewById<Button>(R.id.btnMenu2)
        val btnMenu3 = view.findViewById<Button>(R.id.btnMenu3)

        val btnNew = view.findViewById<Button>(R.id.btnNew)

        val postContainer = view.findViewById<LinearLayout>(R.id.postContainer)


        // 🔐 사용자 인증 정보 가져오기
        // SharedPreferences에서 accessToken, memberName 가져오기
        val sharedPref = requireContext().getSharedPreferences("UserPrefs", AppCompatActivity.MODE_PRIVATE)
        val accessToken = sharedPref.getString("accessToken", "") ?: ""
        val myMemberId = sharedPref.getString("memberName", "noName") ?: "noName"
        val bandId = arguments?.getInt("bandId", -1) ?: -1

        // 디버깅
        Log.d("나의밴드 (메인Pg)", "이름: $myMemberId")
        Log.d("나의밴드 (메인Pg)", "bandId: $bandId")


        // 액세스 토큰이 유효할 때만 실행
        if (accessToken.isNotBlank() && bandId != -1) {
            val tokenHeader = "Bearer $accessToken"

            // ---- 1. 밴드 정보 ----
            bandService.getBandInfo(tokenHeader, bandId).enqueue(object :
                Callback<BandInfoResponse> {
                override fun onResponse(
                    call: Call<BandInfoResponse>,
                    response: Response<BandInfoResponse>
                ) {
                    if (response.isSuccessful) {
                        val band = response.body()
                        // TODO : 밴드 이미지
                        textBandName.text = band?.name ?: "(밴드명 없음)"
                        textGenre.text = "장르 : ${band?.genre ?: "-"}"
                        textAge.text = "연령대 : ${band?.ageGroup ?: "-"}"
                        textStyle.text = "지향 : ${band?.style ?: "-"}"
                        textState.text = "지역 : ${band?.location ?: "-"}"

                    } else {
                        Log.e("밴드정보 실패", "응답 코드: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<BandInfoResponse>, t: Throwable) {
                    Log.e("밴드정보 오류", "네트워크 오류: ${t.message}")
                }
            })
            // ---- 1-2. 멤버의 밴드 참여상태 적용 ----
            bandService.getBandMembers(tokenHeader, bandId).enqueue(object : Callback<BandMembersResponse> {
                override fun onResponse(call: Call<BandMembersResponse>, response: Response<BandMembersResponse>) {
                    if (response.isSuccessful) {
                        val members = response.body()?.data ?: emptyList()

                        // 내 멤버 상태 확인
                        if (members.any { it.memberId == myMemberId && it.status == "ACCEPTED" }) {
                            textStatus.text = "참여 중"
                        }
                    } else {
                        Log.d("밴드멤버 Status 오류", "응답 코드: ${response.code()}")
                    }
                }
                override fun onFailure(call: Call<BandMembersResponse>, t: Throwable) {
                    Log.d("밴드멤버 Status 오류", "네트워크 오류: ${t.message}")
                }
            })
            //TODO : 밴드 공고 & 일정 조회 API 완성 후 호출
            // --- 2. 밴드 공고 & 일정 띄우기 ---

        }


        //일정 및 공고 추가  <-- "공고 추가" 기능은 밴드장 에게만 활성화
        btnNew.setOnClickListener{
            //TODO : 일정 추가 OR 공고 추가 Dialog 띄우기
            //TODO : dialog_my_band_main_add 만들기

            //일정 추가 선택 -> my_band_new_schedule 이동
            //공고 추가 선택 -> my_band_new_recruit 이동

        }



        // ---- 버튼 동작 ----
        btnPrevious.setOnClickListener {
            // 이전 화면으로 이동
        }
        //설정
        btnSetting.setOnClickListener{
            //MyBandFragment_Setting 으로 이동
            (activity as? MyBand)?.goToNextFragment(MyBandFragment_Setting())
        }
        //메뉴 선택
        btnMenu1.setOnClickListener{
            //MyBandFragment_Main 으로 이동
            (activity as? MyBand)?.goToNextFragment(MyBandFragment_Main())

        }
        btnMenu2.setOnClickListener{
            //MyBandFragment_Member 으로 이동
            (activity as? MyBand)?.goToNextFragment(MyBandFragment_Member())

        }
        btnMenu3.setOnClickListener{
            //MyBandFragment_Info 으로 이동
            (activity as? MyBand)?.goToNextFragment(MyBandFragment_Info())

        }
        return view
    }
}