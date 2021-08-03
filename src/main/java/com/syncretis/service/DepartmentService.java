package com.syncretis.service;

import com.syncretis.dto.DepartmentDto;
import com.syncretis.entity.Department;
import com.syncretis.mapper.DepartmentDtoMapper;
import com.syncretis.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentDtoMapper departmentDtoMapper;

    public DepartmentService(DepartmentRepository departmentRepository, DepartmentDtoMapper departmentDtoMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentDtoMapper = departmentDtoMapper;
    }

    public List<DepartmentDto> getAll() {
        return departmentDtoMapper.mapDepartment(departmentRepository.findAll());
    }

    public DepartmentDto getById(Long id) {
        return departmentDtoMapper.mapDepartment(departmentRepository.findById(id).orElse(null));
    }

    public DepartmentDto put(Long id, DepartmentDto departmentDto) {
        Department department = departmentDtoMapper.mapDepartmentDto(departmentDto);
        if (departmentRepository.existsById(id)) {
            department.setId(departmentRepository.findById(id).orElse(null).getId());
            department.setPersonList(Objects.requireNonNull(departmentRepository.findById(id).orElse(null)).getPersonList());
        }
        departmentRepository.save(department);
        return departmentDtoMapper.mapDepartment(department);
    }

    public DepartmentDto save(DepartmentDto departmentDto) {
        Department department = departmentDtoMapper.mapDepartmentDto(departmentDto);
        departmentRepository.save(department);
        return departmentDtoMapper.mapDepartment(department);
    }

    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }
}
