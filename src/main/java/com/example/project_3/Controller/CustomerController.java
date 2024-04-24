package com.example.project_3.Controller;

import com.example.project_3.API.ApiResponse;
import com.example.project_3.DTO.CustomerDTO;
import com.example.project_3.Model.User;
import com.example.project_3.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
//    @GetMapping("/get")
//    public ResponseEntity getUser(@AuthenticationPrincipal User user){
//        return ResponseEntity.status(200).body(customerService.getCustomer(user.getUsername(), user.getPassword()));
//    }
//    @PostMapping("/add")
//    public ResponseEntity addCustomer(@AuthenticationPrincipal User user, @RequestBody @Valid CustomerDTO customerDTO){
//        customerService.addCustomer(user.getId(),customerDTO);
//        return ResponseEntity.status(200).body(new ApiResponse("customer added"));
//    }

}
