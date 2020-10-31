package guru.springframework.repository;

import guru.springframework.domains.Category;
import guru.springframework.domains.Recipe;
import guru.springframework.domains.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByDescription( String description);


}