package com.syncretis.controller;

import com.syncretis.dto.DepartmentDto;
import com.syncretis.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments")
    List<DepartmentDto> all() {
        return departmentService.getAll();
    }

    @GetMapping("/departments/{id}")
    DepartmentDto one(@PathVariable Long id) {
        return departmentService.getById(id);
    }

    @PutMapping("/departments/{id}")
    DepartmentDto updateDepartment(@PathVariable Long id, @RequestBody DepartmentDto newDepartmentDto) {
        return departmentService.put(id, newDepartmentDto);
    }

    @PostMapping("/departments")
    DepartmentDto newDepartment(@RequestBody DepartmentDto departmentDto) {
        return departmentService.save(departmentDto);
    }

    @DeleteMapping("/departments/{id}")
    void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteById(id);
    }
}
