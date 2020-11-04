package br.com.rest;


import br.com.github.modelo.Cliente;
import br.com.github.modelo.Produto;
import br.com.github.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sun.awt.image.IntegerComponentRaster;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody  @Valid Produto produto) {
        return produtoRepository.save(produto);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateProduto(@PathVariable Integer id,
                              @RequestBody @Valid Produto produto) {
        produtoRepository
                .findById(id)
                .map(produtoEncontrado -> {
                    produto.setId(produtoEncontrado.getId());
                    produtoRepository.save(produto);
                    return produto;
                })
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Produto não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduto(@PathVariable Integer id) {

        produtoRepository.findById(id)
                .map(produtoEncontrado -> {
                    produtoRepository.deleteById(produtoEncontrado.getId());
                    return Void.TYPE;
                })
                .orElseThrow(
                        () ->
                                new ResponseStatusException(HttpStatus.NOT_FOUND,
                                        "Produto não encontrado")
                );

    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Produto getById(@PathVariable Integer id) {

        return produtoRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Produto não encontrado"));

    }


    @GetMapping("/all")
    public List<Produto> findAll(Produto filtro) {

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        return produtoRepository.findAll(example);

    }

}
