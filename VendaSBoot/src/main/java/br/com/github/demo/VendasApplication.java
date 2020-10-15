package br.com.github.demo;

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

@SpringBootApplication
@ComponentScan({"br.*"})
@EntityScan(basePackages = {"br.com.github.modelo", "br.com.github.alura"})
@EnableJpaRepositories("br.com.github.repository")
public class VendasApplication {

    @Bean
    public CommandLineRunner inicio(
            @Autowired AluraRepository aluraRepository) {


        return args -> {
            Cliente cliente = new Cliente();
            cliente.setNome("Douglas");

            System.out.println("Salvando clientes");
            aluraRepository.salvar(cliente);



        };

    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
