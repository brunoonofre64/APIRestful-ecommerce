package com.brunoonofre64.domain;

import com.brunoonofre64.validation.DefaultMessage;
import com.brunoonofre64.validation.Regex;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100)
    @NotBlank(message = DefaultMessage.NOME_VALIDATION)
    private String nome;

    @Column(name = "cpf", length = 11)
    @NotBlank(message = DefaultMessage.CPF_VALIDATION)
    @CPF(message = DefaultMessage.CPF_INVALIDO)
    private String cpf;


    @Column(name = "telefone", length = 15)
    @Pattern(regexp = Regex.FONE_FORMAT, message = DefaultMessage.TELEFONE_VALIDATION)
    private String telefone;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private Set<Pedido> listaPedidos;
}
