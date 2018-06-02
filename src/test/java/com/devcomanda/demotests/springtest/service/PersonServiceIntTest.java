package com.devcomanda.demotests.springtest.service;

import com.devcomanda.demotests.model.Person;
import com.devcomanda.demotests.service.PersonService;
import com.devcomanda.demotests.springtest.config.TestJpaConfiguration;
import com.devcomanda.demotests.springtest.config.TestServiceConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextHierarchy(value = {
        @ContextConfiguration(classes = TestJpaConfiguration.class),
        @ContextConfiguration(classes = TestServiceConfiguration.class)
})
@Transactional
public class PersonServiceIntTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private PersonService personService;

    @Test
    public void shouldReturnListPersonsWithGivenName() {
        String expectedName = "testName";
        String expectedEmail = "testEmail";
        Person person = new Person(expectedName, expectedEmail);
        this.em.persist(person);

        List<Person> persons = this.personService.loadPersonsByName(expectedName);

        assertThat(persons).containsExactlyInAnyOrder(person);
    }
}
