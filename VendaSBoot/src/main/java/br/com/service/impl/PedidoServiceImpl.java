package br.com.service.impl;

import br.com.github.repository.PedidoRepository;
import br.com.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl  implements PedidoService {


    private PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }
}
