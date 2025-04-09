package com.example.bandin.ui.SignUp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.bandin.R
import com.example.bandin.data.model.Gender
import com.example.bandin.viewmodel.SignUpViewModel

class SignUpStep4Fragment : Fragment() {
    lateinit var btnNext: Button
    lateinit var edtName: EditText
    lateinit var edtBirth: EditText
    lateinit var btnMale: Button
    lateinit var btnFemale: Button
    lateinit var gender: String   // 전역 변수로 선언
    private val signUpViewModel: SignUpViewModel by activityViewModels() // ViewModel 공유

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //스텝4을 컨테이너에 띄우도록 view로 설정
        val view = inflater.inflate(R.layout.fragment_sign_up_step4, container, false)

        btnNext = view.findViewById(R.id.btnNext)
        edtName = view.findViewById(R.id.edtName)
        edtBirth = view.findViewById(R.id.edtBirth)
        btnMale = view.findViewById(R.id.btnMale)
        btnFemale = view.findViewById(R.id.btnFemale)



        //사용자가 누른 버튼에 따라 gender의 String 값 설정
        btnMale.setOnClickListener {
            gender = "male"
        }

        btnFemale.setOnClickListener {
            gender = "female"
        }


        btnNext.setOnClickListener {

            //TODO : 값이 입력되지 않은 상태일 때 모달/문구 띄우기 & 다음 스텝 넘어가지 못하게

            val name = edtName.text.toString()
            val birth = edtBirth.text.toString()

            Log.d("디버깅", "정보 - 이름 : $name, 생년월일 : $birth, 성별 : $gender")


            //viewModel에 이름, 생년월일, 성별 저장
            signUpViewModel.name = name
            signUpViewModel.birth = birth
            signUpViewModel.gender = Gender.valueOf(gender)  // String을 Enum으로 변환하여 저장


            //다음 스텝 레이아웃으로 이동
            (activity as? SignUp)?.goToNextFragment(SignUpStep5Fragment())

        }
        return view
    }
}