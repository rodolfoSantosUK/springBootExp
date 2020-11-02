package br.com.github.repository;

import br.com.github.guru.domains.Owner;
import br.com.github.guru.domains.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long > {
}
