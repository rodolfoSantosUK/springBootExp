package br.com.github.repository;

import br.com.github.guru.domains.Vet;
import br.com.github.guru.domains.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long > {


}
