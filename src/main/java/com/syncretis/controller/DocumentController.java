package com.syncretis.controller;

import com.syncretis.dto.DocumentDto;
import com.syncretis.service.DocumentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DocumentController {
    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/documents")
    List<DocumentDto> all() {
        return documentService.getAll();
    }

    @GetMapping("/documents/{id}")
    DocumentDto one(@PathVariable String id) {
        return documentService.getById(id);
    }

    @PutMapping("/documents/{id}")
    DocumentDto updateDocument(@PathVariable String id, @RequestBody DocumentDto newDocumentDto) {
        return documentService.put(id, newDocumentDto);
    }

    @PostMapping("/documents")
    DocumentDto newDocument(@RequestBody DocumentDto documentDto) {
        return documentService.save(documentDto);
    }

    @DeleteMapping("/documents/{id}")
    void deleteDocument(@PathVariable String id) {
        documentService.deleteById(id);
    }
}
