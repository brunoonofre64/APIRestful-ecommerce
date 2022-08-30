package com.brunoonofre64.rest.controller;

import com.brunoonofre64.exception.PedidoNaoEncontradoException;
import com.brunoonofre64.exception.RegraNegocioException;
import com.brunoonofre64.rest.ApiErrors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class AppControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex) {
        return new ApiErrors(ex.getMessage());
    }
    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(NOT_FOUND)
    public ApiErrors handlePedidoNotFound(PedidoNaoEncontradoException ex ) {
        return new ApiErrors(ex.getMessage());
    }

}
