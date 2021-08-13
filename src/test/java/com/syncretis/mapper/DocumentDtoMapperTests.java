package com.syncretis.mapper;

import com.syncretis.dto.DocumentDto;
import com.syncretis.entity.Document;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DocumentDtoMapperTests {
    private final DocumentDtoMapper documentDtoMapper = new DocumentDtoMapper();
    private final DocumentDto documentDto1 = new DocumentDto(LocalDate.of(2025, 1, 1));
    private final DocumentDto documentDto2 = new DocumentDto(LocalDate.of(2025, 1, 2));
    private final DocumentDto documentDto3 = new DocumentDto(LocalDate.of(2025, 1, 3));
    private final Document document1 = new Document(LocalDate.of(2025, 1, 1));
    private final Document document2 = new Document(LocalDate.of(2025, 1, 2));
    private final Document document3 = new Document(LocalDate.of(2025, 1, 3));

    @Test
    public void shouldReturnDocument() {
        //WHEN
        Document actualDocument = documentDtoMapper.mapDocumentDto(documentDto1);

        //THEN
        assertThat(actualDocument).isEqualTo(document1);
    }

    @Test
    public void shouldReturnDocumentDto() {
        //WHEN
        DocumentDto actualDocumentDto = documentDtoMapper.mapDocument(document1);

        //THEN
        assertThat(actualDocumentDto).isEqualTo(documentDto1);
    }

    @Test
    public void shouldReturnListDocumentDto() {
        //GIVEN
        List<DocumentDto> expectedDocumentsDto = new ArrayList<>();
        expectedDocumentsDto.add(documentDto1);
        expectedDocumentsDto.add(documentDto2);
        expectedDocumentsDto.add(documentDto3);

        List<Document> documents = new ArrayList<>();
        documents.add(document1);
        documents.add(document2);
        documents.add(document3);

        //WHEN
        List<DocumentDto> actualDocumentsDto = documentDtoMapper.mapDocuments(documents);

        //THEN
        assertThat(actualDocumentsDto).isEqualTo(expectedDocumentsDto);
    }
}

