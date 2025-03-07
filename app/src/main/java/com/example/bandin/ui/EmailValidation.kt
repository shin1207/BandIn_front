package com.example.bandin.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bandin.R

class EmailValidation : AppCompatActivity() {

    lateinit var btnValidate : Button
    lateinit var edtNum1 : EditText
    lateinit var edtNum2 : EditText
    lateinit var edtNum3 : EditText
    lateinit var edtNum4 : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_validation)

        btnValidate = findViewById(R.id.btnValidate)
        edtNum1 = findViewById(R.id.edtNum1)
        edtNum2 = findViewById(R.id.edtNum2)
        edtNum3 = findViewById(R.id.edtNum3)
        edtNum4 = findViewById(R.id.edtNum4)



        //확인 버튼 클릭
        btnValidate.setOnClickListener {
            //키보드 내리기

            // 인증번호 합치기
            val num1 = edtNum1.text?.toString()?.toIntOrNull() ?: 0
            val num2 = edtNum2.text?.toString()?.toIntOrNull() ?: 0
            val num3 = edtNum3.text?.toString()?.toIntOrNull() ?: 0
            val num4 = edtNum4.text?.toString()?.toIntOrNull() ?: 0

            val validation_num = "$num1$num2$num3$num4"

            // 변환된 4자리 숫자 확인
            Log.d("인증번호 변환", "이메일 인증번호: $validation_num")

            //인증번호 검증 (api)
            Toast.makeText(this,"이메일이 검증되었습니다.", Toast.LENGTH_SHORT).show()

            //인증번호 검증 성공 후
            //버튼 '확인' -> '다음'으로 text 변경
            btnValidate.text = "다음"

            //버튼 클릭 -> 다음페이지로 이동
            btnValidate.setOnClickListener{
                Log.d("<다음>버튼 클릭", "다음 페이지로 이동합니다.")
            }


        }





    }
}