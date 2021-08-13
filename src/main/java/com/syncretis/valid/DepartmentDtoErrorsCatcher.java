package com.syncretis.valid;

import com.syncretis.dto.DepartmentDto;
import com.syncretis.exception.DepartmentBadRequestException;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;

import java.util.Locale;

@Component
public class DepartmentDtoErrorsCatcher {
    private final DepartmentValidator departmentValidator;
    private final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

    public DepartmentDtoErrorsCatcher(DepartmentValidator departmentValidator) {
        this.departmentValidator = departmentValidator;
    }

    public void validateDepartmentDto(DepartmentDto departmentDto) {
        DataBinder dataBinder = new DataBinder(departmentDto);

        dataBinder.addValidators(departmentValidator);
        dataBinder.validate(departmentDto);
        if (dataBinder.getBindingResult().hasErrors()) {
            StringBuilder errors = new StringBuilder();
            for (ObjectError allError : dataBinder.getBindingResult().getAllErrors()) {
                errors.append(messageSource.getMessage(allError, Locale.getDefault())).append("\n");
            }
            throw new DepartmentBadRequestException(errors.toString());
        }
    }
}
