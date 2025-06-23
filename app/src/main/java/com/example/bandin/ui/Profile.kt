package com.example.bandin.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bandin.R
import com.example.bandin.data.api.RetrofitClient
import com.example.bandin.data.model.LogoutRequest
import com.example.bandin.data.model.LogoutResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        // TODO : 사용자 정보 불러오기

        //로그아웃 클릭 (API 호출)
        logout.setOnClickListener {

            // TODO : 로그아웃 요청을 위한 사용자 정보 (저장된 값에서 가져올 수 있음)
            val email = "사용자이메일@example.com" // 실제 사용 시 저장된 값에서 불러오세요
            val password = "사용자비밀번호"       // 보안상 비밀번호 저장은 비추. 예시용입니다.

            val request = LogoutRequest(email, password)

            RetrofitClient.instance.logout(request).enqueue(object : Callback<LogoutResponse> {
                override fun onResponse(call: Call<LogoutResponse>, response: Response<LogoutResponse>) {
                    if (response.isSuccessful) {
                        val message = response.body()?.message ?: "로그아웃 성공"
                        Log.d("로그아웃", message)

                        /* (🔐 토큰 및 사용자 정보 제거) 백에서 이미 처리했으면 할 필요 X
                        val prefs = getSharedPreferences("auth", MODE_PRIVATE)
                        prefs.edit().clear().apply() */

                        // 🚀 로그인 화면으로 이동
                        val intent = Intent(this@Profile, Login::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    } else {
                        Log.e("로그아웃 실패", "응답 코드: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                    Toast.makeText(this@Profile, "네트워크 오류", Toast.LENGTH_SHORT).show()
                }
            })
        }


        //TODO : 회원탈퇴 클릭 (API 호출)


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