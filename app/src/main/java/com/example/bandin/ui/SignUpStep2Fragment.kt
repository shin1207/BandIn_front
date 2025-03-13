package com.example.bandin.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.bandin.R
import com.example.bandin.viewmodel.SignUpViewModel

class SignUpStep2Fragment : Fragment()  {

    lateinit var btnValidate : Button
    lateinit var edtNum1 : EditText
    lateinit var edtNum2 : EditText
    lateinit var edtNum3 : EditText
    lateinit var edtNum4 : EditText

    //viewModel 사용하게 되면 코드로 쓰기
    //private val signUpViewModel: SignUpViewModel by activityViewModels() // ViewModel 공유

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //스텝2을 컨테이너에 띄우도록 view로 설정
        val view = inflater.inflate(R.layout.fragment_sign_up_step2, container, false)

        //함수안에서 쓸 변수 설정
        btnValidate = view.findViewById(R.id.btnValidate)
        edtNum1 = view.findViewById(R.id.edtNum1)
        edtNum2 = view.findViewById(R.id.edtNum2)
        edtNum3 = view.findViewById(R.id.edtNum3)
        edtNum4 = view.findViewById(R.id.edtNum4)

        //다음 버튼 클릭 리스너 : 이메일을 String으로 ViewModel에 저장
        btnValidate.setOnClickListener {
            //키보드 내리기

            // 인증번호 합치기
            val num1 = edtNum1.text?.toString()?.toIntOrNull() ?: 0
            val num2 = edtNum2.text?.toString()?.toIntOrNull() ?: 0
            val num3 = edtNum3.text?.toString()?.toIntOrNull() ?: 0
            val num4 = edtNum4.text?.toString()?.toIntOrNull() ?: 0

            val validation_num = "$num1$num2$num3$num4"

            // 변환된 4자리 숫자 확인
            Log.d("디버깅", "(인증번호 변환) 이메일 인증번호: $validation_num")


            //인증번호 검증 api 불러오기 (validation_num)


            //인증번호 검증 성공 후
            Log.d("디버깅", "<< 이메일이 검증되었습니다 >>")

            //버튼 '확인' -> '다음'으로 text 변경
            btnValidate.text = "다음"

            //버튼 클릭 -> 다음페이지로 이동
            btnValidate.setOnClickListener{
                Log.d("디버깅", "다음 페이지로 이동합니다.")
                // 회원가입 액티비티 통해서 다음 Fragment로 이동
                (activity as? SignUp)?.goToNextFragment(SignUpStep3Fragment())
            }
        }
        return view
    }
}