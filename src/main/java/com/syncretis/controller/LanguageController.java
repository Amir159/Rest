package com.syncretis.controller;

import com.syncretis.dto.LanguageDto;
import com.syncretis.service.LanguageService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("/languages")
public class LanguageController {
    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping
    List<LanguageDto> all() {
        return languageService.getAll();
    }

    @GetMapping("{id}")
    LanguageDto one(@PathVariable("id") Long id) {
        return languageService.getById(id);
    }

    @PutMapping("{id}")
    LanguageDto updateLanguage(@PathVariable("id") @Min(1) Long id, @RequestBody @Valid LanguageDto newLanguageDto) {
        return languageService.put(id, newLanguageDto);
    }

    @PostMapping
    LanguageDto newLanguage(@RequestBody @Valid LanguageDto newLanguageDto) {
        return languageService.save(newLanguageDto);
    }

    @DeleteMapping("{id}")
    void deleteLanguage(@PathVariable("id") @Min(1) Long id) {
        languageService.deleteById(id);
    }
}
