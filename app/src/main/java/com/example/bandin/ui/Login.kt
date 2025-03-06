package com.example.bandin.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bandin.R
import com.example.bandin.data.api.ApiClient
import com.example.bandin.data.repository.UserRepository
import com.example.bandin.viewmodel.UserViewModel

class Login : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel

    //login xml에서 변수 가져와 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        // 로그인 버튼 클릭 시
        // 레이아웃 작업 후 코드 수정 요망
        findViewById<Button>(R.id.loginButton).setOnClickListener {
            val email = findViewById<EditText>(R.id.etEmail).text.toString()
            val password = findViewById<EditText>(R.id.etPassword).text.toString()
            userViewModel.login(email, password)
        }

        // 로그인 결과 옵저빙
        userViewModel.loginResult.observe(this) { response ->
            if (response != null) {
                Toast.makeText(this, "로그인 성공! 닉네임: ${response.nickname}", Toast.LENGTH_SHORT).show()
                // 다음 화면으로 이동하는 코드 추가 가능
            } else {
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            }
        }

    }
}