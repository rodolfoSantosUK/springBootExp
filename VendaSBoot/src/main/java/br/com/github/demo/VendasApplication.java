package br.com.github.demo;

import br.com.github.alura.modelo.Conta;
import br.com.github.alura.modelo.Movimentacao;
import br.com.github.alura.modelo.TipoMovimentacao;
import br.com.github.modelo.Cliente;
import br.com.github.modelo.Pedido;
import br.com.github.repository.AluraRepository;
import br.com.github.repository.ClienteRepository;
import br.com.github.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
@ComponentScan({"br.*"})
@EntityScan(basePackages = {"br.com.github.modelo", "br.com.github.alura"})
@EnableJpaRepositories("br.com.github.repository")
public class VendasApplication {

    @Bean
    public CommandLineRunner inicio(
            @Autowired AluraRepository aluraRepository) {


        return args -> {
            Conta conta = new Conta();
            conta.setAgencia(1);
            conta.setSaldo(22.9);
            conta.setNumero(1);
            conta.setTitular("Rodolfo Santos");

            Movimentacao movimentacao = new Movimentacao();
            movimentacao.setData(LocalDateTime.now().plusDays(1));
            movimentacao.setConta(conta);
            movimentacao.setDescricao("MOvimentacao 1");
            movimentacao.setTipoMovimentacao(TipoMovimentacao.ENTRADA);
            movimentacao.setValor(BigDecimal.valueOf(150));

            Movimentacao movimentacao2 = new Movimentacao();
            movimentacao2.setData(LocalDateTime.now());
            movimentacao2.setConta(conta);
            movimentacao2.setDescricao("MOvimentacao 1");
            movimentacao2.setTipoMovimentacao(TipoMovimentacao.ENTRADA);
            movimentacao2.setValor(BigDecimal.valueOf(150));

            System.out.println("Salvando conta");
            aluraRepository.salvar(conta);

            System.out.println("Salvando movimentacao");
            aluraRepository.salvar(movimentacao);
            aluraRepository.salvar(movimentacao2);

            // aluraRepository.metodoExemplo();
            aluraRepository.somandoValores();
            aluraRepository.mediaValores();

        };

    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
