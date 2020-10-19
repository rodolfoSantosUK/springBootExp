package br.com.rest;

import br.com.github.modelo.Cliente;
import br.com.github.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @ResponseBody
    @GetMapping("/api/cliente/{id}")
    public ResponseEntity getCliente(@PathVariable Integer id) {

        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/cliente/")
    @ResponseBody
    public ResponseEntity save(@RequestBody Cliente cliente) {

        Cliente clienteSalvo = clienteRepository.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }


    @ResponseBody
    @DeleteMapping("/api/cliente/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            clienteRepository.delete(cliente.get());
        }
        return ResponseEntity.noContent().build();
    }

    @ResponseBody
    @PutMapping("/api/cliente/{id}")
    public ResponseEntity updateCliente(@PathVariable Integer id,
                                        @RequestBody Cliente cliente) {
        return clienteRepository
                .findById(id)
                .map(clienteEncontrado -> {
                    cliente.setId(clienteEncontrado.getId());
                    clienteRepository.save(cliente);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/api/clientes")
    public ResponseEntity find( Cliente filtro) {

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro);
        List<Cliente> lista = clienteRepository.findAll(example);
        return ResponseEntity.ok(lista);
    }


    public void gerandoMassa() {
        clienteRepository.save(new Cliente("Rodolfo"));
    }

}