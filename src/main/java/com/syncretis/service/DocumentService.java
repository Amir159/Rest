package com.syncretis.service;

import com.syncretis.dto.DocumentDto;
import com.syncretis.entity.Document;
import com.syncretis.mapper.DocumentDtoMapper;
import com.syncretis.repository.DocumentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final DocumentDtoMapper documentDtoMapper = new DocumentDtoMapper();

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public List<DocumentDto> getAll() {
        return documentDtoMapper.mapDocument(documentRepository.findAll());
    }

    public DocumentDto getById(String id) {
        return documentDtoMapper.mapDocument(documentRepository.findById(id).orElse(null));
    }

    public DocumentDto put(String id, DocumentDto documentDto) {
        Document document = documentDtoMapper.mapDocumentDto(documentDto);
        if (documentRepository.existsById(id)) {
            document.setId(documentRepository.findById(id).orElse(null).getId());
            document.setPerson(documentRepository.findById(id).orElse(null).getPerson());
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
        documentRepository.deleteById(id);
    }
}
