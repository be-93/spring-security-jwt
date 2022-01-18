package cus.study.security.auth.application;

import cus.study.security.auth.domain.UserRepository;
import cus.study.security.auth.dto.LoginRequest;
import cus.study.security.auth.dto.LoginResponse;
import cus.study.security.exception.LoginFailException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        userRepository.findByUserNameAndPassWord(loginRequest.getName(), loginRequest.getPassWord())
                .orElseThrow(LoginFailException::new);

        return null;
    }
}
