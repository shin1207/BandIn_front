package com.example.bandin.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bandin.R

class Profile : AppCompatActivity() {

    //유저 프로필 정보
    lateinit var userImage: ImageView
    lateinit var textName: TextView
    lateinit var textBand1: TextView
    lateinit var textBand2: TextView
    lateinit var textBand3: TextView

    lateinit var textState: TextView
    lateinit var textGenre: TextView
    lateinit var textInst1: TextView
    lateinit var textInst2: TextView


    //로그아웃, 회원탈퇴
    lateinit var logout: LinearLayout
    lateinit var userDelete: LinearLayout


    //언더바 메뉴
    lateinit var chat: LinearLayout
    lateinit var bandin: LinearLayout
    lateinit var profile: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        //id 연결
        userImage = findViewById(R.id.userImage)
        textName = findViewById(R.id.textName)

        textBand1 = findViewById(R.id.textBand1)
        textBand2 = findViewById(R.id.textBand2)
        textBand3 = findViewById(R.id.textBand3)

        textState = findViewById(R.id.textState)
        textGenre = findViewById(R.id.textGenre)
        textInst1 = findViewById(R.id.textInst1)
        textInst2 = findViewById(R.id.textInst2)

        logout = findViewById(R.id.logout)
        userDelete = findViewById(R.id.userDelete)

        chat = findViewById(R.id.chat)
        bandin = findViewById(R.id.bandin)
        profile = findViewById(R.id.profile)

        // 사용자 정보 조회

        //로그아웃 클릭 (API 호출)

        //회원탈퇴 클릭 (API 호출)




    }


}