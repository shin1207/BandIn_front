package com.example.bandin.ui.MyBand

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.bandin.R
import com.example.bandin.ui.BandCreate.BandCreateStep1Fragment
import com.example.bandin.viewmodel.MyBandViewModel

class MyBand : AppCompatActivity() {

    private val bandCreateViewModel: MyBandViewModel by viewModels() // ViewModel 생성 및 공유
    //액티비티인 경우 by viewModels() 사용

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_band)

        // 처음 이메일 입력 화면(Fragment) 띄우기
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MyBandFragment_Main())
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
