package com.devcomanda.demotests.springboottest.web;

import com.devcomanda.demotests.model.Person;
import com.devcomanda.demotests.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class PeopleControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @Test
    @WithMockUser("admin")
    public void shouldReturnPageWithPeopleListWithGivenName() throws Exception {
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

    @Test
    public void shouldReturnEmptyPeopleListWithGivenNameIfPersonNotFound() throws Exception {

        List<Person> people = Collections.emptyList();

        when(this.personService.loadPeopleByName(any())).thenReturn(people);

        this.mockMvc.perform(
                get("/people/search")
                        .with(user("admin")
                                .password("admin")
                        )
        )
                .andExpect(status().isOk())
                .andExpect(view().name("people"))
                .andExpect(model().attributeExists("people"))
                .andExpect(model().attribute("people", people))
                .andDo(print());
    }
}
