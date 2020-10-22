package br.com.github.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name= "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column( name = "id")
    private Integer id;

    @Column(name="nome", length = 10)
    private String nome;

    @Column
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private Set<Pedido> pedidos;

    public void adicionaPedidos(Pedido pedido ) {
        getPedidos().add(pedido);
    }

}
