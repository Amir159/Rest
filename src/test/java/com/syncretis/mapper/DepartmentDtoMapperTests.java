package com.syncretis.mapper;

import com.syncretis.dto.DepartmentDto;
import com.syncretis.entity.Department;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DepartmentDtoMapperTests {
    private final DepartmentDtoMapper departmentDtoMapper = new DepartmentDtoMapper();

    private final DepartmentDto departmentDto1 = new DepartmentDto("Test1 department");
    private final DepartmentDto departmentDto2 = new DepartmentDto("Test2 department");
    private final DepartmentDto departmentDto3 = new DepartmentDto("Test3 department");

    private final Department department1 = new Department("Test1 department");
    private final Department department2 = new Department("Test2 department");
    private final Department department3 = new Department("Test3 department");

    @Test
    public void shouldReturnDepartment() {
        //WHEN
        Department actualDepartment = departmentDtoMapper.mapDepartmentDto(departmentDto1);

        //THEN
        assertThat(actualDepartment).isEqualTo(department1);
    }

    @Test
    public void shouldReturnDepartmentDto() {
        //WHEN
        DepartmentDto actualDepartmentDto = departmentDtoMapper.mapDepartment(department1);

        //THEN
        assertThat(actualDepartmentDto).isEqualTo(departmentDto1);
    }

    @Test
    public void shouldReturnListDepartmentDto() {
        //GIVEN
        List<DepartmentDto> expectedDepartmentsDto = new ArrayList<>();
        expectedDepartmentsDto.add(departmentDto1);
        expectedDepartmentsDto.add(departmentDto2);
        expectedDepartmentsDto.add(departmentDto3);

        List<Department> departments = new ArrayList<>();
        departments.add(department1);
        departments.add(department2);
        departments.add(department3);

        //WHEN
        List<DepartmentDto> actualDepartmentsDto = departmentDtoMapper.mapDepartments(departments);

        //THEN
        assertThat(actualDepartmentsDto).isNotEmpty().hasSize(3).isEqualTo(expectedDepartmentsDto);
    }
}
