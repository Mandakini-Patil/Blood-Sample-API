package com.example.bsm.exception;

public class DonationRequestNotFoundByIdException extends RuntimeException {
    private final String message;

    public DonationRequestNotFoundByIdException(String  message) {

        this.message=message;
    }
    @Override
    public String getMessage(){
        return message;
    }
}
