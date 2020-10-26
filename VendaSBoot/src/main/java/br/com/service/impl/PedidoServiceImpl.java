package br.com.service.impl;

import br.com.github.enumerations.StatusPedido;
import br.com.github.exception.PedidoNaoEncontradoException;
import br.com.github.exception.RegraNegocioException;
import br.com.github.modelo.Cliente;
import br.com.github.modelo.ItemPedido;
import br.com.github.modelo.Pedido;
import br.com.github.modelo.Produto;
import br.com.github.repository.ClienteRepository;
import br.com.github.repository.ItemPedidoRepository;
import br.com.github.repository.PedidoRepository;
import br.com.github.repository.ProdutoRepository;
import br.com.rest.dto.ItemPedidoDTO;
import br.com.rest.dto.PedidoDTO;
import br.com.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {


    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;


    @Override
    @Transactional
    public Pedido salvar(PedidoDTO pedidoDTO) {
        Integer idCliente = pedidoDTO.getCliente();
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() ->
                new RegraNegocioException("Codigo cliente invalido"));

        Pedido pedido = new Pedido();
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        pedidoRepository.save(pedido);
        List<ItemPedido> itemPedidos = converterItemPedido(pedido, pedidoDTO.getItems());

        itemPedidoRepository.saveAll(itemPedidos);
        pedido.setItens(itemPedidos);

        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidoRepository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {

        pedidoRepository.findById(id)
                .map( pedido -> {
                    pedido.setStatus(statusPedido);
                    return pedidoRepository.save(pedido);
                }).orElseThrow(() -> new PedidoNaoEncontradoException() )  ;
    }

    private List<ItemPedido> converterItemPedido(Pedido pedido, List<ItemPedidoDTO> itemPedidoList) {
        if (itemPedidoList.isEmpty()) {
            throw new RegraNegocioException("Nao é possivel realizar um pedido sem itens");
        }

        return itemPedidoList.stream()
                .map(dto -> {

                    Integer idProduto = dto.getProduto();
                    Produto produto = produtoRepository.findById(idProduto)
                            .orElseThrow(() ->
                                    new RegraNegocioException("Nao é possivel realizar um pedido sem itens"));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());


    }

}
