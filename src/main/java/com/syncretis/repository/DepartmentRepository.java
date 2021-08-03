package com.syncretis.repository;

import com.syncretis.entity.Department;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
    List<Department> findAll();

    Department findByName(String name);
}
