package com.example.bandin.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.bandin.R
import com.example.bandin.data.api.RetrofitClient
import com.example.bandin.data.model.SignUpRequest
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


        //사용자가 누른 버튼에 따라 experience의 String 값 설정
        btnStyle1.setOnClickListener { style = "취미" }

        btnStyle2.setOnClickListener { style = "세미프로" }

        btnStyle3.setOnClickListener { style = "프로지향" }



        //다음 클릭
        btnNext.setOnClickListener {

            //TODO : 값이 입력되지 않은 상태일 때 모달/문구 띄우기 & 다음 스텝 넘어가지 못하게
            if (style == null) {
                Toast.makeText(requireContext(), "스타일을 선택하세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //viewModel에 State 값 저장
            signUpViewModel.style = style

            val email = signUpViewModel.email
            val password = signUpViewModel.password

            val name = signUpViewModel.name
            val birth = signUpViewModel.birth
            val gender = signUpViewModel.gender

            val state = signUpViewModel.state
            val instruments = signUpViewModel.instruments
            val experience = signUpViewModel.experience
            val genre = signUpViewModel.genre
            val style = signUpViewModel.style


            //모든 값을 받았는지 확인
            if (email != null && password != null && name != null && birth != null
                && gender != null && state != null && instruments != null
                && experience != null && genre != null && style != null)  {

                //<최종 단계> 회원가입 api 불러오기
                sendSignUpRequest(email, password, name, birth, gender, state, instruments, experience, genre, style)
            }

            Log.d("디버깅", "<< 회원가입 성공 >>")

            //메인페이지로 이동
            // TODO : 메인페이지 구현 후 액티비티 연결
        }
        return view
    }

    private fun sendSignUpRequest(email: String, password: String, name: String, birth: String,
                                  gender: String, state: String, instruments: String, experience: String,
                                  genre: String, style: String) {

        // TODO: Retrofit API 호출
        val request = SignUpRequest(email, password, name, birth, gender, state, instruments, experience, genre, style)

        RetrofitClient.instance.signup(request).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Log.d("디버깅", "회원가입 성공")
                    // TODO: 메인 화면으로 이동
                } else {
                    Log.e("디버깅", "회원가입 실패: ${response.code()}")

                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("디버깅", "회원가입 API 오류: ${t.message}")
            }
        })

        Log.d("디버깅", "api -> 회원가입 API 불러오기 성공")
    }
}