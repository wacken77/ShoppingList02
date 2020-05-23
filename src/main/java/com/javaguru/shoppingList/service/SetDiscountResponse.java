package com.javaguru.shoppingList.service;

public class SetDiscountResponse {

    private  boolean success;
    private String errorMessage;

    public SetDiscountResponse(boolean success, String errorMessage) {
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
