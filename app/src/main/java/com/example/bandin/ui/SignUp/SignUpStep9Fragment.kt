package com.example.bandin.ui.SignUp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.bandin.R
import com.example.bandin.data.api.RetrofitClient
import com.example.bandin.data.model.Gender
import com.example.bandin.data.model.Genre
import com.example.bandin.data.model.Instrument
import com.example.bandin.data.model.InstrumentExperience
import com.example.bandin.data.model.SignUpRequest
import com.example.bandin.data.model.SignUpResponse
import com.example.bandin.data.model.State
import com.example.bandin.data.model.Style
import com.example.bandin.ui.Main
import com.example.bandin.viewmodel.SignUpViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpStep9Fragment : Fragment() {
    lateinit var btnNext: Button

    lateinit var btnStyle1: Button
    lateinit var btnStyle2: Button
    lateinit var btnStyle3: Button

    lateinit var style: String
    private val signUpViewModel: SignUpViewModel by activityViewModels() // ViewModel 공유

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //스텝9을 컨테이너에 띄우도록 view로 설정
        val view = inflater.inflate(R.layout.fragment_sign_up_step9, container, false)


        //사용자가 누른 버튼에 따라 style의 String 값 설정
        btnStyle1.setOnClickListener { style = "취미" }  //취미밴드

        btnStyle2.setOnClickListener { style = "세미프로" }  //세미프로

        btnStyle3.setOnClickListener { style = "프로" }  //프로지향



        //다음 클릭
        btnNext.setOnClickListener {

            //TODO : 값이 입력되지 않은 상태일 때 모달/문구 띄우기 & 다음 스텝 넘어가지 못하게
            if (style == null) {
                Toast.makeText(requireContext(), "스타일을 선택하세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //viewModel에 style 값 저장
            signUpViewModel.style = Style.valueOf(style)  // String을 Enum으로 변환하여 저장

            val email = signUpViewModel.email
            val password = signUpViewModel.password

            val name = signUpViewModel.name
            val birth = signUpViewModel.birth
            val gender = signUpViewModel.gender

            val state = signUpViewModel.state

            //악기 및 경력 값을 viewModel에서 가져오기
            val instrument = signUpViewModel.InstrumentExperience ?: emptyList()



            val genre = signUpViewModel.genre
            val style = signUpViewModel.style


            //모든 값을 받았는지 확인
            if (email != null && password != null && name != null && birth != null
                && gender != null && state != null &&  genre != null && style != null && instrument != null)  {

                //<최종 단계> 회원가입 api 불러오기
                SignUp(email, password, name, birth, gender, state, genre, style, instrument)
            }

            Log.d("디버깅", "사용자 : ${name} - 회원가입이 완료되었습니다. ")


            // 회원가입 성공 후 Main (메인화면)로 이동
            val intent = Intent(requireContext(), Main::class.java)
            startActivity(intent)

            activity?.finish()  // 회원가입 화면 종료 (사용자가 뒤로가기 눌러도 이동못하게 종료시킴)

        }
        return view
    }

    private fun SignUp(email: String, password: String, name: String, birth: String,
                       gender: Gender, state: State, genre: Genre, style: Style,
                       instrument: List<InstrumentExperience>) {

        // TODO: Retrofit API 호출
        val request = SignUpRequest(email, password, name, birth, gender, state, genre, style, instrument)

        RetrofitClient.instance.signup(request).enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                if (response.isSuccessful) {
                    Log.d("디버깅", "회원가입 성공")
                    // TODO: 메인 화면으로 이동
                } else {
                    Log.e("디버깅", "회원가입 실패: ${response.code()}")

                }
            }

            // onFailure 메서드를 제대로 구현
            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                Log.e("디버깅", "회원가입 API 오류: ${t.message}")
                Toast.makeText(requireContext(), "회원가입 오류. 인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        })

        Log.d("디버깅", "<<api>> 회원가입 API 불러오기 성공")
    }
}