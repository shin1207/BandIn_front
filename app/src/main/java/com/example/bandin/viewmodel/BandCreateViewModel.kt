package com.example.bandin.viewmodel

import androidx.lifecycle.ViewModel

class BandCreateViewModel : ViewModel() {
    var name: String? = null            //밴드 이름

    var state: String? = null           //밴드 활동지역
    var state_detail: String? = null    //밴드 활동지역 > 세부지역
    var genre: String? = null           //밴드 장르
    var style: String? = null           //밴드 활동유형
}