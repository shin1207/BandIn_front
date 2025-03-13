package com.example.bandin.ui

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.bandin.R
import com.example.bandin.viewmodel.SignUpViewModel
import androidx.fragment.app.Fragment


class SignUp : AppCompatActivity() {

    private val signUpViewModel: SignUpViewModel by viewModels() // ViewModel 생성 및 공유
    //액티비티 인 경우 by viewModels() 사용

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // 처음 이메일 입력 화면(Fragment) 띄우기
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SignUpStep1Fragment())
            .commit()
    }


    //다음 Fragment로 넘어가는 함수
    fun goToNextFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null) // 뒤로 가기 가능하게 설정
            .commit()
    }
}

//***
//beginTransaction()과 commit()조합으로 작성 : 초기에 쓴 commit + addToNullStack에서는
//기본적으로 commit 내부함수에서 add어쩌고가 작동하지 않음