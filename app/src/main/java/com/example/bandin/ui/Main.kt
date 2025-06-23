package com.example.bandin.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bandin.R

class Main : AppCompatActivity() {

    //검색창
    lateinit var editSearch: EditText

    //필터링

    //나의 밴드
    lateinit var bandImage: ImageView
    lateinit var textBandName: TextView
    lateinit var textBandInfo: TextView

    //새로운밴드만들기 버튼
    lateinit var btnBandCreate: Button

    //Other 밴드 목록
    lateinit var otherBandImage: ImageView
    lateinit var textOtherBandName: TextView
    lateinit var textOtherBandInfo: TextView

    //언더바 메뉴
    lateinit var chat: LinearLayout
    lateinit var bandin: LinearLayout
    lateinit var profile: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //메인화면 불러오기 (api)

    }
}