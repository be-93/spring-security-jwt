package cus.study.security.exception;

public class LoginFailBadRequestNameException extends LoginFailException {
    public LoginFailBadRequestNameException() {
    }

    public LoginFailBadRequestNameException(String message) {
        super(message);
    }
}
