package com.syncretis.mapper;

import com.syncretis.dto.LanguageDto;
import com.syncretis.entity.Language;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LanguageDtoMapperTests {
    private final LanguageDtoMapper languageDtoMapper = new LanguageDtoMapper();
    private final LanguageDto languageDto1 = new LanguageDto("West chinese");
    private final LanguageDto languageDto2 = new LanguageDto("North chinese");
    private final LanguageDto languageDto3 = new LanguageDto("East chinese");
    private final Language language1 = new Language("West chinese");
    private final Language language2 = new Language("North chinese");
    private final Language language3 = new Language("East chinese");

    @Test
    public void shouldReturnLanguage() {
        //WHEN
        Language actualLanguage = languageDtoMapper.mapLanguageDto(languageDto1);

        //THEN
        assertThat(actualLanguage).isEqualTo(language1);
    }

    @Test
    public void shouldReturnLanguageDto() {
        //WHEN
        LanguageDto actualLanguageDto = languageDtoMapper.mapLanguage(language1);

        //THEN
        assertThat(actualLanguageDto).isEqualTo(languageDto1);
    }

    @Test
    public void shouldReturnListLanguageDto() {
        //GIVEN
        List<LanguageDto> expectedLanguagesDto = new ArrayList<>();
        expectedLanguagesDto.add(languageDto1);
        expectedLanguagesDto.add(languageDto2);
        expectedLanguagesDto.add(languageDto3);

        List<Language> languages = new ArrayList<>();
        languages.add(language1);
        languages.add(language2);
        languages.add(language3);

        //WHEN
        List<LanguageDto> actualLanguagesDto = languageDtoMapper.mapLanguages(languages);

        //THEN
        assertThat(actualLanguagesDto).isEqualTo(expectedLanguagesDto);
    }
}
