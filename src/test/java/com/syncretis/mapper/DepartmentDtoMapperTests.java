package com.syncretis.mapper;

import com.syncretis.dto.DepartmentDto;
import com.syncretis.entity.Department;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DepartmentDtoMapperTests {
    private final DepartmentDtoMapper departmentDtoMapper = new DepartmentDtoMapper();

    private static final String departmentName1 = "Test1 department";
    private static final String departmentName2 = "Test2 department";
    private static final String departmentName3 = "Test3 department";

    @Test
    public void shouldReturnDepartment() {
        //GIVEN
        DepartmentDto departmentDto = createDepartmentDto(departmentName1);
        Department expectedDepartment = createDepartment(departmentName1);

        //WHEN
        Department actualDepartment = departmentDtoMapper.mapDepartmentDto(departmentDto);

        //THEN
        assertThat(actualDepartment).isEqualTo(expectedDepartment);
    }

    @Test
    public void shouldReturnDepartmentDto() {
        //GIVEN
        Department department = createDepartment(departmentName1);
        DepartmentDto expectedDepartment = createDepartmentDto(departmentName1);

        //WHEN
        DepartmentDto actualDepartmentDto = departmentDtoMapper.mapDepartment(department);

        //THEN
        assertThat(actualDepartmentDto).isEqualTo(expectedDepartment);
    }

    @Test
    public void shouldReturnListDepartmentDto() {
        //GIVEN
        List<DepartmentDto> expectedDepartmentsDto = Arrays.asList(createDepartmentDto(departmentName1),
                createDepartmentDto(departmentName2),
                createDepartmentDto(departmentName3));

        List<Department> departments = Arrays.asList(createDepartment(departmentName1),
                createDepartment(departmentName2),
                createDepartment(departmentName3));

        //WHEN
        List<DepartmentDto> actualDepartmentsDto = departmentDtoMapper.mapDepartments(departments);

        //THEN
        assertThat(actualDepartmentsDto).isNotEmpty().hasSize(3).isEqualTo(expectedDepartmentsDto);
    }

    private Department createDepartment(String name) {
        return new Department(name);
    }

    private DepartmentDto createDepartmentDto(String name) {
        return new DepartmentDto(name);
    }
}
