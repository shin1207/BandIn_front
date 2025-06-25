package com.example.bandin.ui.MyBand

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.bandin.R
import com.example.bandin.viewmodel.MyBandViewModel

class MyBandFragment_Setting :Fragment() {

    private val MyBandViewModel: MyBandViewModel by activityViewModels() // ViewModel 공유

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //fragment_my_band_main을 컨테이너에 띄우도록 view로 설정
        val view = inflater.inflate(R.layout.fragment_my_band_setting, container, false)

        //변수 설정

        //밴드정보 변경

        //밴드탈퇴


        return view
    }

}