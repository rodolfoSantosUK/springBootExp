package br.com.github.demo;

import br.com.github.modelo.Cliente;
import br.com.github.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@ComponentScan({"br.com.*" })
public class VendasApplication {

//  @Autowired
//  @Qualifier("applicationName")
    @Value("${application.name}")
    private String applicationName;

    @GetMapping("/hello")
    public String helloWorld() {
        return applicationName;
    }


    @Bean(name = "init")
    public CommandLineRunner init(@Autowired ClienteRepository clienteRepository) {

        return args -> {
            Cliente cliente = new Cliente();
            cliente.setNome("Douglas");

            System.out.println("Salvando clientes");
            clienteRepository.salvar(cliente);

            Cliente cliente2 = new Cliente();
            cliente2.setNome("Fernando");
            clienteRepository.salvar(cliente2);

            System.out.println("Obtendo todos os clientes");
            List<Cliente> todosClientes = clienteRepository.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("Atualizando o nome do cliente");
            todosClientes.forEach( c -> {
                c.setNome(c.getNome() + " Atualizado ");
                clienteRepository.atualizar(c);
            });

            todosClientes = clienteRepository.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("Deletando cliente");

            clienteRepository.deletar(new Cliente(1));

            System.out.println("Buscando por nome");
            clienteRepository.buscarPorNome("Fernan").forEach(System.out::println);


        };



    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
