package com.example.project_3.Controller;

import com.example.project_3.API.ApiResponse;
import com.example.project_3.DTO.EmployeeDTO;
import com.example.project_3.Model.Account;
import com.example.project_3.Model.User;
import com.example.project_3.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/account")
public class AccountController {
    private final AccountService accountService;


    @GetMapping("/get")
    public ResponseEntity getBankAccounts(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(accountService.getBankAccounts(user.getCustomer().getId()));
    }

    @PostMapping("/create")
    public ResponseEntity createAccount(@AuthenticationPrincipal User user, @RequestBody @Valid Account account){
        accountService.createBankAccount(user.getCustomer().getId(),account);
        return ResponseEntity.status(200).body(new ApiResponse("account created successfully "));
    }
    @GetMapping("/view-account")
    public ResponseEntity getOneAccount(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(accountService.viewAcountDetail(user.getCustomer().getId()));
    }



}
