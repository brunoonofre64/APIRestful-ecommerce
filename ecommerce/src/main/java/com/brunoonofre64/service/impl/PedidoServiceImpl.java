package com.brunoonofre64.service.impl;

import com.brunoonofre64.domain.Cliente;
import com.brunoonofre64.domain.ItemPedido;
import com.brunoonofre64.domain.Pedido;
import com.brunoonofre64.domain.Produto;
import com.brunoonofre64.exception.ErrorMessage;
import com.brunoonofre64.exception.RegraNegocioException;
import com.brunoonofre64.repositories.ClienteRepository;
import com.brunoonofre64.repositories.ItemPedidoRepository;
import com.brunoonofre64.repositories.PedidoRepository;
import com.brunoonofre64.repositories.ProdutoRepository;
import com.brunoonofre64.rest.dto.ItemPedidoDTO;
import com.brunoonofre64.rest.dto.PedidoDTO;
import com.brunoonofre64.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar( PedidoDTO dto ) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clienteRepository
                .findById(idCliente)
                .orElseThrow( () -> new RegraNegocioException(ErrorMessage.CLIENTE_INVALIDO));

        Pedido pedido = new Pedido();
        pedido.setDataPedido(LocalDate.now());
        pedido.setTotal(dto.getTotal());
        pedido.setCliente(cliente);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itemsPedido);
        pedido.setItems(itemsPedido);
        return pedido;
    }

    public List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
        if(items.isEmpty()) {
            throw new RegraNegocioException(ErrorMessage.LISTA_ITEMS_VAZIA);
        }
        return items
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtoRepository
                            .findById(idProduto)
                            .orElseThrow( () -> new RegraNegocioException(ErrorMessage.PRODUTO_INVALIDO));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }

}
