package com.devcomanda.demotests.springboottest.service;

import com.devcomanda.demotests.model.Person;
import com.devcomanda.demotests.service.PersonService;
import com.devcomanda.demotests.springboottest.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
public class PersonServiceIntTest {

    @Autowired
    private PersonService personService;

    @Test
    @Sql("/person-data.sql")
    public void shouldReturnPeopleWithGivenName() {
        String testName = "testName";
        String testEmail = "test@email.com";
        Person expectedPerson = new Person(testName, testEmail);
        expectedPerson.setId(1L);

        List<Person> people = this.personService.loadPeopleByName(testName);

        assertThat(people)
                .hasSize(1)
                .containsExactlyInAnyOrder(expectedPerson);
    }

}
