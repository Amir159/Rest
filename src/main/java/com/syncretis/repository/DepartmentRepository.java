package com.syncretis.repository;

import com.syncretis.entity.Department;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
    List<Department> findAll();

    Optional<Department> findByName(String name);
}
