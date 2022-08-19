package com.brunoonofre64.rest.controller;

import com.brunoonofre64.domain.Pedido;
import com.brunoonofre64.rest.dto.PedidoDTO;
import com.brunoonofre64.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/pedido/v1/api")
public class PedidoController {

    private  PedidoService pedidoService;
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save( @RequestBody  PedidoDTO dto ) {
        Pedido pedido = pedidoService.salvar(dto);
        return pedido.getId();
    }
}
