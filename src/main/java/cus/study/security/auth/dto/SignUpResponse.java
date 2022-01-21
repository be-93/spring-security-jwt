package cus.study.security.auth.dto;

import lombok.Data;

@Data
public class SignUpResponse {

    private String email;

    protected SignUpResponse() {
    }

    public SignUpResponse(String email) {
        this.email = email;
    }

    public static SignUpResponse of(String email) {
        return new SignUpResponse(email);
    }
}
