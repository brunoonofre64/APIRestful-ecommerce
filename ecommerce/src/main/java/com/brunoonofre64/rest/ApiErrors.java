package com.brunoonofre64.rest;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {
    @Getter
    private List<String> errors;

    public ApiErrors(String mensagemError) {
        this.errors = Arrays.asList(mensagemError);
    }
}
