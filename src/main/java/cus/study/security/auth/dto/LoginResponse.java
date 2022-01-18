package cus.study.security.auth.dto;

import lombok.Getter;

@Getter
public class LoginResponse {

    private String name;
    private String token;

    private LoginResponse(String name, String token) {
        this.name = name;
        this.token = token;
    }

    public LoginResponse of(String name, String token) {
        return new LoginResponse(name, token);
    }
}
