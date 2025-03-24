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

class SignUpStep6Fragment : Fragment() {
    lateinit var btnNext: Button

    lateinit var btnInst1: Button
    lateinit var btnInst2: Button
    lateinit var btnInst3: Button
    lateinit var btnInst4: Button
    lateinit var btnInst5: Button
    lateinit var btnInst6: Button

    lateinit var instruments: String
    private val signUpViewModel: SignUpViewModel by activityViewModels() // ViewModel 공유

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //스텝6을 컨테이너에 띄우도록 view로 설정
        val view = inflater.inflate(R.layout.fragment_sign_up_step6, container, false)


        //사용자가 누른 버튼에 따라 State의 String 값 설정
        btnInst1.setOnClickListener {
            instruments = "기타"
        }

        btnInst2.setOnClickListener {
            instruments = "드럼"
        }

        btnInst3.setOnClickListener {
            instruments = "보컬"
        }

        btnInst4.setOnClickListener {
            instruments = "베이스"
        }

        btnInst5.setOnClickListener {
            instruments = "건반악기"
        }

        btnInst6.setOnClickListener {
            instruments = ""
        }


        //다음 클릭
        btnNext.setOnClickListener {

            //TODO : 값이 입력되지 않은 상태일 때 모달/문구 띄우기 & 다음 스텝 넘어가지 못하게

            //viewModel에 State 값 저장
            signUpViewModel.instruments = instruments

            //다음 스텝 레이아웃으로 이동
            (activity as? SignUp)?.goToNextFragment(SignUpStep7Fragment())
        }
        return view
    }
}


