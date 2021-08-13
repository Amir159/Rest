package com.syncretis.controller;

import com.syncretis.dto.PersonDto;
import com.syncretis.service.PersonService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("/persons")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    List<PersonDto> getAllPersons() {
        return personService.getAll();
    }

    @GetMapping("{id}")
    PersonDto getPerson(@PathVariable("id") @Min(1) Long id) {
        return personService.getById(id);
    }

    @PutMapping("{id}")
    PersonDto updatePerson(@PathVariable("id") @Min(1) Long id, @RequestBody @Valid PersonDto newPersonDto) {
        return personService.put(id, newPersonDto);
    }

    @PostMapping
    PersonDto newDepartment(@RequestBody @Valid PersonDto personDto) {
        return personService.save(personDto);
    }

    @DeleteMapping("{id}")
    void deleteDepartment(@PathVariable("id") @Min(1) Long id) {
        personService.deleteById(id);
    }
}
