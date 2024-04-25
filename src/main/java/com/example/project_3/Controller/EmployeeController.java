package com.example.project_3.Controller;

import com.example.project_3.API.ApiResponse;
import com.example.project_3.DTO.EmployeeDTO;
import com.example.project_3.Model.User;
import com.example.project_3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    @GetMapping("/get")
    public ResponseEntity getEmployee(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(employeeService.getEmployee(user.getEmployee().getId()));
    }

    @PostMapping("/add")
    public ResponseEntity addEmployee( @RequestBody @Valid EmployeeDTO employeeDTO){
        employeeService.addEmployee(employeeDTO);
        return ResponseEntity.status(200).body(new ApiResponse("employee added"));
    }

    @PutMapping("/update")
    public ResponseEntity updateEmployee(@AuthenticationPrincipal User user, @RequestBody @Valid EmployeeDTO employeeDTO){
        employeeService.updateEmployee(user.getEmployee().getId(),employeeDTO);
        return ResponseEntity.status(200).body(new ApiResponse("employee updated"));
    }

    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity deleteEmployee(@PathVariable Integer employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.status(200).body(new ApiResponse("employee deleted"));
    }



}
