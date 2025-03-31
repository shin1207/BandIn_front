package com.example.bandin.ui.SignUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.bandin.R
import com.example.bandin.data.model.State
import com.example.bandin.viewmodel.SignUpViewModel

class SignUpStep5Fragment : Fragment() {

    lateinit var btnNext: Button

    lateinit var btnState1: Button
    lateinit var btnState2: Button
    lateinit var btnState3: Button
    lateinit var btnState4: Button
    lateinit var btnState5: Button
    lateinit var btnState6: Button
    lateinit var btnState7: Button

    lateinit var state: String
    private val signUpViewModel: SignUpViewModel by activityViewModels() // ViewModel 공유

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //스텝5을 컨테이너에 띄우도록 view로 설정
        val view = inflater.inflate(R.layout.fragment_sign_up_step5, container, false)


        //사용자가 누른 버튼에 따라 State의 String 값 설정
        btnState1.setOnClickListener {
            state = "서울"
        }

        btnState2.setOnClickListener {
            state = "경기"
        }

        btnState3.setOnClickListener {
            state = "충청"
        }

        btnState4.setOnClickListener {
            state = "강원"
        }

        btnState5.setOnClickListener {
            state = "경상"
        }

        btnState6.setOnClickListener {
            state = "전라"
        }

        btnState7.setOnClickListener {
            state = "제주"
        }


        //다음 클릭
        btnNext.setOnClickListener {

            //TODO : 값이 입력되지 않은 상태일 때 모달/문구 띄우기 & 다음 스텝 넘어가지 못하게

            //viewModel에 State 값 저장
            signUpViewModel.state = State.valueOf(state)  // String을 Enum으로 변환하여 저장

            //다음 스텝 레이아웃으로 이동
            (activity as? SignUp)?.goToNextFragment(SignUpStep6Fragment())
        }
        return view
    }
}
