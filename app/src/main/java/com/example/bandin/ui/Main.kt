package com.example.bandin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bandin.R

class Main : AppCompatActivity() {
    //메인화면 - DB에서 회원 정보 조회 후 가져오기

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}