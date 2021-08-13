package com.syncretis.validator;

import com.syncretis.dto.DepartmentDto;
import com.syncretis.valid.DepartmentValidator;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class DepartmentValidatorTest {
    DepartmentValidator departmentValidator = new DepartmentValidator();

    @Test
    public void shouldCheckSupportedClass() {
        //THEN
        assertThat(departmentValidator.supports(DepartmentDto.class)).isTrue();
    }

    @Test
    public void shouldThrowException() {
        //GIVEN

        DepartmentDto departmentDto = new DepartmentDto(1L, "Dep@rtm3nt");
        BindException error = new BindException(departmentDto, "departmentDto");

        //WHEN
        ValidationUtils.invokeValidator(departmentValidator, departmentDto, error);

        //THEN
        assertThat(error.hasErrors()).isTrue();
    }

    @Test
    public void shouldValidate() {
        //GIVEN
        DepartmentDto departmentDto = new DepartmentDto("Test");
        BindException error = new BindException(departmentDto, "departmentDto");

        //WHEN
        ValidationUtils.invokeValidator(departmentValidator, departmentDto, error);

        //THEN
        assertThat(error.hasErrors()).isFalse();
    }
}
