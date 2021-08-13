package com.syncretis.service;

import com.syncretis.dto.DocumentDto;
import com.syncretis.entity.Document;
import com.syncretis.exception.DocumentNotFoundException;
import com.syncretis.mapper.DocumentDtoMapper;
import com.syncretis.repository.DocumentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentDtoMapper documentDtoMapper;

    public DocumentService(DocumentRepository documentRepository, DocumentDtoMapper documentDtoMapper) {
        this.documentRepository = documentRepository;
        this.documentDtoMapper = documentDtoMapper;
    }

    public List<DocumentDto> getAll() {
        return documentDtoMapper.mapDocuments(documentRepository.findAll());
    }

    public DocumentDto getById(String id) {
        return documentDtoMapper.mapDocument(documentRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException(HttpStatus.NOT_FOUND)));
    }

    public DocumentDto put(String id, DocumentDto documentDto) {
        Document document = documentDtoMapper.mapDocumentDto(documentDto);
        if (documentRepository.existsById(id)) {
            Document newDocument = documentRepository.findById(id).orElse(null);

            document.setId(newDocument.getId());
            document.setPerson(newDocument.getPerson());
        }
        documentRepository.save(document);
        return documentDtoMapper.mapDocument(document);
    }

    public DocumentDto save(DocumentDto documentDto) {
        Document document = documentDtoMapper.mapDocumentDto(documentDto);
        documentRepository.save(document);
        return documentDtoMapper.mapDocument(document);
    }

    @Transactional
    public void deleteById(String id) {
        documentRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException(HttpStatus.NOT_FOUND));
        documentRepository.deleteById(id);
    }
}