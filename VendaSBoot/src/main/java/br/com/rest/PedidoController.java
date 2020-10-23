package br.com.rest;

import br.com.github.modelo.Pedido;
import br.com.rest.dto.PedidoDTO;
import br.com.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {

    PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO pedidoDTO) {

        Pedido pedido = pedidoService.salvar(pedidoDTO);
        return pedido.getId();
    }


}
