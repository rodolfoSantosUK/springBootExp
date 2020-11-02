package br.com.github.repository;

import br.com.github.guru.domains.Owner;
import br.com.github.guru.domains.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long > {
}
