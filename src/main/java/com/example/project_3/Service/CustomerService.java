package com.example.project_3.Service;

import com.example.project_3.API.ApiException;
import com.example.project_3.DTO.CustomerDTO;
import com.example.project_3.Model.Customer;
import com.example.project_3.Model.User;
import com.example.project_3.Repository.AuthRepository;
import com.example.project_3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;


    public void addCustomer(CustomerDTO customerDTO){
        User user=authRepository.findUserByUsername(customerDTO.getUsername());
        if (user==null){
            throw new ApiException("user not found");
        }

//        Customer customer=new Customer(null,customerDTO.getPhoneNumber(),user);
//        customerRepository.save(customer);
    }


    public void updateCustomer(){}
}
