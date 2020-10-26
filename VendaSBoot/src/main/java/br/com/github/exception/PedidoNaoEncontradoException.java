package br.com.github.exception;

public class PedidoNaoEncontradoException extends RuntimeException {

    public PedidoNaoEncontradoException( ) {
        super("Pedido não encontrado.");
    }

}
