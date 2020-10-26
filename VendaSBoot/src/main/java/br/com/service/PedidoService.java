package br.com.service;

import br.com.github.enumerations.StatusPedido;
import br.com.github.modelo.Pedido;
import br.com.rest.dto.PedidoDTO;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar (PedidoDTO pedidoDTO) ;

    Optional<Pedido> obterPedidoCompleto (Integer id );

    void atualizaStatus(Integer id , StatusPedido statusPedido);

}
