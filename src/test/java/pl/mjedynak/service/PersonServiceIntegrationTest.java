package pl.mjedynak.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.mjedynak.model.Person;
import pl.mjedynak.repository.PersonRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class PersonServiceIntegrationTest {

    @Autowired private PersonService personService;
    @Autowired private PersonRepository personRepository;

    @Test
    public void shouldMakeMultipleUpdatesOnTheSameObjectWithinOneTransaction() {
        // when
        Long newPersonId = personService.createNewPersonWithMultipleUpdateInOneTransaction();

        // then
        Person person = personRepository.findOne(newPersonId);
        assertThat(person, is(not(nullValue())));
    }
}
