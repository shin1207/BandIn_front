package com.example.bandin.ui.SignUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.bandin.R
import com.example.bandin.data.model.Experience
import com.example.bandin.data.model.Instrument
import com.example.bandin.data.model.InstrumentExperience
import com.example.bandin.viewmodel.SignUpViewModel

class SignUpStep6Fragment : Fragment() {
    lateinit var btnNext: Button

    lateinit var btnInst1: Button
    lateinit var btnInst2: Button
    lateinit var btnInst3: Button
    lateinit var btnInst4: Button
    lateinit var btnInst5: Button
    lateinit var btnInst6: Button

    lateinit var inst: String
    private val signUpViewModel: SignUpViewModel by activityViewModels() // ViewModel 공유

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //스텝6을 컨테이너에 띄우도록 view로 설정
        val view = inflater.inflate(R.layout.fragment_sign_up_step6, container, false)


        //사용자가 누른 버튼에 따라 State의 String 값 설정
        btnInst1.setOnClickListener {
            inst = "기타"
        }

        btnInst2.setOnClickListener {
            inst = "드럼"
        }

        btnInst3.setOnClickListener {
            inst = "보컬"
        }

        btnInst4.setOnClickListener {
            inst = "베이스"
        }

        btnInst5.setOnClickListener {
            inst = "건반악기"
        }

        btnInst6.setOnClickListener {
            inst = ""  //없음
        }


        //다음 클릭
        btnNext.setOnClickListener {

            //TODO : 값이 입력되지 않은 상태일 때 모달/문구 띄우기 & 다음 스텝 넘어가지 못하게

            //viewModel에 inst 정보만 우선 저장 -> List 타입
            val selectedInstruments = listOf(
                InstrumentExperience(Instrument.보컬, null),
                InstrumentExperience(Instrument.기타, null),
                InstrumentExperience(Instrument.드럼, null),
                InstrumentExperience(Instrument.베이스, null),
                InstrumentExperience(Instrument.건반악기, null),
                InstrumentExperience(Instrument.없음, null))

            signUpViewModel.InstrumentExperience = selectedInstruments


            //다음 스텝 레이아웃으로 이동
            (activity as? SignUp)?.goToNextFragment(SignUpStep7Fragment())
        }
        return view
    }
}


