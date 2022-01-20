package cus.study.security.auth.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Builder
public class LoginResponse {

    private String loginToken;

}
