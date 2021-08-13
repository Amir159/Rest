package com.syncretis.service;

import com.syncretis.dto.LanguageDto;
import com.syncretis.entity.Language;
import com.syncretis.exception.LanguageNotFoundException;
import com.syncretis.mapper.LanguageDtoMapper;
import com.syncretis.repository.LanguageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {

    private final LanguageRepository languageRepository;
    private final LanguageDtoMapper languageDtoMapper;

    public LanguageService(LanguageRepository languageRepository, LanguageDtoMapper languageDtoMapper) {
        this.languageRepository = languageRepository;
        this.languageDtoMapper = languageDtoMapper;
    }

    public List<LanguageDto> getAll() {
        return languageDtoMapper.mapLanguages(languageRepository.findAll());
    }

    public LanguageDto getById(Long id) {
        return languageDtoMapper.mapLanguage(languageRepository.findById(id).orElseThrow(() -> new LanguageNotFoundException(HttpStatus.NOT_FOUND)));
    }

    public LanguageDto put(Long id, LanguageDto languageDto) {
        Language language = languageDtoMapper.mapLanguageDto(languageDto);
        if (languageRepository.existsById(id)) {
            Language newLanguage = languageRepository.findById(id).orElse(null);
            language.setId(newLanguage.getId());
            language.setPersonList(newLanguage.getPersonList());
        }
        languageRepository.save(language);
        return languageDtoMapper.mapLanguage(language);
    }

    public LanguageDto save(LanguageDto languageDto) {
        Language language = languageDtoMapper.mapLanguageDto(languageDto);
        languageRepository.save(language);
        return languageDtoMapper.mapLanguage(language);
    }

    public void deleteById(Long id) {
        languageRepository.findById(id).orElseThrow(() -> new LanguageNotFoundException(HttpStatus.NOT_FOUND));
        languageRepository.deleteById(id);
    }
}
