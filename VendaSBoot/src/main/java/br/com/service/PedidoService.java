package br.com.service;

import br.com.github.modelo.Pedido;
import br.com.rest.dto.PedidoDTO;
import org.springframework.web.bind.annotation.RestController;

public interface PedidoService {


    Pedido salvar (PedidoDTO pedidoDTO) ;

}
