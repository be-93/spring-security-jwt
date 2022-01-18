package cus.study.security.auth.application;

import cus.study.security.auth.domain.UserRepository;
import cus.study.security.auth.dto.LoginRequest;
import cus.study.security.auth.dto.LoginResponse;
import cus.study.security.exception.LoginFailBadRequestNameException;
import cus.study.security.exception.LoginFailBadRequestPassWordException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        loginValidate(loginRequest);

        return null;
    }

    private void loginValidate(LoginRequest loginRequest) {
        userRepository.findByUserName(loginRequest.getName())
                .orElseThrow(() -> new LoginFailBadRequestNameException("잘못된 이름 입니다."));

        userRepository.findByUserNameAndPassWord(loginRequest.getName(), loginRequest.getPassWord())
                .orElseThrow(() -> new LoginFailBadRequestPassWordException("잘못된 패스워드 입니다."));
    }
}
