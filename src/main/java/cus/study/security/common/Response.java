package cus.study.security.common;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

public class Response {

    @Getter
    @Builder
    private static class Body {
        private int state;
        private String result;
        private String massage;
        private Object data;
        private Object error;
    }

    public static ResponseEntity<?> success(Object data, String msg, HttpStatus status) {
        Body body = Body.builder()
                .state(status.value())
                .data(data)
                .result("success")
                .massage(msg)
                .error(Collections.emptyList())
                .build();
        return ResponseEntity.ok(body);
    }

    public static ResponseEntity<?> success(Object data, HttpStatus status) {
        Body body = Body.builder()
                .state(status.value())
                .data(data)
                .result("success")
                .error(Collections.emptyList())
                .build();
        return ResponseEntity.ok(body);
    }

    public static ResponseEntity<?> success(Object data) {
        return success(data, null, HttpStatus.OK);
    }

    public static ResponseEntity<?> fail(Object data, String msg, HttpStatus status) {
        Body body = Body.builder()
                .state(status.value())
                .data(data)
                .result("fail")
                .massage(msg)
                .error(Collections.emptyList())
                .build();
        return ResponseEntity.ok(body);
    }

    public static ResponseEntity<?> fail(String msg, HttpStatus status) {
        return fail(Collections.emptyList(), msg, status);
    }
}
