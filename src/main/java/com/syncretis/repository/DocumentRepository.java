package com.syncretis.repository;

import com.syncretis.entity.Document;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends CrudRepository<Document, Long> {
    Optional<Document> findById(String id);

    List<Document> findAll();

    boolean existsById(String id);

    void deleteById(String id);
}