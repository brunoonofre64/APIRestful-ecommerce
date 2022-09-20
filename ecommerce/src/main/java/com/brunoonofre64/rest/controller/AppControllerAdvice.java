package com.brunoonofre64.rest.controller;

import com.brunoonofre64.exception.PedidoNaoEncontradoException;
import com.brunoonofre64.exception.RegraNegocioException;
import com.brunoonofre64.rest.ApiErrors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map( erro -> erro.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApiErrors(errors);
    }

}
