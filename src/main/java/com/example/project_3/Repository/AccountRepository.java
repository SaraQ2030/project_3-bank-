package com.example.project_3.Repository;

import com.example.project_3.Model.Account;
import com.example.project_3.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
   Account  findAccountByCustomer_Id(Integer id);
    List<Account> findAccountsByCustomer(Customer customer);
 }
