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

    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    List<DocumentDto> getAllDocuments() {
        return documentService.getAll();
    }

    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("{id}")
    DocumentDto getDocument(@PathVariable("id") String id) {
        return documentService.getById(id);
    }

    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("{id}")
    DocumentDto updateDocument(@PathVariable("id") String id, @RequestBody @Valid DocumentDto newDocumentDto) {
        return documentService.put(id, newDocumentDto);
    }

    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    DocumentDto newDocument(@RequestBody @Valid DocumentDto documentDto) {
        return documentService.save(documentDto);
    }

    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    void deleteDocument(@PathVariable("id") String id) {
        documentService.deleteById(id);
    }
}
