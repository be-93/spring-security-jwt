package cus.study.security.auth.application;

import cus.study.security.auth.dto.LoginRequest;
import cus.study.security.auth.dto.LoginResponse;
import cus.study.security.auth.dto.SignUpRequest;
import cus.study.security.auth.dto.SignUpResponse;
import cus.study.security.common.CommonResponse;
import cus.study.security.utils.AcceptanceTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static cus.study.security.utils.AcceptanceFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class AuthServiceTest extends AcceptanceTest {

    private static final String ID = "cusTest";
    private static final String PASS_WORD = "a12341234";

    @Test
    @DisplayName("회원가입 테스트")
    public void signUp(){
        // given
        SignUpRequest given = SignUpRequest.of(ID, PASS_WORD);

        // when
        ExtractableResponse<Response> actual = post("/auth/sign-up", given);

        // then
        CommonResponse.Body response = actual.as(CommonResponse.Body.class);
        SignUpResponse signUpResponse = OBJECT_MAPPER.convertValue(response.getData(), SignUpResponse.class);

        응답_CREATE(response);
        assertThat(signUpResponse.getEmail()).isEqualTo("cusTest");
    }

    @Test
    @DisplayName("토큰을 이용한 API 접근")
    public void tokenTest(){
        // given
        LoginRequest given = LoginRequest.of(ID, PASS_WORD);
        CommonResponse.Body login = post("/auth/login", given).as(CommonResponse.Body.class);
        LoginResponse loginResponse = OBJECT_MAPPER.convertValue(login.getData(), LoginResponse.class);

        // when
        CommonResponse.Body actual = getAuth("/auth/tokenTest", loginResponse.getToken()).as(CommonResponse.Body.class);

        // then
        응답_OK(actual);
    }

}