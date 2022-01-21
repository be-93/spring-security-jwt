package cus.study.security.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import cus.study.security.auth.dto.Token;
import cus.study.security.common.CommonResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

public class AcceptanceFixture {
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final String AUTHORIZATION_HEADER = "Authorization";

    public static ExtractableResponse<Response> get(String path, Token token) {
        return RestAssured.given().log().all()
                .auth().oauth2(token.getAccessToken())
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(path)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> post(String path, Object param) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(param)
                .when()
                .post(path)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> getAuth(String path, Token token) {
        return RestAssured.given().log().all()
                .header(AUTHORIZATION_HEADER, token.getAccessToken())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post(path)
                .then().log().all()
                .extract();
    }

    public static void 응답_OK(CommonResponse.Body response) {
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());
    }

    public static void 응답_CREATE(CommonResponse.Body response) {
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    public static void 응답_NO_CONTENT(CommonResponse.Body response) {
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    public static void 응답_BAD_REQUEST(CommonResponse.Body response) {
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    public static void 응답_SERVER_ERROR(CommonResponse.Body response) {
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}
