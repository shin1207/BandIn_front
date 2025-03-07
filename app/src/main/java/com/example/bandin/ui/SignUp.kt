package com.example.bandin.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.bandin.R

class SignUp : AppCompatActivity() {

    lateinit var btnNext : Button
    lateinit var edtEmail : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btnNext = findViewById(R.id.btnNext)
        edtEmail = findViewById(R.id.edtEmail)



    }
}