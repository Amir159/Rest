package com.syncretis.service;

import com.syncretis.dto.DepartmentDto;
import com.syncretis.entity.Department;
import com.syncretis.exception.DepartmentBadRequestException;
import com.syncretis.exception.DepartmentNotFoundException;
import com.syncretis.mapper.DepartmentDtoMapper;
import com.syncretis.repository.DepartmentRepository;
import com.syncretis.valid.DepartmentDtoErrorsCatcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    DepartmentDtoMapper departmentDtoMapperMock;

    @Mock
    DepartmentRepository departmentRepositoryMock;

    @Mock
    DepartmentDtoErrorsCatcher departmentDtoErrorsCatcher;

    @InjectMocks
    DepartmentService departmentService;

    @Test
    public void shouldReturnAllDepartmentsDto() {
        //GIVEN
        List<Department> departments = new ArrayList<>();
        Department department = new Department("Test");
        Department department1 = new Department("Test1");
        departments.add(department);
        departments.add(department1);

        List<DepartmentDto> departmentsDto = new ArrayList<>();
        DepartmentDto departmentDto = new DepartmentDto("Test");
        DepartmentDto departmentDto1 = new DepartmentDto("Test1");
        departmentsDto.add(departmentDto);
        departmentsDto.add(departmentDto1);

        //WHEN
        Mockito.when(departmentRepositoryMock.findAll()).thenReturn(departments);
        Mockito.when(departmentDtoMapperMock.mapDepartments(departments)).thenReturn(departmentsDto);

        List<DepartmentDto> actualDepartmentsDto = departmentService.getAll();

        //THEN
        Mockito.verify(departmentRepositoryMock).findAll();
        Mockito.verify(departmentDtoMapperMock).mapDepartments(departments);
        assertThat(actualDepartmentsDto).isEqualTo(departmentsDto);
    }

    @Test
    public void shouldReturnDepartmentDtoById() {
        //GIVEN
        Long departmentId = 1L;
        Department department = new Department(departmentId, "Test");
        DepartmentDto departmentDto = new DepartmentDto(departmentId, "Test");

        //WHEN
        Mockito.when(departmentRepositoryMock.findById(departmentId)).thenReturn(Optional.of(department));
        Mockito.when(departmentDtoMapperMock.mapDepartment(department)).thenReturn(departmentDto);

        DepartmentDto actualDepartmentDto = departmentService.getById(departmentId);

        //THEN
        Mockito.verify(departmentRepositoryMock).findById(departmentId);
        Mockito.verify(departmentDtoMapperMock).mapDepartment(department);
        assertThat(actualDepartmentDto).isEqualTo(departmentDto);
    }

    @Test
    public void shouldThrowNpeWhenFindById() {
        //GIVEN
        Long departmentId = 1L;

        //WHEN
        Mockito.when(departmentRepositoryMock.findById(Mockito.any())).thenReturn(Optional.empty());

        //THEN
        assertThrows(DepartmentNotFoundException.class, () -> departmentService.getById(departmentId));
    }

    @Test
    public void shouldPutDepartmentAndReturnDepartmentDtoIfExistById() {
        //GIVEN
        Long departmentId = 1L;
        Department department = new Department(1L, "Test");
        DepartmentDto departmentDto = new DepartmentDto("Test");

        //WHEN
        Mockito.when(departmentDtoMapperMock.mapDepartmentDto(departmentDto)).thenReturn(department);
        Mockito.when(departmentRepositoryMock.existsById(departmentId)).thenReturn(true);
        Mockito.when(departmentRepositoryMock.findById(departmentId)).thenReturn(Optional.of(department));
        Mockito.when(departmentDtoMapperMock.mapDepartment(department)).thenReturn(departmentDto);

        DepartmentDto actualDepartmentDto = departmentService.put(departmentId, departmentDto);

        //THEN
        Mockito.verify(departmentDtoMapperMock).mapDepartmentDto(departmentDto);
        Mockito.verify(departmentRepositoryMock).existsById(departmentId);
        Mockito.verify(departmentRepositoryMock).findById(departmentId);
        Mockito.verify(departmentRepositoryMock).save(department);
        assertThat(actualDepartmentDto).isEqualTo(departmentDto);
    }

    @Test
    public void shouldPutDepartmentAndReturnDepartmentDtoIfDontExistById() {
        //GIVEN
        Long departmentId = 1L;
        Department department = new Department(departmentId, "Test");
        DepartmentDto departmentDto = new DepartmentDto("Test");

        //WHEN
        Mockito.when(departmentDtoMapperMock.mapDepartmentDto(departmentDto)).thenReturn(department);
        Mockito.when(departmentRepositoryMock.existsById(departmentId)).thenReturn(false);
        Mockito.when(departmentDtoMapperMock.mapDepartment(department)).thenReturn(departmentDto);

        DepartmentDto actualDepartmentDto = departmentService.put(departmentId, departmentDto);

        //THEN
        Mockito.verify(departmentDtoMapperMock).mapDepartmentDto(departmentDto);
        Mockito.verify(departmentRepositoryMock).existsById(departmentId);
        Mockito.verify(departmentRepositoryMock, Mockito.times(0)).findById(departmentId);
        Mockito.verify(departmentRepositoryMock).save(department);
        assertThat(actualDepartmentDto).isEqualTo(departmentDto);
    }

    @Test
    public void shouldPutDepartmentAndThrowException() {
        //GIVEN
        Long departmentId = 1L;
        DepartmentDto departmentDto = new DepartmentDto("Test");

        //WHEN

        Mockito.doThrow(new DepartmentBadRequestException("name should not be null\n")).when(departmentDtoErrorsCatcher).validateDepartmentDto(departmentDto);

        //THEN
        assertThatThrownBy(() -> departmentService.put(departmentId, departmentDto))
                .isExactlyInstanceOf(DepartmentBadRequestException.class);
    }

    @Test
    public void shouldPostDepartmentAndReturnDepartmentDto() {
        //GIVEN
        Department department = new Department("Test");
        DepartmentDto departmentDto = new DepartmentDto("Test");

        //WHEN
        Mockito.when(departmentDtoMapperMock.mapDepartmentDto(departmentDto)).thenReturn(department);
        Mockito.when(departmentDtoMapperMock.mapDepartment(department)).thenReturn(departmentDto);

        DepartmentDto actualDepartmentDto = departmentService.save(departmentDto);

        //THEN
        Mockito.verify(departmentDtoMapperMock).mapDepartmentDto(departmentDto);
        Mockito.verify(departmentRepositoryMock).save(department);
        Mockito.verify(departmentDtoMapperMock).mapDepartment(department);
        assertThat(actualDepartmentDto).isEqualTo(departmentDto);
    }

    @Test
    public void shouldPostDepartmentAndThrowException() {
        //GIVEN
        DepartmentDto departmentDto = new DepartmentDto("Test");

        //WHEN
        Mockito.doThrow(new DepartmentBadRequestException("name should not be null\n")).when(departmentDtoErrorsCatcher).validateDepartmentDto(departmentDto);

        //THEN
        assertThatThrownBy(() -> departmentService.save(departmentDto))
                .isExactlyInstanceOf(DepartmentBadRequestException.class);
    }

    @Test
    public void shouldDeleteDepartment() {
        //GIVEN
        Long departmentId = 1L;
        Department department = new Department(departmentId, "Test");

        //WHEN
        Mockito.when(departmentRepositoryMock.findById(departmentId)).thenReturn(Optional.of(department));

        departmentService.deleteById(departmentId);

        //THEN
        Mockito.verify(departmentRepositoryMock).findById(departmentId);
        Mockito.verify(departmentRepositoryMock).deleteById(departmentId);
    }

    @Test
    public void shouldThrowNpeWhenDeleteDepartment() {
        //GIVEN
        Long departmentId = 1L;

        //WHEN
        Mockito.when(departmentRepositoryMock.findById(Mockito.any())).thenReturn(Optional.empty());

        //THEN
        assertThrows(DepartmentNotFoundException.class, () -> departmentService.deleteById(departmentId));
    }
}