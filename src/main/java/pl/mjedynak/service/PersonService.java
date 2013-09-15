package pl.mjedynak.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.mjedynak.model.Person;
import pl.mjedynak.repository.PersonRepository;

import static pl.mjedynak.model.PersonBuilder.aPerson;

@Component
public class PersonService {

    private static final Logger LOG = LoggerFactory.getLogger(PersonService.class);

    @Autowired private PersonRepository personRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long createNewPersonWithMultipleUpdateInOneTransaction() {
        Person person = aPerson().
                withAge(11).
                withName("someName").build();
        LOG.debug("saving first time " + person);
        Person savedPerson = personRepository.save(person);
        savedPerson.setAge(12);
        LOG.debug("saving second time " + savedPerson);
        savedPerson = personRepository.save(person);
        return savedPerson.getId();
    }
}
