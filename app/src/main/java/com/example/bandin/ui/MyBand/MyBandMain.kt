package com.example.bandin.ui.MyBand

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bandin.R
import com.example.bandin.ui.BandCreate.BandCreate

class MyBandMain : AppCompatActivity() {
    lateinit var textBandName : TextView
    lateinit var btnSetting : ImageButton

    lateinit var bandImage : ImageView
    lateinit var textGenre : TextView
    lateinit var textAge : TextView
    lateinit var textStyle : TextView
    lateinit var textState : TextView
    lateinit var textStatus : TextView

    lateinit var btnMenu1 : Button
    lateinit var btnMenu2 : Button
    lateinit var btnMenu3 : Button

    lateinit var btnNew : Button

    lateinit var posting : Button
    lateinit var schedule : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_band_main)
        //id 연결
        textBandName = findViewById(R.id.textBandName)
        btnSetting = findViewById(R.id.btnSetting)

        bandImage = findViewById(R.id.bandImage)
        textGenre = findViewById(R.id.textGenre)
        textAge = findViewById(R.id.textAge)
        textStyle = findViewById(R.id.textStyle)
        textState = findViewById(R.id.textState)
        textStatus = findViewById(R.id.textStatus)

        btnMenu1 = findViewById(R.id.btnMenu1)
        btnMenu2 = findViewById(R.id.btnMenu2)
        btnMenu3 = findViewById(R.id.btnMenu3)

        btnNew = findViewById(R.id.btnNew)

        posting = findViewById(R.id.posting)
        schedule = findViewById(R.id.schedule)

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

        //일정 및 공고 추가  <-- "공고 추가" 기능은 밴드장 에게만 활성화
        btnNew.setOnClickListener{
            //일정 추가 OR 공고 추가 MODAL 띄우기
            //TODO : MODAL 만들기

            //일정 추가 선택 -> my_band_new_schedule 이동
            //공고 추가 선택 -> my_band_new_recruit 이동

        }

        //밴드 설정
        btnSetting.setOnClickListener{
            //설정 화면으로 이동
            val intent = Intent(this, BandSetting::class.java)
            startActivity(intent)
        }
        
        //목록에 밴드 공고 & 일정 띄우기

    }
}