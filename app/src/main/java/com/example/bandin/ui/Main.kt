package com.example.bandin.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bandin.R
import com.example.bandin.ui.BandCreate.BandCreate

class Main : AppCompatActivity() {

    //검색창
    lateinit var editSearch: EditText

    //필터링

    //나의 밴드
    lateinit var myBandImage: ImageView
    lateinit var myBandName: TextView
    lateinit var myBandInfo: TextView

    //새로운밴드만들기 버튼
    lateinit var btnBandCreate: Button

    // 밴드 전체 목록
    lateinit var bandImage: ImageView
    lateinit var bandName: TextView
    lateinit var bandInfo: TextView
    lateinit var textState: TextView

    //네비게이션 메뉴
    lateinit var chat: LinearLayout
    lateinit var bandin: LinearLayout
    lateinit var profile: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //id 연결
        editSearch = findViewById(R.id.editSearch)

        myBandImage = findViewById(R.id.myBandImage)
        myBandName = findViewById(R.id.myBandName)
        myBandInfo = findViewById(R.id.myBandInfo)

        btnBandCreate = findViewById(R.id.btnBandCreate)

        bandImage = findViewById(R.id.bandImage)
        bandName = findViewById(R.id.bandName)
        bandInfo = findViewById(R.id.bandInfo)
        textState = findViewById(R.id.textState)

        chat = findViewById(R.id.chat)
        bandin = findViewById(R.id.bandin)
        profile = findViewById(R.id.profile)


        //TODO: 검색창 -> 검색어 입력 -> 다른밴드 영역에 검색 결과 띄우기 (API)

        //TODO : 나의 밴드 정보 불러오기 (API)

        // 밴드만들기 버튼 -> 밴드만들기 화면으로 이동
        btnBandCreate.setOnClickListener{
            val intent = Intent(this, BandCreate::class.java)
            startActivity(intent)
        }


        //네비게이션 메뉴
        chat.setOnClickListener{
            // TODO: activity_chat_main 으로 이동
        }

        bandin.setOnClickListener{
            val intent = Intent(this, Main::class.java)
            startActivity(intent)
        }

        profile.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }



    }
}