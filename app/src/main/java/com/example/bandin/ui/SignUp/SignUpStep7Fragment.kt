package com.example.bandin.ui.SignUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.bandin.R
import com.example.bandin.data.model.Experience
import com.example.bandin.data.model.Instrument
import com.example.bandin.data.model.InstrumentExperience
import com.example.bandin.viewmodel.SignUpViewModel

class SignUpStep7Fragment : Fragment() {
    lateinit var btnNext: Button

    lateinit var btnExp1: Button
    lateinit var btnExp2: Button
    lateinit var btnExp3: Button
    lateinit var btnExp4: Button
    lateinit var btnExp5: Button

    lateinit var textInst1: TextView
    lateinit var textInst2: TextView

    lateinit var exp1: String //1번째 악기 경력
    lateinit var exp2: String //2번째 악기 경력


    private val signUpViewModel: SignUpViewModel by activityViewModels() // ViewModel 공유

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //스텝7을 컨테이너에 띄우도록 view로 설정
        val view = inflater.inflate(R.layout.fragment_sign_up_step7, container, false)

        // TODO : 앞 페이지에서 사용자가 선택한 악기들의 exp를 선택하도록 추가 수정해야 함

        // 로직 :
        // 선택한 악기가 1개이면 -> secondLayout 안보이게 하기 & textInst1을 해당 악기로 텍스트변경
        // 선택한 악기가 2개이면 -> firstLayout, secondLayout 두개 다 보이게 하기 & textInst1, 2 변경

        // List InstrumentExperience 에서 Instrument를 추출
        val instruments = signUpViewModel.InstrumentExperience?.map { it.instrument } ?: emptyList()

        // 악기의 개수에 따라 레이아웃 및 텍스트 변경
        if (instruments.size == 1) {
            // 악기가 1개인 경우
            textInst1.text = instruments[0]?.name  // 악기 이름을 텍스트뷰에 설정
            textInst2.visibility = View.GONE  // 두 번째 악기 관련 레이아웃 숨기기
        }
        if (instruments.size == 2) {
            // 악기가 2개인 경우
            textInst1.text = instruments[0]?.name  // 첫 번째 악기
            textInst2.text = instruments[1]?.name  // 두 번째 악기
            textInst2.visibility = View.VISIBLE  // 두 번째 악기 관련 레이아웃 보이기
        }


        //첫번째 악기 경력 선택
        btnExp1.setOnClickListener { exp1 = "one_year" }

        btnExp2.setOnClickListener { exp1 = "three_year" } //1년이상3년미만

        btnExp3.setOnClickListener { exp1 = "five_year" }  //3년이상5년미만

        btnExp4.setOnClickListener { exp1 = "ten_year" }  //5년이상10년미만

        btnExp5.setOnClickListener { exp1 = "more_than_ten"}  //10년이상


        //두번째 악기 경력 선택
        btnExp1.setOnClickListener { exp2 = "one_year" }

        btnExp2.setOnClickListener { exp2 = "three_year" } //1년이상3년미만

        btnExp3.setOnClickListener { exp2 = "five_year" }  //3년이상5년미만

        btnExp4.setOnClickListener { exp2 = "ten_year" }  //5년이상10년미만

        btnExp5.setOnClickListener { exp2 = "more_than_ten"}  //10년이상


        btnNext.setOnClickListener {

            //TODO : 값이 입력되지 않은 상태일 때 모달/문구 띄우기 & 다음 스텝 넘어가지 못하게

            // 선택된 악기 경력을 InstrumentExperience 객체로 만들어 ViewModel에 저장
            val selectedExperience = mutableListOf<InstrumentExperience>()

            if (instruments.isNotEmpty()) {
                // 첫 번째 악기와 경력 정보 추가
                selectedExperience.add(InstrumentExperience(instrument = instruments[0], experience = Experience.valueOf(exp1)))
            }
            if (instruments.size > 1) {
                // 두 번째 악기와 경력 정보 추가
                selectedExperience.add(InstrumentExperience(instrument = instruments[1], experience = Experience.valueOf(exp2)))
            }

            // SignUpViewModel에 경력 정보를 저장
            signUpViewModel.InstrumentExperience = selectedExperience


            //다음 스텝 레이아웃으로 이동
            (activity as? SignUp)?.goToNextFragment(SignUpStep8Fragment())
        }
        return view
    }

}