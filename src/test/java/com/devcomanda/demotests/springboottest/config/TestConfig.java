package com.devcomanda.demotests.springboottest.config;

import com.devcomanda.demotests.repository.PersonRepository;
import com.devcomanda.demotests.service.PersonService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public PersonService personService(PersonRepository repo) {
        return new PersonService(repo);
    }
}
