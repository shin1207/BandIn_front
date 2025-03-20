package com.example.bandin.data.model

//API호출에 따른 요청/응답 데이터 모델 정의

// 로그인 요청 모델
data class LoginRequest(
    val email: String,
    val password: String
)

// 로그인 응답 모델
data class LoginResponse(
    val token: String,
    val name: String
)


// 회원가입 요청 모델
data class SignUpRequest(
    val email: String,
    val password: String,
    val name: String,
    val birthDate: String,
    val gender: Gender,  // ENUM 타입 사용
    val state: State,  // ENUM 타입 사용
    val style: Style,  // ENUM 타입 사용
    val genre: Genre,  // ENUM 타입 사용
    val instrument: List<InstrumentExperience> // 악기와 경력을 리스트로 저장
)

// ENUM 타입 정의
enum class Gender {
    male, female
}

enum class State {
    서울, 경기, 인천, 충청, 강원, 경상, 전라, 제주  //xml 화면 수정하기
}

enum class Style {
    취미밴드, 세미프로, 프로
}

enum class Genre {
    ROCK, JAZZ, CLASSICAL, POP // 예시 장르 (필요에 따라 수정)
}

// 악기 + 경력 정보를 담을 데이터 모델
data class InstrumentExperience(
    val instrument: Instrument,
    val experience: Experience
)

enum class Instrument {
    보컬,
    기타,
    드럼,
    베이스,
    키보드,
    기타악기,
    없음
}

enum class Experience {
    육개월
}



// 회원가입 > 인증번호 전송 요청모델
data class EmailSendRequest(
    val email: String
)

// 회원가입 > 인증번호 검증 요청모델
data class EmailVerifyRequest(
    val email: String,
    val code: String
)