package com.devcomanda.demotests.web;

import com.devcomanda.demotests.model.Person;
import com.devcomanda.demotests.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PeopleController {

    private final PersonService personService;

    @Autowired
    public PeopleController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/people/search")
    public String searchPersonsByName(
            @RequestParam(value = "name", defaultValue = "name") String name,
            Model model
    ) {

        List<Person> people = this.personService.loadPeopleByName(name);
        model.addAttribute("people", people);
        return "people";
    }
}
