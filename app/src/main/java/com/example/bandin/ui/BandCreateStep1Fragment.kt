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
import androidx.lifecycle.ViewModelProvider
import com.example.bandin.R
import com.example.bandin.viewmodel.SignUpViewModel
import androidx.fragment.app.activityViewModels

class BandCreateStep1Fragment : Fragment() {

    private val signUpViewModel: SignUpViewModel by activityViewModels() // ViewModel 공유


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Fragment1을 컨테이너에 띄우도록 view로 설정
        val view = inflater.inflate(R.layout.fragment_band_create_step1, container, false)


        //함수안에서 쓸 변수 설정
        val edtEmail = view.findViewById<EditText>(R.id.edtEmail)
        val btnNext = view.findViewById<Button>(R.id.btnNext)



        //다음 버튼 클릭 리스너 : 이메일을 String으로 ViewModel에 저장
        btnNext.setOnClickListener {

            //TODO : 값이 입력되지 않은 상태일 때 모달/문구 띄우기 & 다음 스텝 넘어가지 못하게

            signUpViewModel.email = edtEmail.text.toString()

            // 회원가입 액티비티 통해서 다음 Fragment로 이동
            (activity as? SignUp)?.goToNextFragment(SignUpStep2Fragment())
        }

        return view
    }
}