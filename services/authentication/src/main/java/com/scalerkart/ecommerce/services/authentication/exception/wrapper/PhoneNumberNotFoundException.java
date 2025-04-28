package com.scalerkart.ecommerce.services.authentication.exception.wrapper;

public class PhoneNumberNotFoundException extends RuntimeException{
    public PhoneNumberNotFoundException() {
        super();
    }

    public PhoneNumberNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhoneNumberNotFoundException(String message) {
        super(message);
    }

    public PhoneNumberNotFoundException(Throwable cause) {
        super(cause);
    }
}
