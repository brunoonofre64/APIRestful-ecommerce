package com.brunoonofre64.service;

import com.brunoonofre64.domain.Pedido;
import com.brunoonofre64.rest.dto.InformacoesPedidoDTO;
import com.brunoonofre64.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar( PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto( Integer id );


}
