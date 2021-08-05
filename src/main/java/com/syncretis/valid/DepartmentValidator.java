package com.syncretis.valid;

import com.syncretis.dto.DepartmentDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DepartmentValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return DepartmentDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DepartmentDto departmentDto = (DepartmentDto) target;
        if (departmentDto.getId() != null) {
            errors.rejectValue("name", "400", "id should be null");
        }
        if (departmentDto.getName().isBlank() || departmentDto.getName() == null) {
            errors.rejectValue("name", "400", "name should not be null");
        }
        if (!departmentDto.getName().matches("^[a-zA-Z ]+$")) {
            errors.rejectValue("name", "400", "name should contains only letters");
        }
        if (departmentDto.getPersonsId() != null) {
            errors.rejectValue("name", "400", "person id should be null");
        }
    }
}