package br.com.github.demo;

import br.com.github.modelo.Cliente;
import br.com.github.modelo.Pedido;
import br.com.github.repository.ClienteRepository;
import br.com.github.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
//@RestController
@ComponentScan({"br.*"})
@EntityScan(basePackages = {"br.com.github.modelo", "br.com.github.alura" })
@EnableJpaRepositories("br.com.github.repository")
public class VendasApplication {

    @Autowired
     private EntityManager entityManager;

    @Bean
    public CommandLineRunner init(@Autowired ClienteRepository clienteRepository,
                                  @Autowired PedidoRepository pedidoRepository,
                                  @Autowired AluraRepository aluraRepository) {

        return args -> {
            Cliente cliente = new Cliente();
            cliente.setNome("Douglas");

            System.out.println("Salvando clientes");
            aluraRepository.salvar(cliente);

            // CRIANDO O PEDIDO
            Pedido pedido = new Pedido();
            pedido.setCliente(cliente);
            pedido.setDataPedido(LocalDate.now());
            pedido.setTotal(BigDecimal.valueOf(100));

            pedidoRepository.save(pedido);
            Cliente clientFromDatabase =   clienteRepository.findClienteFetchPedidos(cliente.getId());

            System.out.println("Cliente : " + cliente);
            System.out.println(clientFromDatabase.getPedidos());

             pedidoRepository.findByCliente(clientFromDatabase).forEach(System.out::println);


        };


    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
