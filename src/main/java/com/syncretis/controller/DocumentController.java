package com.syncretis.controller;

import com.syncretis.dto.DocumentDto;
import com.syncretis.service.DocumentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/documents")
public class DocumentController {
    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping
    List<DocumentDto> getAllDocuments() {
        return documentService.getAll();
    }

    @GetMapping("{id}")
    DocumentDto getDocument(@PathVariable("id") String id) {
        return documentService.getById(id);
    }

    @PutMapping("{id}")
    DocumentDto updateDocument(@PathVariable("id") String id, @RequestBody @Valid DocumentDto newDocumentDto) {
        return documentService.put(id, newDocumentDto);
    }

    @PostMapping
    DocumentDto newDocument(@RequestBody @Valid DocumentDto documentDto) {
        return documentService.save(documentDto);
    }

    @DeleteMapping("{id}")
    void deleteDocument(@PathVariable("id") String id) {
        documentService.deleteById(id);
    }
}
