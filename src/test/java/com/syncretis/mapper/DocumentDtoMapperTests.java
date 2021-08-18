package com.syncretis.mapper;

import com.syncretis.dto.DocumentDto;
import com.syncretis.entity.Document;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DocumentDtoMapperTests {
    private final DocumentDtoMapper documentDtoMapper = new DocumentDtoMapper();

    private static final LocalDate date1 = LocalDate.of(2025, 1, 1);
    private static final LocalDate date2 = LocalDate.of(2025, 1, 2);
    private static final LocalDate date3 = LocalDate.of(2025, 1, 3);

    @Test
    public void shouldReturnDocument() {
        //GIVEN
        DocumentDto documentDto = createDocumentDto(date1);
        Document expectedDocument = createDocument(date1);
        //WHEN
        Document actualDocument = documentDtoMapper.mapDocumentDto(documentDto);

        //THEN
        assertThat(actualDocument).isEqualTo(expectedDocument);
    }

    @Test
    public void shouldReturnDocumentDto() {
        //GIVEN
        Document document = createDocument(date1);
        DocumentDto expectedDocumentDto = createDocumentDto(date1);

        //WHEN
        DocumentDto actualDocumentDto = documentDtoMapper.mapDocument(document);

        //THEN
        assertThat(actualDocumentDto).isEqualTo(expectedDocumentDto);
    }

    @Test
    public void shouldReturnListDocumentDto() {
        //GIVEN
        List<DocumentDto> expectedDocumentsDto = Arrays.asList(createDocumentDto(date1),
                createDocumentDto(date2),
                createDocumentDto(date3));

        List<Document> documents = Arrays.asList(createDocument(date1),
                createDocument(date2),
                createDocument(date3));

        //WHEN
        List<DocumentDto> actualDocumentsDto = documentDtoMapper.mapDocuments(documents);

        //THEN
        assertThat(actualDocumentsDto).isEqualTo(expectedDocumentsDto);
    }

    private Document createDocument(LocalDate date) {
        return new Document(date);
    }

    private DocumentDto createDocumentDto(LocalDate date) {
        return new DocumentDto(date);
    }
}

