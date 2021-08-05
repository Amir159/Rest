package com.syncretis.service;

import com.syncretis.dto.DepartmentDto;
import com.syncretis.entity.Department;
import com.syncretis.exception.DepartmentBadRequestException;
import com.syncretis.mapper.DepartmentDtoMapper;
import com.syncretis.repository.DepartmentRepository;
import com.syncretis.valid.DepartmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentValidator personValidator;

    private static final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

    static {
        messageSource.setBasename("message");
    }


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
        validateDepartment(departmentDto);
        Department department = departmentDtoMapper.mapDepartmentDto(departmentDto);
        if (departmentRepository.existsById(id)) {
            department.setId(Objects.requireNonNull(departmentRepository.findById(id).orElse(null).getId()));
            department.setPersonList(Objects.requireNonNull(departmentRepository.findById(id).orElse(null)).getPersonList());
        }
        departmentRepository.save(department);
        return departmentDtoMapper.mapDepartment(department);
    }

    public DepartmentDto save(DepartmentDto departmentDto) {
        validateDepartment(departmentDto);
        Department department = departmentDtoMapper.mapDepartmentDto(departmentDto);
        departmentRepository.save(department);
        return departmentDtoMapper.mapDepartment(department);
    }

    public void validateDepartment(DepartmentDto departmentDto) {
        DataBinder dataBinder = new DataBinder(departmentDto);
        dataBinder.addValidators(personValidator);
        dataBinder.validate(departmentDto);
        if (dataBinder.getBindingResult().hasErrors()) {
            StringBuilder errors = new StringBuilder();
            for (ObjectError allError : dataBinder.getBindingResult().getAllErrors()) {
                errors.append(messageSource.getMessage(allError, Locale.getDefault())).append("\n");
            }
            throw new DepartmentBadRequestException(errors.toString());
        }
    }

    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }
}
