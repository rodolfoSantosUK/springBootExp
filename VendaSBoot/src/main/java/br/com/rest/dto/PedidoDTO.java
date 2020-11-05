package br.com.rest.dto;

//{
//"cliente" :1,
//"total" : 100 ,
//
//"items" : [
//        {
//        "produto" : 1,
//        "quantidade" : 100
//        }
//          ]
//}

import br.com.github.exception.NotEmptyList;
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

    @NotNull
    private Integer cliente;
    private BigDecimal total;
    @NotEmptyList(message = "Pedido nao pode ser realizado sem itens.")
    private List<ItemPedidoDTO> items;

}
