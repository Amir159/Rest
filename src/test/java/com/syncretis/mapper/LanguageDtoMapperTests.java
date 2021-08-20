package com.syncretis.mapper;

import com.syncretis.dto.LanguageDto;
import com.syncretis.entity.Language;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LanguageDtoMapperTests {
    private final LanguageDtoMapper languageDtoMapper = new LanguageDtoMapper();

    private static final String languageName1 = "West chinese";
    private static final String languageName2 = "North chinese";
    private static final String languageName3 = "East chinese";

    @Test
    public void shouldReturnLanguage() {
        //GIVEN
        LanguageDto languageDto = createLanguageDto(languageName1);
        Language expectedLanguage = createLanguage(languageName1);

        //WHEN
        Language actualLanguage = languageDtoMapper.mapLanguageDto(languageDto);

        //THEN
        assertThat(actualLanguage).isEqualTo(expectedLanguage);
    }

    @Test
    public void shouldReturnLanguageDto() {
        //GIVEN
        Language language = createLanguage(languageName1);
        LanguageDto expectedLanguageDto = createLanguageDto(languageName1);

        //WHEN
        LanguageDto actualLanguageDto = languageDtoMapper.mapLanguage(language);

        //THEN
        assertThat(actualLanguageDto).isEqualTo(expectedLanguageDto);
    }

    @Test
    public void shouldReturnListLanguageDto() {
        //GIVEN
        List<LanguageDto> expectedLanguagesDto = Arrays.asList(createLanguageDto(languageName1),
                createLanguageDto(languageName2),
                createLanguageDto(languageName3));

        List<Language> languages = Arrays.asList(createLanguage(languageName1),
                createLanguage(languageName2),
                createLanguage(languageName3));

        //WHEN
        List<LanguageDto> actualLanguagesDto = languageDtoMapper.mapLanguages(languages);

        //THEN
        assertThat(actualLanguagesDto).isEqualTo(expectedLanguagesDto);
    }

    private LanguageDto createLanguageDto(String name) {
        return new LanguageDto(name);
    }

    private Language createLanguage(String name) {
        return new Language(name);
    }
}
