package com.brunoonofre64.service;

import com.brunoonofre64.domain.Cliente;
import com.brunoonofre64.exception.ErrorMessage;
import com.brunoonofre64.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente getClienteById(Integer id) {
        return clienteRepository
                .findById(id)
                .orElseThrow( () ->
                        getResponseStatusException());
    }

//    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save( Cliente cliente ) {
        return clienteRepository.save(cliente);
    }

    public void uptade( Integer id, Cliente cliente) {
        clienteRepository
                .findById(id)
                .map( clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clienteRepository.save(cliente);
                    return clienteExistente;
                }).orElseThrow( () -> getResponseStatusException());
    }

    public void delete( Integer id ) {
        clienteRepository
                .findById(id)
                .map( cliente -> {
                    clienteRepository.delete(cliente);
                    return cliente;
                }).orElseThrow( () -> getResponseStatusException());
    }

    public List<Cliente> findCliente( Cliente filtro ) {
        ExampleMatcher matcher = ExampleMatcher
                                .matching()
                                .withIgnoreCase()
                                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        return clienteRepository.findAll(example);
    }

    private ResponseStatusException getResponseStatusException() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND,
                ErrorMessage.CLIENTE_NAO_ENCONTRADO);
    }
}
