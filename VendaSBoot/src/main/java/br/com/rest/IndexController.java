package br.com.rest;


import br.com.github.guru.domains.Category;
import br.com.github.guru.domains.UnitOfMeasure;
import br.com.github.repository.CategoryRepository;
import br.com.github.repository.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @RequestMapping({"",  "/", "/index"})
    public String getIndexPage() {

        Optional<Category> categoryOptional  = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Cat id is: " + categoryOptional.get().getId());
        System.out.println("UOM id is: " + unitOptional.get().getId());

        return "";
    }

}
