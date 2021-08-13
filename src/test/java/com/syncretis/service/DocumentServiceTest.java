package com.syncretis.service;

import com.syncretis.dto.DocumentDto;
import com.syncretis.entity.Document;
import com.syncretis.exception.DocumentNotFoundException;
import com.syncretis.mapper.DocumentDtoMapper;
import com.syncretis.repository.DocumentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class DocumentServiceTest {
    @Mock
    DocumentDtoMapper documentDtoMapperMock;

    @Mock
    DocumentRepository documentRepositoryMock;

    @InjectMocks
    DocumentService documentService;

    @Test
    public void shouldReturnAllDocumentsDto() {
        //GIVEN
        List<Document> documents = new ArrayList<>();
        Document document = new Document(LocalDate.of(2023, 1, 1));
        Document document1 = new Document(LocalDate.of(2025, 3, 3));
        documents.add(document);
        documents.add(document1);

        List<DocumentDto> documentsDto = new ArrayList<>();
        DocumentDto documentDto = new DocumentDto(LocalDate.of(2023, 1, 1));
        DocumentDto documentDto1 = new DocumentDto(LocalDate.of(2025, 3, 3));
        documentsDto.add(documentDto);
        documentsDto.add(documentDto1);

        //WHEN
        Mockito.when(documentRepositoryMock.findAll()).thenReturn(documents);
        Mockito.when(documentDtoMapperMock.mapDocuments(documents)).thenReturn(documentsDto);

        List<DocumentDto> actualDocumentsDto = documentService.getAll();

        //THEN
        Mockito.verify(documentRepositoryMock).findAll();
        Mockito.verify(documentDtoMapperMock).mapDocuments(documents);
        assertThat(actualDocumentsDto).isEqualTo(documentsDto);
    }

    @Test
    public void shouldReturnDocumentDtoById() {
        //GIVEN
        String documentId = "123d";
        Document document = new Document(documentId, LocalDate.of(2023, 1, 1));
        DocumentDto documentDto = new DocumentDto(documentId, LocalDate.of(2023, 1, 1));

        //WHEN
        Mockito.when(documentRepositoryMock.findById(documentId)).thenReturn(Optional.of(document));
        Mockito.when(documentDtoMapperMock.mapDocument(document)).thenReturn(documentDto);

        DocumentDto actualDocumentDto = documentService.getById(documentId);

        //THEN
        Mockito.verify(documentRepositoryMock).findById(documentId);
        Mockito.verify(documentDtoMapperMock).mapDocument(document);
        assertThat(actualDocumentDto).isEqualTo(documentDto);
    }

    @Test
    public void shouldThrowNpeWhenFindById() {
        //GIVEN
        String documentId = "123d";

        //WHEN
        Mockito.when(documentRepositoryMock.findById(documentId)).thenReturn(Optional.empty());

        //THEN
        assertThrows(DocumentNotFoundException.class, () -> documentService.getById(documentId));
    }

    @Test
    public void shouldPutDocumentAndReturnDocumentDtoIfExistById() {
        //GIVEN
        String documentId = "123d";
        Document document = new Document(documentId, LocalDate.of(2025, 3, 3));
        DocumentDto documentDto = new DocumentDto(documentId, LocalDate.of(2025, 3, 3));

        //WHEN
        Mockito.when(documentDtoMapperMock.mapDocumentDto(documentDto)).thenReturn(document);
        Mockito.when(documentRepositoryMock.existsById(documentId)).thenReturn(true);
        Mockito.when(documentRepositoryMock.findById(documentId)).thenReturn(Optional.of(document));
        Mockito.when(documentDtoMapperMock.mapDocument(document)).thenReturn(documentDto);

        DocumentDto actualDocumentDto = documentService.put(documentId, documentDto);

        //THEN
        Mockito.verify(documentDtoMapperMock).mapDocumentDto(documentDto);
        Mockito.verify(documentRepositoryMock).existsById(documentId);
        Mockito.verify(documentRepositoryMock).findById(documentId);
        Mockito.verify(documentRepositoryMock).save(document);
        assertThat(actualDocumentDto).isEqualTo(documentDto);
    }

    @Test
    public void shouldPutDocumentAndReturnDocumentDtoIfDontExistById() {
        //GIVEN
        String documentId = "123d";
        Document document = new Document(documentId, LocalDate.of(2025, 3, 3));
        DocumentDto documentDto = new DocumentDto(documentId, LocalDate.of(2025, 3, 3));

        //WHEN
        Mockito.when(documentDtoMapperMock.mapDocumentDto(documentDto)).thenReturn(document);
        Mockito.when(documentRepositoryMock.existsById(documentId)).thenReturn(false);
        Mockito.when(documentDtoMapperMock.mapDocument(document)).thenReturn(documentDto);

        DocumentDto actualDocumentDto = documentService.put(documentId, documentDto);

        //THEN
        Mockito.verify(documentDtoMapperMock).mapDocumentDto(documentDto);
        Mockito.verify(documentRepositoryMock).existsById(documentId);
        Mockito.verify(documentRepositoryMock, Mockito.times(0)).findById(documentId);
        Mockito.verify(documentRepositoryMock).save(document);
        assertThat(actualDocumentDto).isEqualTo(documentDto);
    }

    @Test
    public void shouldPostDocumentAndReturnDocumentDto() {
        //GIVEN
        Document document = new Document(LocalDate.of(2025, 3, 3));
        DocumentDto documentDto = new DocumentDto(LocalDate.of(2025, 3, 3));

        //WHEN
        Mockito.when(documentDtoMapperMock.mapDocumentDto(documentDto)).thenReturn(document);
        Mockito.when(documentDtoMapperMock.mapDocument(document)).thenReturn(documentDto);

        DocumentDto actualDocumentDto = documentService.save(documentDto);

        //THEN
        Mockito.verify(documentDtoMapperMock).mapDocumentDto(documentDto);
        Mockito.verify(documentRepositoryMock).save(document);
        Mockito.verify(documentDtoMapperMock).mapDocument(document);
        assertThat(actualDocumentDto).isEqualTo(documentDto);
    }

    @Test
    public void shouldDeleteDocument() {
        //GIVEN
        String documentId = "123d";
        Document document = new Document(documentId, LocalDate.of(2023, 1, 1));

        //WHEN
        Mockito.when(documentRepositoryMock.findById(documentId)).thenReturn(Optional.of(document));

        documentService.deleteById(documentId);

        //THEN
        Mockito.verify(documentRepositoryMock).findById(documentId);
        Mockito.verify(documentRepositoryMock).deleteById(documentId);
    }

    @Test
    public void shouldThrowNpeWhenDeleteDocument() {
        //GIVEN
        String documentId = "123d";

        //WHEN
        Mockito.when(documentRepositoryMock.findById(documentId)).thenReturn(Optional.empty());

        //THEN
        assertThrows(DocumentNotFoundException.class, () -> documentService.deleteById(documentId));
    }
}