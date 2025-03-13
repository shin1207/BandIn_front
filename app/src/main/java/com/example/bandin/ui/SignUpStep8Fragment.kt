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
import com.example.bandin.viewmodel.SignUpViewModel

class SignUpStep8Fragment : Fragment() {
    lateinit var btnNext: Button

    lateinit var btnGenre1: Button
    lateinit var btnGenre2: Button
    lateinit var btnGenre3: Button
    lateinit var btnGenre4: Button
    lateinit var btnGenre5: Button
    lateinit var btnGenre6: Button
    lateinit var btnGenre7: Button
    lateinit var btnGenre8: Button

    lateinit var genre: String
    private val signUpViewModel: SignUpViewModel by activityViewModels() // ViewModel 공유

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //스텝8을 컨테이너에 띄우도록 view로 설정
        val view = inflater.inflate(R.layout.fragment_sign_up_step8, container, false)


        //사용자가 누른 버튼에 따라 experience의 String 값 설정
        btnGenre1.setOnClickListener {
            genre = "해외락"
        }

        btnGenre2.setOnClickListener {
            genre = "메탈"
        }

        btnGenre3.setOnClickListener {
            genre = "재즈"
        }

        btnGenre4.setOnClickListener {
            genre = "국내인디"
        }

        btnGenre5.setOnClickListener {
            genre = "CCM"
        }

        btnGenre6.setOnClickListener {
            genre = "J-POP"
        }

        btnGenre7.setOnClickListener {
            genre = "팝"
        }

        btnGenre8.setOnClickListener {
            genre = "기타장르"
        }



        //다음 클릭
        btnNext.setOnClickListener {

            //TODO : 값이 입력되지 않은 상태일 때 모달/문구 띄우기 & 다음 스텝 넘어가지 못하게

            //viewModel에 State 값 저장
            signUpViewModel.genre = genre

            //다음 스텝 레이아웃으로 이동
            (activity as? SignUp)?.goToNextFragment(SignUpStep9Fragment())
        }
        return view
    }


}