package com.example.bandin.ui.SignUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.bandin.R
import com.example.bandin.viewmodel.SignUpViewModel

class SignUpStep7Fragment : Fragment() {
    lateinit var btnNext: Button

    lateinit var btnExp1: Button
    lateinit var btnExp2: Button
    lateinit var btnExp3: Button
    lateinit var btnExp4: Button
    lateinit var btnExp5: Button

    lateinit var instruments: String
    private val signUpViewModel: SignUpViewModel by activityViewModels() // ViewModel 공유

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //스텝7을 컨테이너에 띄우도록 view로 설정
        val view = inflater.inflate(R.layout.fragment_sign_up_step7, container, false)


        //사용자가 누른 버튼에 따라 experience의 String 값 설정
        btnExp1.setOnClickListener {
            instruments = "1년이하"
        }

        btnExp2.setOnClickListener {
            instruments = "1년이상3년미만"
        }

        btnExp3.setOnClickListener {
            instruments = "3년이상5년미만"
        }

        btnExp4.setOnClickListener {
            instruments = "5년이상10년미만"
        }

        btnExp5.setOnClickListener {
            instruments = "10년이상"
        }



        //다음 클릭
        btnNext.setOnClickListener {

            //TODO : 값이 입력되지 않은 상태일 때 모달/문구 띄우기 & 다음 스텝 넘어가지 못하게

            //viewModel에 State 값 저장
            signUpViewModel.instruments = instruments


            //다음 스텝 레이아웃으로 이동
            (activity as? SignUp)?.goToNextFragment(SignUpStep8Fragment())
        }
        return view
    }

}