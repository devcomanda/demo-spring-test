package com.devcomanda.simpletests.spring.service;

import com.devcomanda.demotests.model.Person;
import com.devcomanda.demotests.repository.PersonRepository;
import com.devcomanda.demotests.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    private PersonService personService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.personService = new PersonService(this.personRepository);
    }

    @Test
    public void shouldReturnListPersonsWithGivenName() {

        String expectedName = "testName";
        String expectedEmail = "testEmail";

        Person person = new Person(expectedName, expectedEmail);

        when(this.personRepository.findAllByName(eq(expectedName))).thenReturn(Collections.singletonList(person));

        List<Person> persons = this.personService.loadPersonsByName(expectedName);

        assertThat(persons)
                .isNotNull()
                .containsExactlyInAnyOrder(person);

        // in this case this is anti-pattern
        // verify(this.personRepository).findAllByName(eq(expectedName));
    }
}
