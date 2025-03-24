package com.example.bandin.ui.BandCreate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.bandin.R
import com.example.bandin.viewmodel.BandCreateViewModel

class BandCreateStep2Fragment : Fragment() {

    lateinit var btnNext: Button
    lateinit var btnBack: Button

    lateinit var btnState1: Button
    lateinit var btnState2: Button
    lateinit var btnState3: Button
    lateinit var btnState4: Button
    lateinit var btnState5: Button
    lateinit var btnState6: Button

    lateinit var edtStateDetail : EditText
    lateinit var state: String      //String으로 저장용 변수

    private val bandCreateViewModel: BandCreateViewModel by activityViewModels() // ViewModel 공유

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //스텝5을 컨테이너에 띄우도록 view로 설정
        val view = inflater.inflate(R.layout.fragment_band_create_step2, container, false)


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


        //이전 클릭
        btnBack.setOnClickListener {
            //Fragement1로 이동 (DB 저장은 초기화됨)
        }


        //다음 클릭
        btnNext.setOnClickListener {

            //TODO : 값이 입력되지 않은 상태일 때 모달/문구 띄우기 & 다음 스텝 넘어가지 못하게

            //viewModel에 state, state_detail  값 저장
            bandCreateViewModel.state = state
            bandCreateViewModel.state_detail = edtStateDetail.text.toString()

            //밴드만들기 액티비티 통해서 다음 스텝 레이아웃으로 이동
            (activity as? BandCreate)?.goToNextFragment(BandCreate3Fragment())
        }
        return view
    }
}
