package pl.mjedynak.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.mjedynak.model.Person;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static pl.mjedynak.model.PersonBuilder.aPerson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class PersonRepositoryIntegrationTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void shouldFindPreviouslySavedPerson() {
        // given
        Integer age = 22;
        String name = "Charlie";
        Person person = aPerson().
                withAge(age).
                withName(name).build();

        personRepository.save(person);

        // when
        List<Person> result = (List<Person>) personRepository.findAll();

        // then
        assertThat(result, hasSize(1));
        Person foundPerson = result.get(0);
        assertThat(foundPerson.getAge(), is(age));
        assertThat(foundPerson.getName(), is(name));
    }
}
