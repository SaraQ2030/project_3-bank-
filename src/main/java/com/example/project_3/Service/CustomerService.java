package com.example.project_3.Service;

import com.example.project_3.API.ApiException;
import com.example.project_3.DTO.CustomerDTO;
import com.example.project_3.DTO.EmployeeDTO;
import com.example.project_3.Model.Customer;
import com.example.project_3.Model.Employee;
import com.example.project_3.Model.User;
import com.example.project_3.Repository.AuthRepository;
import com.example.project_3.Repository.CustomerRepository;
import com.example.project_3.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;


    public Customer getCustomer(Integer id){
        Customer customer=customerRepository.findCustomerById(id);
        if (customer==null)throw new ApiException("customer not found");
        return customer;
    }

//    public void addCustomer(CustomerDTO customerDTO){
//        User user=authRepository.findUserByUsername(customerDTO.getUsername());
//        if (user==null){
//            throw new ApiException("user not found");
//        }
//        Customer customer=new Customer(customerDTO.getUsername(),customerDTO.getPassword(),customerDTO.getName(),
//                customerDTO.getEmail(),customerDTO.getPhoneNumber(), user);
//customerRepository.save(customer);
//        }

        public void updateCustomer(Integer customerId,CustomerDTO customerDTO){
            Customer customer=customerRepository.findCustomerById(customerDTO.getCustomerId());
            if (customer==null){
                throw new ApiException("the customer not found");
            }
            if (customerId.equals(customerDTO.getCustomerId())) {
               customer.setPassword(customerDTO.getPassword());
               customer.setName(customerDTO.getName());
               customer.setUsername(customerDTO.getUsername());
               customer.setEmail(customerDTO.getEmail());
               customer.setPhoneNumber(customerDTO.getPhoneNumber());
             customerRepository.save(customer);
            }
            else throw new ApiException("Not match customer ids");
        }

        public void deleteCustomer(Integer customerId){
          Customer customer=customerRepository.findCustomerById(customerId);
            if (customer==null){
                throw new ApiException("customer not found");
            }
            customerRepository.delete(customer);
        }

}
