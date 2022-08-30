package com.brunoonofre64.service.impl;

import com.brunoonofre64.domain.Cliente;
import com.brunoonofre64.domain.ItemPedido;
import com.brunoonofre64.domain.Pedido;
import com.brunoonofre64.domain.Produto;
import com.brunoonofre64.domain.enums.StatusPedido;
import com.brunoonofre64.exception.ErrorMessage;
import com.brunoonofre64.exception.PedidoNaoEncontradoException;
import com.brunoonofre64.exception.RegraNegocioException;
import com.brunoonofre64.repositories.ClienteRepository;
import com.brunoonofre64.repositories.ItemPedidoRepository;
import com.brunoonofre64.repositories.PedidoRepository;
import com.brunoonofre64.repositories.ProdutoRepository;
import com.brunoonofre64.rest.dto.InformacoesItemPedidoDTO;
import com.brunoonofre64.rest.dto.InformacoesPedidoDTO;
import com.brunoonofre64.rest.dto.ItemPedidoDTO;
import com.brunoonofre64.rest.dto.PedidoDTO;
import com.brunoonofre64.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
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
                .orElseThrow( () ->
                        new RegraNegocioException(ErrorMessage.CLIENTE_INVALIDO));

        Pedido pedido = new Pedido();
        pedido.setDataPedido(LocalDate.now());
        pedido.setTotal(dto.getTotal());
        pedido.setCliente(cliente);
        pedido.setStatusPedido(StatusPedido.REALIZADO);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itemsPedido);
        pedido.setItems(itemsPedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidoRepository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatusPedido( Integer id, StatusPedido statusPedido ) {
        pedidoRepository
                .findById(id)
                .map( pedido -> {
                    pedido.setStatusPedido(statusPedido);
                   return pedidoRepository.save(pedido);
                }).orElseThrow( () -> new PedidoNaoEncontradoException());
    }


    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
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
    public InformacoesPedidoDTO getById( Integer id ) {
        return obterPedidoCompleto(id)
                .map(p -> converter(p))
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                ErrorMessage.PEDIDO_NAO_ENCONTRADO));
    }
        private InformacoesPedidoDTO converter(Pedido pedido){
            return InformacoesPedidoDTO
                    .builder()
                    .codigo(pedido.getId())
                    .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                    .telefone(pedido.getCliente().getTelefone())
                    .cpf(pedido.getCliente().getCpf())
                    .nome(pedido.getCliente().getNome())
                    .totalPedido(pedido.getTotal())
                    .statusPedido(pedido.getStatusPedido().name())
                    .items(converter(pedido.getItems()))
                    .build();
        }

        private List<InformacoesItemPedidoDTO> converter (List<ItemPedido> items) {
            if (CollectionUtils.isEmpty(items)) {
                return Collections.emptyList();
            }
            return items.stream().map(
                    item -> InformacoesItemPedidoDTO
                            .builder()
                            .descricaoProduto(item.getProduto().getDescricao())
                            .precoUnitario(item.getProduto().getValorUnitario())
                            .quantidade(item.getQuantidade())
                            .build()
            ).collect(Collectors.toList());
         }


    }

