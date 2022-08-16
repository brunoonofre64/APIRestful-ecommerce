package com.brunoonofre64.rest.controller;

import com.brunoonofre64.domain.Cliente;
import com.brunoonofre64.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/cliente/v1/api")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {

        this.clienteService = clienteService;
    }

    @GetMapping("/{id}")
    public Cliente getClienteById( @PathVariable("id") Integer id ) {
        return clienteService.getClienteById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Cliente save( @RequestBody Cliente cliente ) {
        return clienteService.save(cliente);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update ( @PathVariable("id") Integer id,
                        @RequestBody Cliente cliente ) {
        clienteService.uptade(id, cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete( @PathVariable Integer id ) {
        clienteService.delete(id);
    }

    @GetMapping
    public List<Cliente> findCliente( Cliente filtro ) {
        return clienteService.findCliente(filtro);
    }
}
