package com.example.bandin.data.repository


//API에서 받아온 데이터를 처리하는 역할
//API에서 가져온 데이터를 ViewModel에서 쉽게 활용할 수 있도록 중간 역할 수행

//1.백엔드 API 호출 (Retrofit) -> API 응답을 받아서 callback함수로 ViewModel에 전달
//2. ViewModel과 통신하여 UI에서 사용할 데이터 제공
//3. 데이터 캐싱 및 변환 (필요한경우)



import com.example.bandin.data.api.AuthService
import com.example.bandin.data.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(private val authService: AuthService) {

    // 로그인 요청
    fun login(email: String, password: String, callback: (LoginResponse?) -> Unit) {
        val request = LoginRequest(email, password)
        authService.login(request).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    callback(response.body()) // 로그인 성공 시 결과 반환
                } else {
                    callback(null) // 로그인 실패 시 null 반환
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                callback(null) // 네트워크 오류 등 실패 시 null 반환
            }
        })
    }

    // 회원가입 요청
    fun signup(email: String, password: String, nickname: String, callback: (Boolean) -> Unit) {
        val request = SignupRequest(email, password, nickname)
        authService.signup(request).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                callback(response.isSuccessful) // 회원가입 성공 여부 반환
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                callback(false) // 실패 시 false 반환
            }
        })
    }
}