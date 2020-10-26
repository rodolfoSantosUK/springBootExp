package br.com.rest;

import br.com.github.enumerations.StatusPedido;
import br.com.github.modelo.ItemPedido;
import br.com.github.modelo.Pedido;
import br.com.rest.dto.AtulizacaoStatusPedidoDTO;
import br.com.rest.dto.InformacoesItemPedidoDTO;
import br.com.rest.dto.InformacoesPedidoDTO;
import br.com.rest.dto.PedidoDTO;
import br.com.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.websocket.server.PathParam;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id) {
        return pedidoService
                .obterPedidoCompleto(id)
                .map(pedido -> converter(pedido))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));
    }

    private InformacoesPedidoDTO converter(Pedido pedido) {
        return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .items(converter(pedido.getItens()))
                .status(pedido.getStatus().name())
                .build();
    }


    private List<InformacoesItemPedidoDTO> converter(List<ItemPedido> itens) {

        if (CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }

        return itens.stream()
                .map(item -> InformacoesItemPedidoDTO
                        .builder()
                        .descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()
                )
                .collect(Collectors.toList());

    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id ,
                             @RequestBody  AtulizacaoStatusPedidoDTO dto) {

    String novoStatus = dto.getNovoStatus();
    pedidoService.atualizaStatus(id, StatusPedido.valueOf(novoStatus));

    }


}
