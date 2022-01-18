package cus.study.security.exception;

public class LoginFailBadRequestPassWordException extends LoginFailException {
    public LoginFailBadRequestPassWordException() {
    }

    public LoginFailBadRequestPassWordException(String message) {
        super(message);
    }
}
