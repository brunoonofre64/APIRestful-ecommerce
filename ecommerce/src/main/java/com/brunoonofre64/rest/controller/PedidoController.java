package com.brunoonofre64.rest.controller;

import com.brunoonofre64.domain.Pedido;
import com.brunoonofre64.domain.enums.StatusPedido;
import com.brunoonofre64.rest.dto.AtualizacaoStatusPedidoDTO;
import com.brunoonofre64.rest.dto.InformacoesPedidoDTO;
import com.brunoonofre64.rest.dto.PedidoDTO;
import com.brunoonofre64.service.PedidoService;
import com.brunoonofre64.service.impl.PedidoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pedido/v1/api")
public class PedidoController {

    private final PedidoService pedidoService;
    private final PedidoServiceImpl pedidoServiceImpl;
    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save( @RequestBody  PedidoDTO dto ) {
        Pedido pedido = pedidoService.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("/{id}")
    public InformacoesPedidoDTO getById( @PathVariable("id") Integer id ) {
        return pedidoServiceImpl.getById(id);
    }
    @PatchMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void atualizaStatusPedido( @PathVariable("id") Integer id,
                                      @RequestBody AtualizacaoStatusPedidoDTO dto ) {
        String novoStatus = dto.getNovoStatus();
        pedidoService.atualizaStatusPedido(id, StatusPedido.valueOf(novoStatus));
    }
}
