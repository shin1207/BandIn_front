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

class BandCreateStep3Fragment : Fragment() {

    lateinit var btnNext: Button
    lateinit var btnBack: Button

    lateinit var btnGenre1: Button
    lateinit var btnGenre2: Button
    lateinit var btnGenre3: Button
    lateinit var btnGenre4: Button
    lateinit var btnGenre5: Button
    lateinit var btnGenre6: Button
    lateinit var btnGenre7: Button


    lateinit var genre: String      //String으로 저장용 변수

    private val bandCreateViewModel: BandCreateViewModel by activityViewModels() // ViewModel 공유

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //스텝5을 컨테이너에 띄우도록 view로 설정
        val view = inflater.inflate(R.layout.fragment_band_create_step3, container, false)


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

        //이전 클릭
        btnBack.setOnClickListener {
            //Fragement1로 이동 (DB 저장은 초기화됨)
        }


        //다음 클릭
        btnNext.setOnClickListener {

            //TODO : 값이 입력되지 않은 상태일 때 모달/문구 띄우기 & 다음 스텝 넘어가지 못하게

            //viewModel에 state, state_detail  값 저장
            bandCreateViewModel.genre = genre

            //밴드만들기 액티비티 통해서 다음 스텝 레이아웃으로 이동
            (activity as? BandCreate)?.goToNextFragment(BandCreate4Fragment())
        }
        return view
    }
}
