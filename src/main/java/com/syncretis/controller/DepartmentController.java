package com.syncretis.controller;

import com.syncretis.dto.DepartmentDto;
import com.syncretis.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    ResponseEntity<List<DepartmentDto>> all() {
        return ResponseEntity.ok(departmentService.getAll());
    }

    @GetMapping("{id}")
    DepartmentDto one(@PathVariable("id") @Min(0) Long id) {
        return departmentService.getById(id);
    }

    @PutMapping("{id}")
    DepartmentDto updateDepartment(@PathVariable("id") @Min(0) Long id, @RequestBody @Valid DepartmentDto newDepartmentDto) {
        return departmentService.put(id, newDepartmentDto);
    }

    @PostMapping
    DepartmentDto newDepartment(@RequestBody /*@Valid*/ DepartmentDto departmentDto) {
        return departmentService.save(departmentDto);
    }

    @DeleteMapping("{id}")
    void deleteDepartment(@PathVariable("id") Long id) {
        departmentService.deleteById(id);
    }
}
