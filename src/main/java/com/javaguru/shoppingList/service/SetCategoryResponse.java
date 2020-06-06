package com.javaguru.shoppingList.service;

public class SetCategoryResponse {
    private boolean success;
    private String errorMessage;

    public SetCategoryResponse(boolean success, String errorMessage) {
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
