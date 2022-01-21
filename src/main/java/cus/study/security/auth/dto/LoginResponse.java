package cus.study.security.auth.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
public class LoginResponse {

    private String email;
    private Token token;

    protected LoginResponse() {
    }

    @Builder
    public LoginResponse(String email, Token token) {
        this.email = email;
        this.token = token;
    }

    public static LoginResponse of(String email, Token token) {
        return new LoginResponse(email, token);
    }
}
