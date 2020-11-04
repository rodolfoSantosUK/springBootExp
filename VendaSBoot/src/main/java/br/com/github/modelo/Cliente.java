package br.com.github.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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

    @NotBlank( message = "Nome deve ser preenchido")
    @Column(name="nome", length = 10)
    private String nome;

    @Column(name = "cpf")
    @NotBlank( message = "Nome deve ser preenchido")
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private Set<Pedido> pedidos;

    public void adicionaPedidos(Pedido pedido ) {
        getPedidos().add(pedido);
    }

}
