package com.syncretis.service;

import com.syncretis.dto.PersonDto;
import com.syncretis.entity.Language;
import com.syncretis.entity.Person;
import com.syncretis.mapper.PersonDtoMapper;
import com.syncretis.repository.DepartmentRepository;
import com.syncretis.repository.DocumentRepository;
import com.syncretis.repository.LanguageRepository;
import com.syncretis.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final DepartmentRepository departmentRepository;
    private final DocumentRepository documentRepository;
    private final LanguageRepository languageRepository;
    private final PersonDtoMapper personDtoMapper = new PersonDtoMapper();

    public PersonService(PersonRepository personRepository, DepartmentRepository departmentRepository, DocumentRepository documentRepository, LanguageRepository languageRepository) {
        this.personRepository = personRepository;
        this.departmentRepository = departmentRepository;
        this.documentRepository = documentRepository;
        this.languageRepository = languageRepository;
    }

    public List<PersonDto> getAll() {
        return personDtoMapper.mapPerson(personRepository.findAll());
    }

    public PersonDto getById(Long id) {
        return personDtoMapper.mapPerson(personRepository.findById(id).orElse(null));
    }

    public PersonDto put(Long id, PersonDto personDto) {
        if (personRepository.existsById(id)) {
            personDto.setId(id);
        }
        return save(personDto);
    }

    public PersonDto save(PersonDto personDto) {

        Person person = personDtoMapper.mapPersonDto(personDto);
        person.setDepartment(departmentRepository.findByName(personDto.getDepartmentName()));
        person.setDocument(documentRepository.findById(personDto.getDocumentId()).orElse(null));
        List<Language> languagesList = new ArrayList<>();
        for (String languagesName : personDto.getLanguagesNames()) {
            if (languageRepository.findByName(languagesName) != null)
                languagesList.add(languageRepository.findByName(languagesName));
            else {
                return null;
            }
        }
        person.setLanguageList(languagesList);
        Person newPerson = personRepository.save(person);
        return personDtoMapper.mapPerson(newPerson);
    }

    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }
}
