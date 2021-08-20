package com.syncretis.validator;

import com.syncretis.dto.DepartmentDto;
import com.syncretis.valid.DepartmentValidator;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class DepartmentValidatorTest {
    DepartmentValidator departmentValidator = new DepartmentValidator();
    private static final String expectedIdNull = "id should be null";
    private static final String expectedNameNull = "name should not be null";
    private static final String expectedNameLetters = "name should contains only letters";
    private static final String expectedPersonIdNull = "person id should be null";

    @Test
    public void shouldCheckSupportedClass() {
        //THEN
        assertThat(departmentValidator.supports(DepartmentDto.class)).isTrue();
    }

    @Test
    public void shouldReturnErrorNullId() {
        //GIVEN
        DepartmentDto departmentDto = new DepartmentDto(1L, "Test");
        BindException error = new BindException(departmentDto, "departmentDto");

        //WHEN
        departmentValidator.validate(departmentDto, error);
        String actualErrorMessage = "";
        for (ObjectError allError : error.getBindingResult().getAllErrors()) {
            actualErrorMessage = allError.getDefaultMessage();
        }

        //THEN
        assertThat(actualErrorMessage).isEqualTo(expectedIdNull);
        assertThat(error.getErrorCount()).isEqualTo(1);
    }

    @Test
    public void shouldReturnErrorNotNullName() {
        //GIVEN
        DepartmentDto departmentDto = new DepartmentDto("");
        BindException error = new BindException(departmentDto, "departmentDto");

        //WHEN
        departmentValidator.validate(departmentDto, error);
        String actualErrorMessage = "";
        for (ObjectError allError : error.getBindingResult().getAllErrors()) {
            actualErrorMessage = allError.getDefaultMessage();
        }

        //THEN
        assertThat(actualErrorMessage).isEqualTo(expectedNameNull);
        assertThat(error.getErrorCount()).isEqualTo(1);
    }

    @Test
    public void shouldReturnErrorNotLettersName() {
        //GIVEN
        DepartmentDto departmentDto = new DepartmentDto("T35t");
        BindException error = new BindException(departmentDto, "departmentDto");

        //WHEN
        departmentValidator.validate(departmentDto, error);
        String actualErrorMessage = "";
        for (ObjectError allError : error.getBindingResult().getAllErrors()) {
            actualErrorMessage = allError.getDefaultMessage();
        }

        //THEN
        assertThat(actualErrorMessage).isEqualTo(expectedNameLetters);
        assertThat(error.getErrorCount()).isEqualTo(1);
    }

    @Test
    public void shouldReturnErrorNotNullPersonId() {
        //GIVEN
        DepartmentDto departmentDto = new DepartmentDto(null, "Test", List.of(1L, 3L));
        BindException error = new BindException(departmentDto, "departmentDto");

        //WHEN
        departmentValidator.validate(departmentDto, error);
        String actualErrorMessage = "";
        for (ObjectError allError : error.getBindingResult().getAllErrors()) {
            actualErrorMessage = allError.getDefaultMessage();
        }

        //THEN
        assertThat(actualErrorMessage).isEqualTo(expectedPersonIdNull);
        assertThat(error.getErrorCount()).isEqualTo(1);
    }

    @Test
    public void shouldReturnErrors() {
        //GIVEN
        DepartmentDto departmentDto = new DepartmentDto(1L, "Te1st");
        BindException error = new BindException(departmentDto, "departmentDto");
        List<String> expectedErrorMessage = Arrays.asList(expectedIdNull, expectedNameLetters);

        //WHEN
        departmentValidator.validate(departmentDto, error);

        List<ObjectError> allErrors = error.getBindingResult().getAllErrors();

        List<String> actualErrorMessage = allErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        //THEN
        assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage);
        assertThat(error.getErrorCount()).isEqualTo(2);
    }

    @Test
    public void shouldNotReturnErrors() {
        //GIVEN
        DepartmentDto departmentDto = new DepartmentDto("Test");
        BindException error = new BindException(departmentDto, "departmentDto");

        //WHEN
        departmentValidator.validate(departmentDto, error);

        //THEN
        assertThat(error.getErrorCount()).isEqualTo(0);
    }
}