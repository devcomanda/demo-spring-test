package com.devcomanda.demotests.springtest.web;

import com.devcomanda.demotests.model.Person;
import com.devcomanda.demotests.service.PersonService;
import com.devcomanda.demotests.web.PeopleController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
public class PeopleControllerIntTest {
    @Mock
    private PersonService personService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new PeopleController(personService)).build();
    }

    @Test
    public void shouldReturnPageWithListPersonsWithGivenName() throws Exception {
        String testName = "testName";
        String testEmail = "testEmail";
        Person person = new Person(testName, testEmail);

        List<Person> people = Collections.singletonList(person);
        when(this.personService.loadPeopleByName(eq(testName))).thenReturn(people);

        this.mockMvc.perform(
                get("/people/search")
                        .param("name", testName)
        )
                .andExpect(status().isOk())
                .andExpect(view().name("people"))
                .andExpect(model().attributeExists("people"))
                .andExpect(model().attribute("people", people))
                .andDo(print());
    }
}
