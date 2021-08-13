package com.syncretis.mapper;

import com.syncretis.dto.DocumentDto;
import com.syncretis.entity.Document;
import com.syncretis.entity.Person;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DocumentDtoMapper {
    public List<DocumentDto> mapDocuments(List<Document> documents) {
        List<DocumentDto> documentsDto = new ArrayList<>();
        for (Document document : documents) {
            documentsDto.add(createDocumentDto(document));
        }
        return documentsDto;
    }

    public DocumentDto mapDocument(Document document) {
        return createDocumentDto(document);
    }

    private DocumentDto createDocumentDto(Document document) {
        if (document == null) {
            return null;
        }
        String id = document.getId();
        LocalDate expiryDate = document.getExpiryDate();
        Person person = document.getPerson();
        Long personId = null;
        if (person != null) {
            personId = person.getId();
        }
        return new DocumentDto(id, expiryDate, personId);
    }

    public Document mapDocumentDto(DocumentDto documentDto) {
        Document document = new Document();
        document.setExpiryDate(documentDto.getExpiryDate());
        return document;
    }
}
