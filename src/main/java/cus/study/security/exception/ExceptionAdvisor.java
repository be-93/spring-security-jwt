package cus.study.security.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvisor {
    @ExceptionHandler(LoginFailException.class)
    public ResponseEntity loginAdvisor(LoginFailException e) {
        return ResponseEntity.status(401)
                .body(e.getMessage());
    }
}
