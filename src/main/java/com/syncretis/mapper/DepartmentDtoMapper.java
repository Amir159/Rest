package com.syncretis.mapper;

import com.syncretis.dto.DepartmentDto;
import com.syncretis.entity.Department;
import com.syncretis.entity.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentDtoMapper {
    public List<DepartmentDto> mapDepartment(List<Department> departments) {
        List<DepartmentDto> departmentsDto = new ArrayList<>();
        for (Department department : departments) {
            departmentsDto.add(createDepartmentDto(department));
        }
        return departmentsDto;
    }

    public DepartmentDto mapDepartment(Department department) {
        return createDepartmentDto(department);
    }

    private DepartmentDto createDepartmentDto(Department department) {
        if (department == null) {
            return null;
        }
        Long id = department.getId();
        String name = department.getName();
        List<Person> personsList = department.getPersonList();
        List<Long> personsId = new ArrayList<>();
        if (personsList != null) {
            for (Person person : personsList) {
                personsId.add(person.getId());
            }
        }
        return new DepartmentDto(id, name, personsId);
    }

    public Department mapDepartmentDto(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setName(departmentDto.getName());
        return department;
    }
}
