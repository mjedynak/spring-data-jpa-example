package pl.mjedynak.repository;

import org.springframework.data.repository.CrudRepository;
import pl.mjedynak.model.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
