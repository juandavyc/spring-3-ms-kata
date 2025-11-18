package com.juandavyc.product.application.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    private final String value;

    public ResourceNotFoundException(String field, String value) {
        super(String.format("Product not found for field %s, value: '%s'", field, value));
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
