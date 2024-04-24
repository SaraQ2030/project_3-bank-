package com.example.project_3.Service;

import com.example.project_3.API.ApiException;
import com.example.project_3.DTO.EmployeeDTO;
import com.example.project_3.Model.Employee;
import com.example.project_3.Model.User;
import com.example.project_3.Repository.AuthRepository;
import com.example.project_3.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AuthRepository authRepository;

    public Employee getEmployee(Integer id){
     return employeeRepository.findEmployeeById(id);
    }

    public void addEmployee(EmployeeDTO employeeDTO){
User user=authRepository.findUserByUsername(employeeDTO.getUsername());
if(user==null){
    throw new ApiException("employee not found");
}
Employee employee=new Employee(null,employeeDTO.getUsername(), employeeDTO.getPassword(), employeeDTO.getName(), employeeDTO.getEmail(), employeeDTO.getPosition(), employeeDTO.getSalary(), user);
employeeRepository.save(employee);
    }

public void updateEmployee(EmployeeDTO employeeDTO){
    Employee employee=employeeRepository.findEmployeeById(employeeDTO.getEmployeeId());
    if (employee==null){
        throw new ApiException("the employee not found");
    }
    employee.setPosition(employeeDTO.getPosition());
    employee.setSalary(employeeDTO.getSalary());
    employeeRepository.save(employee);
}

}
