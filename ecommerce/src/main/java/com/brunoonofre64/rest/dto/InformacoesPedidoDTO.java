package com.brunoonofre64.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesPedidoDTO {

    private Integer codigo;
    private String cpf;
    private String telefone;
    private String nome;
    private String dataPedido;
    private BigDecimal totalPedido;
    private List<InformacoesItemPedidoDTO> items;
}
