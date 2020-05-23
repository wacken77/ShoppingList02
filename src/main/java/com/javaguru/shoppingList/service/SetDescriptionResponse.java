package com.javaguru.shoppingList.service;

public class SetDescriptionResponse {
    private boolean success;
    private String errorMessage;

    public SetDescriptionResponse(boolean success, String errorMessage) {
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
