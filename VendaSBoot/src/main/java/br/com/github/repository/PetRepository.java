package br.com.github.repository;

import br.com.github.guru.domains.Owner;
import br.com.github.guru.domains.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long > {
}
