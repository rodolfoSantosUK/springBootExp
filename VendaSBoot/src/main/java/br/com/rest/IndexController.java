package br.com.rest;


import br.com.github.guru.domains.Category;
import br.com.github.guru.domains.UnitOfMeasure;
import br.com.github.repository.CategoryRepository;
import br.com.github.repository.UnitOfMeasureRepository;
import br.com.service.OwnerSDJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    private OwnerSDJpaService ownerSDJpaService;


    public IndexController(OwnerSDJpaService ownerSDJpaService) {
        this.ownerSDJpaService = ownerSDJpaService;
    }

    @GetMapping("/all")
    public String testano() {

        ownerSDJpaService.findAll().stream().map( owner -> {
            System.out.println(owner.getId());
            return null;
        }) ;

        return null;
    }

    @RequestMapping({"",  "/", "/index"})
    public String getIndexPage() {

        Optional<Category> categoryOptional  = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Cat id is: " + categoryOptional.get().getId());
        System.out.println("UOM id is: " + unitOptional.get().getId());

        return "";
    }

}
