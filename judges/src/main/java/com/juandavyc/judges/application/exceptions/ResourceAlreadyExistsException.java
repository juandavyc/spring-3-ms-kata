package com.juandavyc.judges.application.exceptions;

import lombok.Getter;

@Getter
public class ResourceAlreadyExistsException extends RuntimeException {


    public ResourceAlreadyExistsException(String fieldName, String fieldValue ) {
        super(String.format("with %s '%s' already exists", fieldName, fieldValue));
    }

}
