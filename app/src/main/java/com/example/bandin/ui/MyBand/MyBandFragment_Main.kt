package com.example.bandin.ui.MyBand

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.bandin.R
import com.example.bandin.ui.BandCreate.BandCreate
import com.example.bandin.ui.BandCreate.BandCreateStep2Fragment
import com.example.bandin.viewmodel.MyBandViewModel

class MyBandFragment_Main : Fragment() {

    private val MyBandViewModel: MyBandViewModel by activityViewModels() // ViewModel 공유

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //fragment_my_band_main을 컨테이너에 띄우도록 view로 설정
        val view = inflater.inflate(R.layout.fragment_my_band_main, container, false)

        val textBandName = view.findViewById<TextView>(R.id.textBandName)
        val btnSetting = view.findViewById<ImageButton>(R.id.btnSetting)

        val bandImage = view.findViewById<ImageView>(R.id.bandImage)
        val textGenre = view.findViewById<TextView>(R.id.textGenre)
        val textAge = view.findViewById<TextView>(R.id.textAge)
        val textStyle = view.findViewById<TextView>(R.id.textStyle)
        val textState = view.findViewById<TextView>(R.id.textState)
        val textStatus = view.findViewById<TextView>(R.id.textStatus)

        val btnMenu1 = view.findViewById<Button>(R.id.btnMenu1)
        val btnMenu2 = view.findViewById<Button>(R.id.btnMenu2)
        val btnMenu3 = view.findViewById<Button>(R.id.btnMenu3)

        val btnNew = view.findViewById<Button>(R.id.btnNew)

        val posting = view.findViewById<Button>(R.id.posting)
        val schedule = view.findViewById<Button>(R.id.schedule)

        //밴드 정보 띄우기
        //DB에서 밴드 정보 가져오기
        val genre = ""
        val age = ""
        val style = ""
        val state = ""
        val state_detail = ""
        val status = "" //참여중 OR 지원하기

        //정보 반영
        textGenre.text = "장르 : $genre"
        textAge.text = "연령대 : $age"
        textStyle.text = "지향 : $style"
        textState.text = "지역 : $state $state_detail"
        textStatus.text = status

        //TODO : 밴드 공고 & 일정 띄우기


        //일정 및 공고 추가  <-- "공고 추가" 기능은 밴드장 에게만 활성화
        btnNew.setOnClickListener{
            //TODO : 일정 추가 OR 공고 추가 MODAL 띄우기
            //TODO : MODAL 만들기

            //일정 추가 선택 -> my_band_new_schedule 이동
            //공고 추가 선택 -> my_band_new_recruit 이동

        }



        //밴드 설정
        btnSetting.setOnClickListener{
            //MyBandFragment_Setting 으로 이동
            (activity as? MyBand)?.goToNextFragment(MyBandFragment_Setting())
        }
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