package com.example.bandin.ui.BandCreate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.bandin.R
import com.example.bandin.viewmodel.BandCreateViewModel

class BandCreateStep4Fragment : Fragment() {

    lateinit var btnNext: Button
    lateinit var btnBack: Button

    lateinit var btnStyle1: Button
    lateinit var btnStyle2: Button
    lateinit var btnStyle3: Button


    lateinit var style: String      //String으로 저장용 변수

    private val bandCreateViewModel: BandCreateViewModel by activityViewModels() // ViewModel 공유

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //스텝5을 컨테이너에 띄우도록 view로 설정
        val view = inflater.inflate(R.layout.fragment_band_create_step4, container, false)

        //사용자가 누른 버튼에 따라 style의 String 값 설정
        btnStyle1.setOnClickListener { style = "취미" }

        btnStyle2.setOnClickListener { style = "세미프로" }

        btnStyle3.setOnClickListener { style = "프로지향" }



        //이전 클릭
        btnBack.setOnClickListener {
            //이전 Fragement로 이동 (DB 저장은 초기화됨)
        }


        //다음 클릭
        btnNext.setOnClickListener {

            //TODO : 값이 입력되지 않은 상태일 때 모달/문구 띄우기 & 다음 스텝 넘어가지 못하게

            //viewModel에 style 값 저장
            bandCreateViewModel.style = style

            //최종 저장 단계
            val name = bandCreateViewModel.name
            val state = bandCreateViewModel.state
            val state_detail = bandCreateViewModel.state_detail
            val genre = bandCreateViewModel.genre
            val style = bandCreateViewModel.style

            // TODO : 밴드만들기 API 불러오기
        }
        return view
    }
}
