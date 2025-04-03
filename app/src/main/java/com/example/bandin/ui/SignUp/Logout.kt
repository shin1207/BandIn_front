package com.example.bandin.ui.SignUp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bandin.R

class Logout : AppCompatActivity() {

    //로그아웃 다이얼로그 팝업창 띄우기

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout_dialog)
    }
}