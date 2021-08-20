package com.syncretis.mapper;

import com.syncretis.dto.PersonDto;
import com.syncretis.entity.Department;
import com.syncretis.entity.Document;
import com.syncretis.entity.Language;
import com.syncretis.entity.Person;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonDtoMapperTests {
    private final PersonDtoMapper personDtoMapper = new PersonDtoMapper();

    private static final String firstName1 = "Test1";
    private static final String firstName2 = "Test2";

    private static final String secondName1 = "Testing1";
    private static final String secondName2 = "Testing2";

    private static final LocalDate birthday1 = LocalDate.of(2000, 1, 1);
    private static final LocalDate birthday2 = LocalDate.of(2000, 2, 2);

    private static final String departmentName1 = "Test department1";
    private static final String departmentName2 = "Test department2";

    private static final String languageName1 = "Test language1";
    private static final String languageName2 = "Test language2";

    private static final String documentId1 = "TestDocument1";
    private static final String documentId2 = "TestDocument2";

    private static final LocalDate expiryDate1 = LocalDate.of(2025, 1, 1);
    private static final LocalDate expiryDate2 = LocalDate.of(2025, 2, 2);

    @Test
    public void shouldReturnPerson() {
        //GIVEN
        Department department = createDepartment(999L, departmentName1);
        Document document = createDocument(documentId1, expiryDate1);
        List<Language> languagesList = createLanguagesList(languageName1, languageName2);
        Person expectedPerson = createPerson(999L, firstName1, secondName1, birthday1, department, languagesList, document);

        PersonDto personDto = createPersonDto(999L, firstName1, secondName1, birthday1, departmentName1,
                Arrays.asList(languageName1, languageName2), departmentName1);

        //WHEN
        Person actualPerson = personDtoMapper.mapPersonDto(personDto);

        //THEN
        assertThat(actualPerson).isEqualTo(expectedPerson);
    }

    @Test
    public void shouldReturnPersonDto() {
        //GIVEN
        Department department = createDepartment(999L, departmentName1);
        Document document = createDocument(documentId1, expiryDate1);
        List<Language> languagesList = createLanguagesList(languageName1, languageName2);
        Person person = createPerson(999L, firstName1, secondName1, birthday1, department, languagesList, document);

        PersonDto expectedPersonDto = createPersonDto(999L, firstName1, secondName1, birthday1, departmentName1,
                Arrays.asList(languageName1, languageName2), documentId1);

        //WHEN
        PersonDto actualPersonDto = personDtoMapper.mapPerson(person);

        //THEN
        assertThat(actualPersonDto).isEqualTo(expectedPersonDto);
    }

    @Test
    public void shouldReturnListPersonDto() {
        //GIVEN
        Department department1 = createDepartment(999L, departmentName1);
        Department department2 = createDepartment(998L, departmentName2);
        Document document1 = createDocument(documentId1, expiryDate1);
        Document document2 = createDocument(documentId2, expiryDate2);
        List<Language> languagesList = createLanguagesList(languageName1, languageName2);

        List<Person> persons = Arrays.asList(
                createPerson(999L, firstName1, secondName1, birthday1, department1, languagesList, document1),
                createPerson(998L, firstName2, secondName2, birthday2, department2, languagesList, document2));

        List<PersonDto> expectedPersonsDto = Arrays.asList(
                createPersonDto(999L, firstName1, secondName1, birthday1, departmentName1,
                        Arrays.asList(languageName1, languageName2), documentId1),
                createPersonDto(998L, firstName2, secondName2, birthday2, departmentName2,
                        Arrays.asList(languageName1, languageName2), documentId2));

        //WHEN
        List<PersonDto> actualPersonsDto = personDtoMapper.mapPersons(persons);

        //THEN
        assertThat(actualPersonsDto).isEqualTo(expectedPersonsDto);
    }

    private Person createPerson(Long id, String firstName, String secondName, LocalDate birthday, Department department,
                                List<Language> languagesList, Document document) {
        return new Person(id, firstName, secondName, birthday, department, languagesList, document);
    }

    private PersonDto createPersonDto(Long id, String firstName, String secondName, LocalDate birthday, String departmentName,
                                      List<String> languagesList, String documentId) {
        return new PersonDto(id, firstName, secondName, birthday, departmentName, languagesList, documentId);
    }

    private Department createDepartment(Long id, String name) {
        return new Department(id, name);
    }

    private List<Language> createLanguagesList(String name1, String name2) {
        return Arrays.asList(new Language(name1), new Language(name2));
    }

    private Document createDocument(String id, LocalDate date) {
        return new Document(id, date);
    }
}