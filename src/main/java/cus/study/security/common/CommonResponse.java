package cus.study.security.common;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

public class CommonResponse {

    @Getter
    public static class Body {
        private int statusCode;
        private String result;
        private String massage;
        private Object data;
        private Object error;

        protected Body() {
        }

        @Builder
        public Body(int statusCode, String result, String massage, Object data, Object error) {
            this.statusCode = statusCode;
            this.result = result;
            this.massage = massage;
            this.data = data;
            this.error = error;
        }
    }

    public static ResponseEntity<?> success(Object data, String msg, HttpStatus status) {
        Body body = Body.builder()
                .statusCode(status.value())
                .data(data)
                .result("success")
                .massage(msg)
                .error(Collections.emptyList())
                .build();
        return ResponseEntity.ok(body);
    }

    public static ResponseEntity<?> success(Object data, HttpStatus status) {
        Body body = Body.builder()
                .statusCode(status.value())
                .data(data)
                .result("success")
                .error(Collections.emptyList())
                .build();
        return ResponseEntity.ok(body);
    }

    public static ResponseEntity<?> success(Object data) {
        return success(data, null, HttpStatus.OK);
    }

    public static ResponseEntity<?> success() {
        return success(null, null, HttpStatus.OK);
    }

    public static ResponseEntity<?> fail(Object data, String msg, HttpStatus status) {
        Body body = Body.builder()
                .statusCode(status.value())
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
