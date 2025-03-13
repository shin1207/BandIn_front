package com.example.bandin.viewmodel

import androidx.lifecycle.ViewModel

//DB 구조 보고 다시 수정

class SignUpViewModel  : ViewModel()  {
    var email: String? = null       //이메일
    var password: String? = null    //비밀번호

    var name: String? = null        //이름
    var birth: String? = null       //생년월일
    var gender: String? = null      //성별

    var state: String? = null       //활동 지역
    var instruments: String? = null //악기 - 복수값 허용해줘야 됨
    var experience: String? = null  //악기 경력
    var genre: String? = null       //선호 장르
    var style: String? = null       //활동 유형

}