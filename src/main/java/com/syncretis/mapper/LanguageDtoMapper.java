package com.syncretis.mapper;

import com.syncretis.dto.LanguageDto;
import com.syncretis.entity.Language;
import com.syncretis.entity.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LanguageDtoMapper {
    public List<LanguageDto> mapLanguages(List<Language> languages) {
        List<LanguageDto> languagesDto = new ArrayList<>();
        for (Language language : languages) {
            languagesDto.add(createLanguageDto(language));
        }
        return languagesDto;
    }

    public LanguageDto mapLanguage(Language language) {
        return createLanguageDto(language);
    }

    private LanguageDto createLanguageDto(Language language) {
        if (language == null) {
            return null;
        }
        Long id = language.getId();
        String name = language.getName();
        List<Person> personsList = language.getPersonList();
        List<Long> personsId = new ArrayList<>();
        if (personsList != null) {
            for (Person person : personsList) {
                personsId.add(person.getId());
            }
        } else {
            personsId = null;
        }
        return new LanguageDto(id, name, personsId);
    }

    public Language mapLanguageDto(LanguageDto languageDto) {
        Language language = new Language();
        language.setName(languageDto.getName());
        return language;
    }
}
