package com.example.bandin.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bandin.data.model.Gender
import com.example.bandin.data.model.Genre
import com.example.bandin.data.model.Instrument
import com.example.bandin.data.model.InstrumentExperience
import com.example.bandin.data.model.State
import com.example.bandin.data.model.Style


class SignUpViewModel  : ViewModel()  {
    var email: String? = null       //이메일
    var password: String? = null    //비밀번호

    var name: String? = null        //이름
    var birth: String? = null       //생년월일
    var gender: Gender? = null      // 성별 (enum으로 변경)

    var state: State? = null        // 활동 지역 (enum으로 변경)
    var InstrumentExperience: List<InstrumentExperience>? = null // 악기 (복수값을 List로 처리)
    var genre: Genre? = null        // 선호 장르 (enum으로 변경)
    var style: Style? = null        // 활동 유형 (enum으로 변경)

}