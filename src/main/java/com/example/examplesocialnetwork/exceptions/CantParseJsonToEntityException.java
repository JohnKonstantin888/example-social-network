package com.example.examplesocialnetwork.exceptions;

public class CantParseJsonToEntityException extends RuntimeException {
    public CantParseJsonToEntityException(Class<?> clazz, String json) {
        super("Невозможно преобразовать в сущность : '" + clazz.getSimpleName() + "' JSON : " + json +  "'");
    }
}
