package com.example.project_3.Service;

import com.example.project_3.API.ApiException;
import com.example.project_3.Model.Account;
import com.example.project_3.Model.Customer;
import com.example.project_3.Repository.AccountRepository;
import com.example.project_3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;


    public List<Account>  getBankAccounts(Integer customer_id){
        Customer customer =customerRepository.findCustomerById(customer_id);
       return accountRepository.findAccountsByCustomer(customer);
    }

    public void createBankAccount(Integer customer_id,Account account){
        Customer customer=customerRepository.findCustomerById(customer_id);
        account.setCustomer(customer);
        accountRepository.save(account);
    }

    public Account viewAcountDetail(Integer custumerId){
        Account account=accountRepository.findAccountByCustomer_Id(custumerId);
        if (account==null){
            throw new ApiException("not found account ");
        }
        return account;

    }

    public void activeAccount(){

    }






//public int transfer(Account c ,int amount) {
//    Account c
//    if (amount > c.Balance) {
//        System.out.println("The amount is out of balance ");
//    }
//else {
//        Balance += amount;
//    }
//return Balance;
//}



//    public int credit(int amount){
//        if(amount>Balance){
//            System.out.printf("The balance is low");
//        }else if(amount<=Balance){
//            Balance=Balance-amount;
////        System.out.println("The updated balance : "+new_Balance);
//        }
//
//        return Balance;
//    }





//    public int debit(int amount ){
//        int new_Balance=0;
//        if(amount>0){
//            new_Balance=amount+Balance;
//        }
//        return new_Balance;
//    }




//    public int trans(Account to, int amount) {
//        if(Balance >= amount){
////            from.Balance = from.Balance - amount;
//            to.Balance = to.Balance + amount;
//            System.out.println("successful transfer");
//        } else {
//            System.out.println("the Balance is low");
//        }
//        return amount;
//    }
}
