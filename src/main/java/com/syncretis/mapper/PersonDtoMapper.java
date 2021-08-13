package com.syncretis.mapper;

import com.syncretis.dto.PersonDto;
import com.syncretis.entity.Language;
import com.syncretis.entity.Person;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDtoMapper {
    public List<PersonDto> mapPersons(List<Person> persons) {
        List<PersonDto> personsDto = new ArrayList<>();
        for (Person person : persons) {
            personsDto.add(createPersonDto(person));
        }
        return personsDto;
    }

    public PersonDto mapPerson(Person person) {
        return createPersonDto(person);
    }

    private PersonDto createPersonDto(Person person) {
        Long personId = person.getId();
        String firstName = person.getFirstName();
        String secondName = person.getSecondName();
        LocalDate date = person.getBirthday();
        String departmentName = person.getDepartment().getName();

        List<Language> languages = person.getLanguageList();
        List<String> languagesNames = new ArrayList<>();
        for (Language language : languages) {
            languagesNames.add(language.getName());
        }

        String documentId = person.getDocument().getId();
        return new PersonDto(personId, firstName, secondName, date, departmentName, languagesNames, documentId);
    }

    public Person mapPersonDto(PersonDto personDto) {
        Person person = new Person();
        if (personDto.getId() != null) {
            person.setId(personDto.getId());
        }
        person.setFirstName(personDto.getFirstName());
        person.setSecondName(personDto.getSecondName());
        person.setBirthday(personDto.getBirthday());
        return person;
    }
}