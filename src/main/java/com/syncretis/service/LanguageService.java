package com.syncretis.service;

import com.syncretis.dto.LanguageDto;
import com.syncretis.entity.Language;
import com.syncretis.mapper.LanguageDtoMapper;
import com.syncretis.repository.LanguageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;
    private final LanguageDtoMapper languageDtoMapper;

    public LanguageService(LanguageRepository languageRepository, LanguageDtoMapper languageDtoMapper) {
        this.languageRepository = languageRepository;
        this.languageDtoMapper = languageDtoMapper;
    }

    public List<LanguageDto> getAll() {
        return languageDtoMapper.mapLanguage(languageRepository.findAll());
    }

    public LanguageDto getById(Long id) {
        return languageDtoMapper.mapLanguage(languageRepository.findById(id).orElse(null));
    }

    public LanguageDto put(Long id, LanguageDto languageDto) {
        Language language = languageDtoMapper.mapLanguageDto(languageDto);
        if (languageRepository.existsById(id)) {
            language.setId(languageRepository.findById(id).orElse(null).getId());
            language.setPersonList(Objects.requireNonNull(languageRepository.findById(id).orElse(null)).getPersonList());
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
        languageRepository.deleteById(id);
    }
}
