package com.devcomanda.demotests.springtest.repository;

import com.devcomanda.demotests.model.Person;
import com.devcomanda.demotests.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestJpaConfiguration.class)
@Transactional
public class PersonRepositoryIntTest {

    @Autowired
    EntityManager manager;

    @Autowired
    PersonRepository personRepository;

    @Test
    public void shouldReturnListPersonsWithGivenName() {
        String expectedName = "testName";
        String expectedEmail = "test@email.com";

        Person person = new Person(expectedName, expectedEmail);
        manager.persist(person);

        List<Person> persons = this.personRepository.findAllByName(expectedName);

        assertThat(persons)
                .isNotNull()
                .hasSize(1)
                .containsExactlyInAnyOrder(person);
    }

    @Test
    public void shouldReturnEmptyListPersonsWithGivenNameIfPersonNotFound() {
        String expectedName = "testName";

        List<Person> persons = this.personRepository.findAllByName(expectedName);

        assertThat(persons)
                .isNotNull()
                .hasSize(0);
    }
}

