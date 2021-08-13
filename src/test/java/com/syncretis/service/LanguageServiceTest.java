package com.syncretis.service;

import com.syncretis.dto.LanguageDto;
import com.syncretis.entity.Language;
import com.syncretis.exception.LanguageNotFoundException;
import com.syncretis.mapper.LanguageDtoMapper;
import com.syncretis.repository.LanguageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class LanguageServiceTest {
    @Mock
    LanguageDtoMapper languageDtoMapperMock;

    @Mock
    LanguageRepository languageRepositoryMock;

    @InjectMocks
    LanguageService languageService;

    @Test
    public void shouldReturnAllLanguagesDto() {
        //GIVEN
        List<Language> languages = new ArrayList<>();
        Language language = new Language("English");
        Language language1 = new Language("Russian");
        languages.add(language);
        languages.add(language1);

        List<LanguageDto> languagesDto = new ArrayList<>();
        LanguageDto languageDto = new LanguageDto("English");
        LanguageDto languageDto1 = new LanguageDto("Russian");
        languagesDto.add(languageDto);
        languagesDto.add(languageDto1);

        //WHEN
        Mockito.when(languageRepositoryMock.findAll()).thenReturn(languages);
        Mockito.when(languageDtoMapperMock.mapLanguages(languages)).thenReturn(languagesDto);

        List<LanguageDto> actualLanguagesDto = languageService.getAll();

        //THEN
        Mockito.verify(languageRepositoryMock).findAll();
        Mockito.verify(languageDtoMapperMock).mapLanguages(languages);
        Mockito.verify(languageDtoMapperMock, Mockito.times(0)).mapLanguageDto(any());
        assertThat(actualLanguagesDto).isEqualTo(languagesDto);
    }

    @Test
    public void shouldReturnLanguageDtoById() {
        //GIVEN
        Long languageId = 1L;
        Language language = new Language("English");
        LanguageDto languageDto = new LanguageDto("English");

        //WHEN
        Mockito.when(languageRepositoryMock.findById(languageId)).thenReturn(Optional.of(language));
        Mockito.when(languageDtoMapperMock.mapLanguage(language)).thenReturn(languageDto);

        LanguageDto actualLanguageDto = languageService.getById(languageId);

        //THEN
        Mockito.verify(languageRepositoryMock).findById(languageId);
        Mockito.verify(languageDtoMapperMock).mapLanguage(language);
        assertThat(actualLanguageDto).isEqualTo(languageDto);
    }

    @Test
    public void shouldThrowNpeWhenFindById() {
        //GIVEN
        Long languageId = 1L;

        //WHEN
        Mockito.when(languageRepositoryMock.findById(Mockito.any())).thenReturn(Optional.empty());

        //THEN
        assertThrows(LanguageNotFoundException.class, () -> languageService.getById(languageId));
    }

    @Test
    public void shouldPutLanguageAndReturnLanguageDtoIfExistById() {
        //GIVEN
        Long languageId = 1L;
        Language language = new Language("English");
        LanguageDto languageDto = new LanguageDto("English");

        //WHEN
        Mockito.when(languageDtoMapperMock.mapLanguageDto(languageDto)).thenReturn(language);
        Mockito.when(languageRepositoryMock.existsById(languageId)).thenReturn(true);
        Mockito.when(languageRepositoryMock.findById(languageId)).thenReturn(Optional.of(language));
        Mockito.when(languageDtoMapperMock.mapLanguage(language)).thenReturn(languageDto);

        LanguageDto actualLanguageDto = languageService.put(languageId, languageDto);

        //THEN
        Mockito.verify(languageDtoMapperMock).mapLanguageDto(languageDto);
        Mockito.verify(languageRepositoryMock).existsById(languageId);
        Mockito.verify(languageRepositoryMock).findById(languageId);
        Mockito.verify(languageRepositoryMock).save(language);
        assertThat(actualLanguageDto).isEqualTo(languageDto);
    }

    @Test
    public void shouldPutLanguageAndReturnLanguageDtoIfDontExistById() {
        //GIVEN
        Long languageId = 1L;
        Language language = new Language("English");
        LanguageDto languageDto = new LanguageDto("English");

        //WHEN
        Mockito.when(languageDtoMapperMock.mapLanguageDto(languageDto)).thenReturn(language);
        Mockito.when(languageRepositoryMock.existsById(languageId)).thenReturn(false);
        Mockito.when(languageDtoMapperMock.mapLanguage(language)).thenReturn(languageDto);

        LanguageDto actualLanguageDto = languageService.put(languageId, languageDto);

        //THEN
        Mockito.verify(languageDtoMapperMock).mapLanguageDto(languageDto);
        Mockito.verify(languageRepositoryMock).existsById(languageId);
        Mockito.verify(languageRepositoryMock, Mockito.times(0)).findById(languageId);
        Mockito.verify(languageRepositoryMock).save(language);
        assertThat(actualLanguageDto).isEqualTo(languageDto);
    }

    @Test
    public void shouldPostLanguageAndReturnLanguageDto() {
        //GIVEN
        Language language = new Language("English");
        LanguageDto languageDto = new LanguageDto("English");

        //WHEN
        Mockito.when(languageDtoMapperMock.mapLanguageDto(languageDto)).thenReturn(language);
        Mockito.when(languageDtoMapperMock.mapLanguage(language)).thenReturn(languageDto);

        LanguageDto actualLanguageDto = languageService.save(languageDto);

        //THEN
        Mockito.verify(languageDtoMapperMock).mapLanguageDto(languageDto);
        Mockito.verify(languageRepositoryMock).save(language);
        Mockito.verify(languageDtoMapperMock).mapLanguage(language);
        assertThat(actualLanguageDto).isEqualTo(languageDto);
    }

    @Test
    public void shouldDeleteLanguage() {
        //GIVEN
        Long languageId = 1L;
        Language language = new Language("English");

        //WHEN
        Mockito.when(languageRepositoryMock.findById(languageId)).thenReturn(Optional.of(language));

        languageService.deleteById(languageId);

        //THEN
        Mockito.verify(languageRepositoryMock).findById(languageId);
        Mockito.verify(languageRepositoryMock).deleteById(languageId);
    }

    @Test
    public void shouldThrowNpeWhenDeleteLanguage() {
        //GIVEN
        Long languageId = 1L;

        //WHEN
        Mockito.when(languageRepositoryMock.findById(Mockito.any())).thenReturn(Optional.empty());

        //THEN
        assertThrows(LanguageNotFoundException.class, () -> languageService.deleteById(languageId));
    }
}
