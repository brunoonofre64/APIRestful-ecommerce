package com.brunoonofre64.domain;

import com.brunoonofre64.validation.DefaultMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @NotEmpty(message = DefaultMessage.DESCRICAO_VALIDATION)
    @Column(name = "descricao")
    private String descricao;

    @NotNull(message = DefaultMessage.VALOR_VALIDATION)
    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;

}
