package com.example.bandin.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bandin.R
import com.example.bandin.data.api.ApiClient
import com.example.bandin.data.repository.UserRepository
import com.example.bandin.viewmodel.UserViewModel

class Login : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    lateinit var edtEmail: EditText
    lateinit var edtPassword :EditText
    lateinit var btnLogin : Button
    lateinit var textSignup : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        textSignup = findViewById(R.id.textSignup)
        btnLogin = findViewById(R.id.btnLogin)

        // TODO: 비밀번호 입력 자릿수 제한 두기

        btnLogin.setOnClickListener{
            //이메일, 비밀번호 입력값 가져오기
            val email = edtEmail.text?.toString() ?: ""
            val password = edtPassword.text?.toString() ?: ""

            Log.d("로그인", "이메일 : $email")
            Log.d("로그인", "비밀번호 : $password")
        }
    }
}