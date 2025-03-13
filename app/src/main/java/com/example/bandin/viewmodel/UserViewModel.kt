package com.example.bandin.viewmodel

//UI와 데이터를 연결하는 역할
//UserRepository에서 데이터를 가져와 화면에서 사용하도록 제공

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bandin.data.model.LoginResponse
import com.example.bandin.data.repository.UserRepository

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    // 로그인 결과를 저장할 LiveData
    private val _loginResult = MutableLiveData<LoginResponse?>()
    val loginResult: LiveData<LoginResponse?> = _loginResult

    // 회원가입 결과를 저장할 LiveData
    private val _signupResult = MutableLiveData<Boolean>()
    val signupResult: LiveData<Boolean> = _signupResult

    // 로그인 함수
    fun login(email: String, password: String) {
        userRepository.login(email, password) { response ->
            _loginResult.postValue(response) // LiveData 업데이트
        }
    }

}