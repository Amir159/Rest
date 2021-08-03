package com.syncretis.repository;

import com.syncretis.entity.Language;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LanguageRepository extends CrudRepository<Language, Long> {
    Language findByName(String name);

    List<Language> findAll();
}
