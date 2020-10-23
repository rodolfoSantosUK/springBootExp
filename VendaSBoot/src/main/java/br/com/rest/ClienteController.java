package br.com.rest;

import br.com.github.modelo.Cliente;
import br.com.github.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("{id}")
    public Cliente getCliente(@PathVariable Integer id) {
        return clienteRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, " Nenhum cliente encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {

        clienteRepository.findById(id)
                .map(clienteFromDatabase -> {
                    clienteRepository.save(clienteFromDatabase);
                    return clienteFromDatabase;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, " Nenhum cliente encontrado"));

    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCliente(@PathVariable Integer id,
                              @RequestBody Cliente cliente) {
        clienteRepository
                .findById(id)
                .map(clienteEncontrado -> {
                    cliente.setId(clienteEncontrado.getId());
                    clienteRepository.save(cliente);
                    return clienteEncontrado;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, " Nenhum cliente encontrado"));
    }


    @GetMapping("/all")
    public List<Cliente> findAll(Cliente filtro) {

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        return clienteRepository.findAll(example);

    }


    public void gerandoMassa() {
        //clienteRepository.save(new Cliente("Rodolfo"));
    }

}
