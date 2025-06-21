package com.example.bandin.ui.MyBand

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bandin.R

class MyBandMain : AppCompatActivity() {
    lateinit var btnPrevious : ImageButton
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

    }
}