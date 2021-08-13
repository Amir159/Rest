package com.syncretis.service;

import com.syncretis.dto.PersonDto;
import com.syncretis.entity.Language;
import com.syncretis.entity.Person;
import com.syncretis.exception.DepartmentNotFoundException;
import com.syncretis.exception.DocumentNotFoundException;
import com.syncretis.exception.LanguageNotFoundException;
import com.syncretis.exception.PersonNotFoundException;
import com.syncretis.mapper.PersonDtoMapper;
import com.syncretis.repository.DepartmentRepository;
import com.syncretis.repository.DocumentRepository;
import com.syncretis.repository.LanguageRepository;
import com.syncretis.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class PersonService {

    private final PersonRepository personRepository;
    private final DepartmentRepository departmentRepository;
    private final DocumentRepository documentRepository;
    private final LanguageRepository languageRepository;
    private final PersonDtoMapper personDtoMapper;

    public PersonService(PersonRepository personRepository, DepartmentRepository departmentRepository, DocumentRepository documentRepository,
                         LanguageRepository languageRepository, PersonDtoMapper personDtoMapper) {
        this.personRepository = personRepository;
        this.departmentRepository = departmentRepository;
        this.documentRepository = documentRepository;
        this.languageRepository = languageRepository;
        this.personDtoMapper = personDtoMapper;
    }

    public List<PersonDto> getAll() {
        return personDtoMapper.mapPersons(personRepository.findAll());
    }

    public PersonDto getById(Long id) {
        return personDtoMapper.mapPerson(personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(HttpStatus.NOT_FOUND)));
    }

    public PersonDto put(Long id, PersonDto personDto) {
        if (personRepository.existsById(id)) {
            personDto.setId(id);
        }
        return save(personDto);
    }

    public PersonDto save(PersonDto personDto) {

        Person person = personDtoMapper.mapPersonDto(personDto);
        person.setDepartment(departmentRepository.findByName(personDto.getDepartmentName()).orElseThrow(() -> new DepartmentNotFoundException(HttpStatus.NOT_FOUND)));
        person.setDocument(documentRepository.findById(personDto.getDocumentId()).orElseThrow(() -> new DocumentNotFoundException(HttpStatus.NOT_FOUND)));

        List<Language> languagesList = new ArrayList<>();
        for (String languagesName : personDto.getLanguagesNames()) {
            if (languageRepository.findByName(languagesName) != null)
                languagesList.add(languageRepository.findByName(languagesName));
            else {
                throw new LanguageNotFoundException(HttpStatus.NOT_FOUND);
            }
        }
        person.setLanguageList(languagesList);
        Person newPerson = personRepository.save(person);
        return personDtoMapper.mapPerson(newPerson);
    }

    public void deleteById(Long id) {
        personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(HttpStatus.NOT_FOUND));
        personRepository.deleteById(id);
    }
}
