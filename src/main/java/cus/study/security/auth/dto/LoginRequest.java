package cus.study.security.auth.dto;

import lombok.Getter;

@Getter
public class LoginRequest {

    private String name;
    private String passWord;

    public LoginRequest(String name, String passWord) {
        this.name = name;
        this.passWord = passWord;
    }
}
