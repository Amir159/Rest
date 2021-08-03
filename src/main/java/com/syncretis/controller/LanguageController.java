package com.syncretis.controller;

import com.syncretis.dto.LanguageDto;
import com.syncretis.service.LanguageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LanguageController {
    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping("/languages")
    List<LanguageDto> all() {
        return languageService.getAll();
    }

    @GetMapping("/languages/{id}")
    LanguageDto one(@PathVariable Long id) {
        return languageService.getById(id);
    }

    @PutMapping("/languages/{id}")
    LanguageDto updateLanguage(@PathVariable Long id, @RequestBody LanguageDto newLanguageDto) {
        return languageService.put(id, newLanguageDto);
    }

    @PostMapping("/languages")
    LanguageDto newLanguage(@RequestBody LanguageDto newLanguageDto) {
        return languageService.save(newLanguageDto);
    }

    @DeleteMapping("/languages/{id}")
    void deleteLanguage(@PathVariable Long id) {
        languageService.deleteById(id);
    }
}
