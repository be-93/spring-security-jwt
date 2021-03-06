package cus.study.security.auth.application;

import cus.study.security.auth.domain.Member;
import cus.study.security.auth.domain.MemberRepository;
import cus.study.security.auth.dto.*;
import cus.study.security.common.CommonResponse;
import cus.study.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncode;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public ResponseEntity<?> signUp(SignUpRequest sign) {
        if (memberRepository.existsByEmail(sign.getEmail())) {
            return CommonResponse.fail("이미 회원가입된 이메일입니다.", HttpStatus.BAD_REQUEST);
        }

        Member savedMember
                = memberRepository.save(new Member(sign.getEmail(), passwordEncode.encode(sign.getPassword())));

        return CommonResponse.success(SignUpResponse.of(savedMember.getEmail()), HttpStatus.CREATED);
    }

    public ResponseEntity login(LoginRequest loginRequest) {
        if (memberRepository.findByEmail(loginRequest.getEmail()).orElse(null) == null) {
            return CommonResponse.fail("해당 유저가 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = loginRequest.toAuthentication();

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        Token token = jwtTokenProvider.generateToken(authentication);

        LoginResponse result = LoginResponse.builder()
                .token(token)
                .email(loginRequest.getEmail())
                .build();

        return CommonResponse.success(result);
    }
}
