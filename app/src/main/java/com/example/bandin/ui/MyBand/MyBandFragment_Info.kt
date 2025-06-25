package com.example.bandin.ui.MyBand

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.bandin.R
import com.example.bandin.ui.BandCreate.BandCreate
import com.example.bandin.viewmodel.MyBandViewModel

class MyBandFragment_Info : Fragment() {
    //xml의 변수 정의
    lateinit var btnMenu1: Button
    lateinit var btnMenu2: Button
    lateinit var btnMenu3: Button
    lateinit var btnSetting: Button

    private val MyBandViewModel: MyBandViewModel by activityViewModels() // ViewModel 공유

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //fragment_my_band_main을 컨테이너에 띄우도록 view로 설정
        val view = inflater.inflate(R.layout.fragment_my_band_info, container, false)

        //TODO : 밴드 정보 띄우기 + 밴드 한줄 소개글


        //밴드 설정 선택
        btnSetting.setOnClickListener{
            //MyBandFragment_Setting 으로 이동
            (activity as? MyBand)?.goToNextFragment(MyBandFragment_Setting())
        }

        //TODO : 목록에 밴드 활동 목록 띄우기

        //메뉴 선택
        btnMenu1.setOnClickListener{
            //MyBandFragment_Main 으로 이동
            (activity as? MyBand)?.goToNextFragment(MyBandFragment_Main())
        }
        btnMenu2.setOnClickListener{
            //MyBandFragment_Member 으로 이동
            (activity as? MyBand)?.goToNextFragment(MyBandFragment_Member())
        }
        btnMenu3.setOnClickListener{
            //MyBandFragment_Info 으로 이동
            (activity as? MyBand)?.goToNextFragment(MyBandFragment_Info())
        }
        return view

    }

}
