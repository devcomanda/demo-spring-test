package com.devcomanda.demotests.springtest.config;

import com.devcomanda.demotests.repository.PersonRepository;
import com.devcomanda.demotests.service.PersonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestServiceConfiguration {

    @Bean
    public PersonService personService(PersonRepository repo) {
        return new PersonService(repo);
    }
}
