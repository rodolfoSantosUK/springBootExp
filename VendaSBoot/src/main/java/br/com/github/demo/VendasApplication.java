package br.com.github.demo;

import br.com.github.modelo.Cliente;
import br.com.github.repository.ClienteRepository;
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

import java.util.List;

@SpringBootApplication
//@RestController
@ComponentScan({"br.*" })
@EntityScan("br.com.github.modelo")
@EnableJpaRepositories("br.com.github.repository")
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClienteRepository clienteRepository) {

        return args -> {
            Cliente cliente = new Cliente();
            cliente.setNome("Douglas");

            System.out.println("Salvando clientes");
           clienteRepository.save(cliente);


//            System.out.println("Obtendo todos os clientes");
//            List<Cliente> todosClientes = clienteRepository.obterTodos();
//            todosClientes.forEach(System.out::println);
//
//            System.out.println("Atualizando o nome do cliente");
//            todosClientes.forEach( c -> {
//                c.setNome(c.getNome() + " Atualizado ");
//                clienteRepository.atualizar(c);
//            });
//
//            todosClientes = clienteRepository.obterTodos();
//            todosClientes.forEach(System.out::println);
//
//            System.out.println("Deletando cliente");
//
//            clienteRepository.deletar(new Cliente(1));
//
//            System.out.println("Buscando por nome");
//            clienteRepository.buscarPorNome("Fernan").forEach(System.out::println);


        };



    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
