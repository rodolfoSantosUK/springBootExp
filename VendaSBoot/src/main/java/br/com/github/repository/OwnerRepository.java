package br.com.github.repository;

import br.com.github.guru.domains.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long > {

    Owner findByLastName(String lastName);


}
