package com.example.bandin.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bandin.R
import com.example.bandin.data.api.RetrofitClient
import com.example.bandin.data.model.LoginRequest
import com.example.bandin.data.model.LoginResponse
import com.example.bandin.viewmodel.LoginViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Login : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    lateinit var edtEmail: EditText
    lateinit var edtPassword :EditText
    lateinit var btnLogin : Button
    lateinit var textSignup : TextView
    lateinit var textFindPassword : TextView //비밀번호찾기 구현 후 추가


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

            //로그인 API 호출 (Retrofit)

            val request = LoginRequest(email, password)

            RetrofitClient.instance.login(request).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        Log.d("로그인 성공", "토큰: ${response.body()?.token}")
                    } else {
                        Log.e("로그인 실패", "오류 코드: ${response.code()}")
                    }
                }


                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(this@Login, "네트워크 오류", Toast.LENGTH_SHORT).show()
                }
            })

        }
    }
}