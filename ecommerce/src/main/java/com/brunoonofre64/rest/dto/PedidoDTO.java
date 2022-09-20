package com.brunoonofre64.rest.dto;

import com.brunoonofre64.validation.DefaultMessage;
import com.brunoonofre64.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    @NotNull(message = DefaultMessage.ID_CLIENTE_VALIDATION)
    private Integer cliente;

    @NotNull(message = DefaultMessage.TOTAL_PEDIDO_VALIDATION)
    private BigDecimal total;

    @NotEmptyList(message = DefaultMessage.LISTA_PEDIDO_VALIDATION)
    private List<ItemPedidoDTO> items;
}
