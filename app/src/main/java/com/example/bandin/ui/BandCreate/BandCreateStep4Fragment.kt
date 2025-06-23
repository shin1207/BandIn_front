package com.example.bandin.ui.BandCreate

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.bandin.R
import com.example.bandin.data.api.RetrofitClient
import com.example.bandin.data.model.Gender
import com.example.bandin.data.model.Genre
import com.example.bandin.data.model.InstrumentExperience
import com.example.bandin.data.model.SignUpRequest
import com.example.bandin.data.model.SignUpResponse
import com.example.bandin.data.model.State
import com.example.bandin.data.model.Style
import com.example.bandin.ui.Main
import com.example.bandin.viewmodel.BandCreateViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BandCreateStep4Fragment : Fragment() {

    lateinit var btnNext: Button
    lateinit var btnBack: Button

    lateinit var btnStyle1: Button
    lateinit var btnStyle2: Button
    lateinit var btnStyle3: Button

    lateinit var style: String      //String으로 저장용 변수

    private val bandCreateViewModel: BandCreateViewModel by activityViewModels() // ViewModel 공유

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //스텝4을 컨테이너에 띄우도록 view로 설정
        val view = inflater.inflate(R.layout.fragment_band_create_step4, container, false)

        //사용자가 누른 버튼에 따라 style의 String 값 설정
        btnStyle1.setOnClickListener { style = "취미" }

        btnStyle2.setOnClickListener { style = "세미프로" }

        btnStyle3.setOnClickListener { style = "프로지향" }



        //이전 클릭
        btnBack.setOnClickListener {
            //이전 Fragement로 이동 (DB 저장은 초기화됨)
            (activity as? BandCreate)?.goToNextFragment(BandCreateStep3Fragment())
        }


        //다음 클릭
        btnNext.setOnClickListener {

            //TODO : 값이 입력되지 않은 상태일 때 모달/문구 띄우기 & 다음 스텝 넘어가지 못하게

            //viewModel에 style 값 저장
            bandCreateViewModel.style = style

            //최종 저장 단계
            val name = bandCreateViewModel.name
            val state = bandCreateViewModel.state
            val state_detail = bandCreateViewModel.state_detail
            val genre = bandCreateViewModel.genre
            val style = bandCreateViewModel.style

            // TODO : 밴드만들기 API 불러오기
            //모든 값을 받았는지 확인
            if (name != null && state != null && state_detail != null && genre != null && style != null )  {

                //<최종 단계> 회원가입 api 불러오기
                BandCreate(name, state, state_detail, genre, style)
            }

            Log.d("디버깅", "새 밴드를 만들었습니다. -> 밴드명 : ${name}")


            // 성공 후 Main (메인화면)로 이동
            val intent = Intent(requireContext(), Main::class.java)
            startActivity(intent)

            activity?.finish()  // 화면 종료 (사용자가 뒤로가기 눌러도 이동못하게 종료시킴)
        }
        return view
    }
    private fun BandCreate(name: String, state: String, state_detail: String, genre: String, style: String) {

        // TODO: 밴드만들기 API 호출
        val request = BandCreateRequest(name, state, state_detail, genre, style)

        // TODO : 밴드만들기 API 요청 & 응답 모델 만들기, 데이터 담을 viewModel 수정 (데이터 타입)
        RetrofitClient.instance.signup(request).enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                if (response.isSuccessful) {
                    Log.d("디버깅", "밴드만들기 성공")
                    // TODO: 메인 화면으로 이동
                } else {
                    Log.d("디버깅", "밴드만들기 실패: ${response.code()}")

                }
            }

            // onFailure 메서드
            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                Log.d("디버깅", "밴드만들기 API 오류: ${t.message}") }
        })

        Log.d("디버깅", "<<api>> 밴드만들기 API 불러오기 성공")
    }
}
