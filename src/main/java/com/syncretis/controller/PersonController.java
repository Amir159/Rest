package com.syncretis.controller;

import com.syncretis.dto.PersonDto;
import com.syncretis.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons")
    List<PersonDto> all() {
        return personService.getAll();
    }

    @GetMapping("/persons/{id}")
    PersonDto one(@PathVariable Long id) {
        return personService.getById(id);
    }

    @PutMapping("/persons/{id}")
    PersonDto updatePerson(@PathVariable Long id, @RequestBody PersonDto newPersonDto) {
        return personService.put(id, newPersonDto);
    }

    @PostMapping("/persons")
    PersonDto newDepartment(@RequestBody PersonDto personDto) {
        return personService.save(personDto);
    }

    @DeleteMapping("/persons/{id}")
    void deleteDepartment(@PathVariable Long id) {
        personService.deleteById(id);
    }
}
