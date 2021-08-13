package com.syncretis.mapper;

import com.syncretis.dto.PersonDto;
import com.syncretis.entity.Department;
import com.syncretis.entity.Document;
import com.syncretis.entity.Language;
import com.syncretis.entity.Person;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonDtoMapperTests {
    private final PersonDtoMapper personDtoMapper = new PersonDtoMapper();

    private static final LocalDate birthday1 = LocalDate.of(2000, 1, 1);
    private static final LocalDate birthday2 = LocalDate.of(2000, 1, 2);

    private static final LocalDate expiryDate1 = LocalDate.of(2000, 1, 1);
    private static final LocalDate expiryDate2 = LocalDate.of(2000, 1, 2);

    private final Department department1 = new Department(999L, "Test department1");
    private final Department department2 = new Department(998L, "Test department2");

    private final Document document1 = new Document("TestDocument1", LocalDate.of(2025, 1, 1));
    private final Document document2 = new Document("TestDocument2", LocalDate.of(2025, 2, 2));

    private final List<String> languagesNamesList = new ArrayList<>() {{
        add("Test1 language");
        add("Test2 language");
    }};

    List<Language> languagesList = new ArrayList<>() {{
        add(new Language(999L, "Test1 language"));
        add(new Language(998L, "Test2 language"));
    }};

    private final Person person1 = new Person(999L, "Test1", "Testing1", birthday1,
            department1, languagesList, document1);
    private final Person person2 = new Person(998L, "Test2", "Testing2", LocalDate.of(2000, 1, 2),
            department2, languagesList, document2);

    private final PersonDto personDto1 = new PersonDto(999L, "Test1", "Testing1", LocalDate.of(2000, 1, 1),
            "Test department1", languagesNamesList, "TestDocument1");
    private final PersonDto personDto2 = new PersonDto(998L, "Test2", "Testing2", LocalDate.of(2000, 1, 2),
            "Test department2", languagesNamesList, "TestDocument2");

    @Test
    public void shouldReturnPerson() {
        //WHEN
        Person actualPerson = personDtoMapper.mapPersonDto(personDto1);

        //THEN
        assertThat(actualPerson).isEqualTo(person1);
    }

    @Test
    public void shouldReturnPersonDto() {
        //WHEN
        PersonDto actualPersonDto = personDtoMapper.mapPerson(person1);

        //THEN
        assertThat(actualPersonDto).isEqualTo(personDto1);
    }

    @Test
    public void shouldReturnListPersonDto() {
        //GIVEN
        List<PersonDto> expectedPersonsDto = new ArrayList<>();
        expectedPersonsDto.add(personDto1);
        expectedPersonsDto.add(personDto2);

        List<Person> persons = new ArrayList<>();
        persons.add(person1);
        persons.add(person2);

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
                                List<String > languagesList, String documentId) {
        return new PersonDto(id, firstName, secondName, birthday, departmentName, languagesList, documentId);
    }
}