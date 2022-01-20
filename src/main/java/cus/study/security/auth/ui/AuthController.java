package cus.study.security.auth.ui;

import cus.study.security.auth.application.AuthService;
import cus.study.security.auth.dto.LoginRequest;
import cus.study.security.auth.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody SignUpRequest sign) {
        return authService.signUp(sign);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
