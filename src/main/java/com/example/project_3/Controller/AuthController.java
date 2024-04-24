package com.example.project_3.Controller;

import com.example.project_3.API.ApiException;
import com.example.project_3.API.ApiResponse;
import com.example.project_3.Model.User;
import com.example.project_3.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthService authService;
    @GetMapping("/get-all")
    public ResponseEntity getAllUser(){
        return ResponseEntity.status(200).body(authService.getAllUsers() );
    }

    @PostMapping("/register/customer")
    public ResponseEntity customerRegister(@RequestBody @Valid User user){
        authService.registerCustomer(user);
        return ResponseEntity.status(200).body(new ApiResponse("Welcome to our system :)"));
    }
    @PostMapping("/register/employee")
    public ResponseEntity employeeRegister(@RequestBody @Valid User user){
        authService.registerEmployee(user);
        return ResponseEntity.status(200).body(new ApiResponse("Employee added to the system successfully"));
    }
        @PostMapping("/login/{username}/{password}")
    public ResponseEntity login(@PathVariable String username,@PathVariable String password){
        authService.login(username,password);
        return ResponseEntity.status(200).body(new ApiResponse("you are logged in  :)"));
    }

//    @PostMapping("/register")
//    public ResponseEntity register(@RequestBody @Valid User user){
//        authService.register(user);
//        return ResponseEntity.status(200).body(new ApiResponse("Welcome to our system :)"));
//    }

    @DeleteMapping("/delete/{usernameDel}")
    public ResponseEntity delete(@PathVariable String usernameDel){
        authService.deleteUser(usernameDel);
        return ResponseEntity.status(200).body(new ApiResponse("deleted"));
    }


    @PutMapping("/update/{username}")
    public ResponseEntity updateUser(@PathVariable String username,@RequestBody @Valid User user){
        authService.updateUser(username,user);
        return ResponseEntity.status(200).body(new ApiResponse("user updated successfully"));
    }


}
