package br.com.github.repository;

import br.com.github.modelo.Cliente;
import br.com.github.modelo.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository <Pedido, Integer> {


    List<Pedido> findByCliente(Cliente cliente);

}
