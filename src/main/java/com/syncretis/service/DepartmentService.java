package com.syncretis.service;

import com.syncretis.dto.DepartmentDto;
import com.syncretis.entity.Department;
import com.syncretis.exception.DepartmentNotFoundException;
import com.syncretis.mapper.DepartmentDtoMapper;
import com.syncretis.repository.DepartmentRepository;
import com.syncretis.valid.DepartmentDtoErrorsCatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentDtoMapper departmentDtoMapper;
    private final DepartmentDtoErrorsCatcher departmentDtoErrorsCatcher;

    public DepartmentService(DepartmentRepository departmentRepository,
                             DepartmentDtoMapper departmentDtoMapper, DepartmentDtoErrorsCatcher departmentDtoErrorsCatcher) {
        this.departmentRepository = departmentRepository;
        this.departmentDtoMapper = departmentDtoMapper;
        this.departmentDtoErrorsCatcher = departmentDtoErrorsCatcher;
    }

    public List<DepartmentDto> getAll() {
        return departmentDtoMapper.mapDepartments(departmentRepository.findAll());
    }

    public DepartmentDto getById(Long id) {
        return departmentDtoMapper.mapDepartment(departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(HttpStatus.NOT_FOUND)));
    }

    public DepartmentDto put(Long id, DepartmentDto departmentDto) {
        departmentDtoErrorsCatcher.validateDepartmentDto(departmentDto);
        Department department = departmentDtoMapper.mapDepartmentDto(departmentDto);
        if (departmentRepository.existsById(id)) {
            Department newDepartment = departmentRepository.findById(id).orElse(null);

            department.setId(newDepartment.getId());
            department.setPersonList(newDepartment.getPersonList());
        }
        departmentRepository.save(department);
        return departmentDtoMapper.mapDepartment(department);
    }

    public DepartmentDto save(DepartmentDto departmentDto) {
        departmentDtoErrorsCatcher.validateDepartmentDto(departmentDto);
        Department department = departmentDtoMapper.mapDepartmentDto(departmentDto);
        departmentRepository.save(department);
        return departmentDtoMapper.mapDepartment(department);
    }

    public void deleteById(Long id) {
        departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(HttpStatus.NOT_FOUND));
        departmentRepository.deleteById(id);
    }
}