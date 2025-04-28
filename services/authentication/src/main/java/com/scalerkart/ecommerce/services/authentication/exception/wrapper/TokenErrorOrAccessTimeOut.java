package com.scalerkart.ecommerce.services.authentication.exception.wrapper;

public class TokenErrorOrAccessTimeOut extends RuntimeException {
    public TokenErrorOrAccessTimeOut() {
        super();
    }

    public TokenErrorOrAccessTimeOut(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenErrorOrAccessTimeOut(String message) {
        super(message);
    }

    public TokenErrorOrAccessTimeOut(Throwable cause) {
        super(cause);
    }
}
