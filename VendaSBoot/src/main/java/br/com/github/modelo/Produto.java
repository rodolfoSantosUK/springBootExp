package br.com.github.modelo;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "descricao")
    @NotEmpty( message = "descricao é obrigatorio")
    private String descricao;

    @Column(name = "preco_unitario")
    @NotNull(message = "Preco nao pode ser nulo ")
    private BigDecimal preco;



}
