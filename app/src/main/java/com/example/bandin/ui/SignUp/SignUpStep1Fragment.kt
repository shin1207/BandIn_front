package com.example.bandin.ui.SignUp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.bandin.R
import com.example.bandin.data.api.RetrofitClient
import com.example.bandin.viewmodel.SignUpViewModel
import androidx.fragment.app.activityViewModels
import com.example.bandin.data.model.EmailSendRequest
import com.example.bandin.data.model.EmailSendResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpStep1Fragment : Fragment() {

    private val signUpViewModel: SignUpViewModel by activityViewModels() // ViewModel 공유


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Fragment1을 컨테이너에 띄우도록 view로 설정
        val view = inflater.inflate(R.layout.fragment_sign_up_step1, container, false)

        //함수안에서 쓸 변수 설정
        val edtEmail = view.findViewById<EditText>(R.id.edtBandName)
        val btnNext = view.findViewById<Button>(R.id.btnNext)



        //다음 버튼 클릭 리스너 : 이메일을 String으로 ViewModel에 저장
        btnNext.setOnClickListener {

            //TODO : 값이 입력되지 않은 상태일 때 모달/문구 띄우기 & 다음 스텝 넘어가지 못하게

            val email = edtEmail.text.toString()
            signUpViewModel.email = email

            //TODO : 이메일로 인증번호 전송 API 호출
            emailSend(email)
            Log.d("디버깅", "<< 회원가입 / 이메일 인증번호 전송 성공>>")


            // 회원가입 액티비티 통해서 다음 Fragment로 이동
            (activity as? SignUp)?.goToNextFragment(SignUpStep2Fragment())
        }

        return view
    }

    private fun emailSend(email: String) {
        // 요청 바디 생성
        val request = EmailSendRequest(email)

        // Retrofit API 호출
        RetrofitClient.instance.emailSend(request).enqueue(object : Callback<EmailSendResponse> {
            override fun onResponse(call: Call<EmailSendResponse>, response: Response<EmailSendResponse>) {
                if (response.isSuccessful) {
                    Log.d("디버깅", "이메일 전송 요청 성공: ${response.body()?.message}")

                } else {
                    Log.e("디버깅", "이메일 전송 요청 실패: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<EmailSendResponse>, t: Throwable) {
                Log.e("디버깅", "이메일 전송 API 오류: ${t.message}")
            }
        })

        Log.d("디버깅", "api -> 이메일 인증 API 불러오기 성공")
    }
}