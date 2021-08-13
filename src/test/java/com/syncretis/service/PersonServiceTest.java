package com.syncretis.service;

import com.syncretis.dto.PersonDto;
import com.syncretis.entity.Department;
import com.syncretis.entity.Document;
import com.syncretis.entity.Language;
import com.syncretis.entity.Person;
import com.syncretis.exception.PersonNotFoundException;
import com.syncretis.mapper.PersonDtoMapper;
import com.syncretis.repository.DepartmentRepository;
import com.syncretis.repository.DocumentRepository;
import com.syncretis.repository.LanguageRepository;
import com.syncretis.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PersonServiceTest {
    @Mock
    PersonDtoMapper personDtoMapperMock;

    @Mock
    DepartmentRepository departmentRepository;

    @Mock
    LanguageRepository languageRepository;

    @Mock
    DocumentRepository documentRepository;

    @Mock
    PersonRepository personRepositoryMock;

    @InjectMocks
    PersonService personService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnAllPersonsDto() {
        //GIVEN
        Language language = new Language("English");
        Language language1 = new Language("Russian");

        List<Person> persons = new ArrayList<>();

        Department department = new Department("Test department");
        Document document = new Document("123d", LocalDate.of(2025, 1, 1));
        Person person = new Person(1L, "TestName", "TestSurname", LocalDate.of(2021, 8, 11), department, List.of(language1), document);

        Department department1 = new Department("Test department1");
        Document document1 = new Document("124d", LocalDate.of(2025, 1, 1));
        Person person1 = new Person(2L, "TestName1", "TestSurname1", LocalDate.of(2021, 8, 11), department1, List.of(language), document1);
        persons.add(person);
        persons.add(person1);

        List<PersonDto> personsDto = new ArrayList<>();
        PersonDto personDto = new PersonDto(1L, "TestName", "TestSurname", LocalDate.of(2021, 8, 11),
                "Test department", List.of("Russian"), "123d");
        PersonDto personDto1 = new PersonDto(2L, "TestName1", "TestSurname1", LocalDate.of(2021, 8, 11),
                "Test department1", List.of("English"), "124d");
        personsDto.add(personDto);
        personsDto.add(personDto1);

        //WHEN
        Mockito.when(personDtoMapperMock.mapPersons(persons)).thenReturn(personsDto);
        Mockito.when(personRepositoryMock.findAll()).thenReturn(persons);

        List<PersonDto> actualPersonsDto = personService.getAll();

        //THEN
        Mockito.verify(personRepositoryMock).findAll();
        Mockito.verify(personDtoMapperMock).mapPersons(persons);
        assertThat(actualPersonsDto).isEqualTo(personsDto);
    }

    @Test
    public void shouldReturnPersonDtoById() {
        //GIVEN
        Language language = new Language("Russian");

        Long personId = 1L;
        Department department = new Department("Test department");
        Document document = new Document("123d", LocalDate.of(2025, 1, 1));
        Person person = new Person(personId, "TestName", "TestSurname", LocalDate.of(2021, 8, 11),
                department, List.of(language), document);

        PersonDto personDto = new PersonDto(personId, "TestName", "TestSurname", LocalDate.of(2021, 8, 11),
                "Test department", List.of("Russian"), "123d");

        //WHEN
        Mockito.when(personRepositoryMock.findById(personId)).thenReturn(Optional.of(person));
        Mockito.when(personDtoMapperMock.mapPerson(person)).thenReturn(personDto);

        PersonDto actualPersonDto = personService.getById(personId);

        //THEN
        Mockito.verify(personRepositoryMock).findById(personId);
        Mockito.verify(personDtoMapperMock).mapPerson(person);
        assertThat(actualPersonDto).isEqualTo(personDto);
    }

    @Test
    public void shouldThrowNpeWhenFindById() {
        //GIVEN
        Long personId = 11L;

        //WHEN
        Mockito.when(personRepositoryMock.findById(Mockito.any())).thenReturn(Optional.empty());

        //THEN
        assertThrows(PersonNotFoundException.class, () -> personService.getById(personId));
    }

    @Test
    public void shouldPutPersonAndReturnPersonDtoIfExistById() {
        //GIVEN
        Language language = new Language("Russian");

        Long personId = 1L;
        Department department = new Department("Test department");
        Document document = new Document("123d", LocalDate.of(2025, 1, 1));
        Person person = new Person(personId, "TestName", "TestSurname", LocalDate.of(2021, 8, 11),
                department, List.of(language), document);

        PersonDto personDto = new PersonDto(personId, "TestName", "TestSurname", LocalDate.of(2021, 8, 11),
                "Test department", List.of("Russian"), "123d");

        //WHEN
        Mockito.when(personRepositoryMock.existsById(personId)).thenReturn(true);
        Mockito.when(departmentRepository.findByName(personDto.getDepartmentName())).thenReturn(Optional.of(department));
        Mockito.when(documentRepository.findById(personDto.getDocumentId())).thenReturn(Optional.of(document));
        Mockito.when(languageRepository.findByName(personDto.getLanguagesNames().get(0))).thenReturn(language);
        Mockito.when(personRepositoryMock.save(person)).thenReturn(person);
        Mockito.when(personDtoMapperMock.mapPersonDto(personDto)).thenReturn(person);
        Mockito.when(personDtoMapperMock.mapPerson(person)).thenReturn(personDto);

        PersonDto actualPersonDto = personService.put(personId, personDto);

        //THEN
        Mockito.verify(personRepositoryMock).existsById(personId);
        Mockito.verify(departmentRepository).findByName(personDto.getDepartmentName());
        Mockito.verify(documentRepository).findById(personDto.getDocumentId());
        Mockito.verify(languageRepository, Mockito.times(2)).findByName(personDto.getLanguagesNames().get(0));
        Mockito.verify(personRepositoryMock).save(person);
        Mockito.verify(personDtoMapperMock).mapPersonDto(personDto);

        assertThat(actualPersonDto).isEqualTo(personDto);
    }

    @Test
    public void shouldPutPersonAndReturnPersonDtoIfDontExistById() {
        //GIVEN
        Language language = new Language("Russian");

        Long personId = 1L;
        Department department = new Department("Test department");
        Document document = new Document("123d", LocalDate.of(2025, 1, 1));
        Person person = new Person(null, "TestName", "TestSurname", LocalDate.of(2021, 8, 11),
                department, List.of(language), document);

        PersonDto personDto = new PersonDto(null, "TestName", "TestSurname", LocalDate.of(2021, 8, 11),
                "Test department", List.of("Russian"), "123d");

        //WHEN
        Mockito.when(personRepositoryMock.existsById(personId)).thenReturn(false);
        Mockito.when(departmentRepository.findByName(personDto.getDepartmentName())).thenReturn(Optional.of(department));
        Mockito.when(documentRepository.findById(personDto.getDocumentId())).thenReturn(Optional.of(document));
        Mockito.when(languageRepository.findByName(personDto.getLanguagesNames().get(0))).thenReturn(language);
        Mockito.when(personRepositoryMock.save(person)).thenReturn(person);
        Mockito.when(personDtoMapperMock.mapPersonDto(personDto)).thenReturn(person);
        Mockito.when(personDtoMapperMock.mapPerson(person)).thenReturn(personDto);

        PersonDto actualPersonDto = personService.put(personId, personDto);

        //THEN
        Mockito.verify(personRepositoryMock).existsById(personId);
        Mockito.verify(departmentRepository).findByName(personDto.getDepartmentName());
        Mockito.verify(documentRepository).findById(personDto.getDocumentId());
        Mockito.verify(languageRepository, Mockito.times(2)).findByName(personDto.getLanguagesNames().get(0));
        Mockito.verify(personRepositoryMock).save(person);
        Mockito.verify(personDtoMapperMock).mapPersonDto(personDto);

        assertThat(actualPersonDto).isEqualTo(personDto);
    }

    @Test
    public void shouldPostPersonAndReturnPersonDto() {
        //GIVEN
        Language language = new Language("Russian");

        Department department = new Department("Test department");
        Document document = new Document("123d", LocalDate.of(2025, 1, 1));
        Person person = new Person(null, "TestName", "TestSurname", LocalDate.of(2021, 8, 11),
                department, List.of(language), document);

        PersonDto personDto = new PersonDto(null, "TestName", "TestSurname", LocalDate.of(2021, 8, 11),
                "Test department", List.of("Russian"), "123d");

        //WHEN
        Mockito.when(departmentRepository.findByName(personDto.getDepartmentName())).thenReturn(Optional.of(department));
        Mockito.when(documentRepository.findById(personDto.getDocumentId())).thenReturn(Optional.of(document));
        Mockito.when(languageRepository.findByName(personDto.getLanguagesNames().get(0))).thenReturn(language);
        Mockito.when(personRepositoryMock.save(person)).thenReturn(person);
        Mockito.when(personDtoMapperMock.mapPersonDto(personDto)).thenReturn(person);
        Mockito.when(personDtoMapperMock.mapPerson(person)).thenReturn(personDto);

        PersonDto actualPersonDto = personService.save(personDto);

        //THEN
        Mockito.verify(departmentRepository).findByName(personDto.getDepartmentName());
        Mockito.verify(documentRepository).findById(personDto.getDocumentId());
        Mockito.verify(languageRepository, Mockito.times(2)).findByName(personDto.getLanguagesNames().get(0));
        Mockito.verify(personRepositoryMock).save(person);
        Mockito.verify(personDtoMapperMock).mapPersonDto(personDto);

        assertThat(actualPersonDto).isEqualTo(personDto);
    }

    @Test
    public void shouldDeletePerson() {
        //GIVEN
        Language language = new Language("Russian");

        Long personId = 1L;
        Department department = new Department("Test department");
        Document document = new Document("123d", LocalDate.of(2025, 1, 1));
        Person person = new Person(personId, "TestName", "TestSurname", LocalDate.of(2021, 8, 11),
                department, List.of(language), document);

        //WHEN
        Mockito.when(personRepositoryMock.findById(personId)).thenReturn(Optional.of(person));

        personService.deleteById(personId);

        //THEN
        Mockito.verify(personRepositoryMock).findById(personId);
        Mockito.verify(personRepositoryMock).deleteById(personId);
    }

    @Test
    public void shouldThrowNpeWhenDeletePerson() {
        //GIVEN
        Long personId = 1L;

        //WHEN
        Mockito.when(personRepositoryMock.findById(Mockito.any())).thenReturn(Optional.empty());

        //THEN
        assertThrows(PersonNotFoundException.class, () -> personService.deleteById(personId));
    }
}