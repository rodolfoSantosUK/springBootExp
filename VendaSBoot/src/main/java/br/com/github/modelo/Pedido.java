package br.com.github.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pedido {

private Integer id;
private Cliente cliente;
private LocalDate DataPedido;
private BigDecimal total;


    public Pedido() {
    }

    public Pedido(Integer id, Cliente cliente, LocalDate dataPedido, BigDecimal total) {
        this.id = id;
        this.cliente = cliente;
        DataPedido = dataPedido;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataPedido() {
        return DataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        DataPedido = dataPedido;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
