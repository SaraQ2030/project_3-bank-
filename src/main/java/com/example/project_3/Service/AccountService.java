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

    public void activeAccount(Integer customerId,Integer numAccount){
        Account account=accountRepository.findAccountByCustomer_Id(customerId);
        if (account==null){
            throw new ApiException("not found account ");
        }

        if (account.getAccountNumber().equals(numAccount)){
            if (account.getIsActive()){
                throw new ApiException("the account already active");
            }
            account.setIsActive(true);
            accountRepository.save(account);
        }
        }

    public void blockAccount(Integer numAccount){
        Account account=accountRepository.findAccountByAccountNumber(numAccount);
        if (account==null){
            throw new ApiException("the account not found");
        }
        account.setIsActive(false);
        accountRepository.save(account);
    }



    public void depositMoney(Integer accountId, Double amount) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
         throw new  ApiException("Account not found");
        }
        Double currentBalance = account.getBalance();
        Double newBalance = currentBalance+(amount);
        account.setBalance(newBalance);

        accountRepository.save(account);
    }


    public void withdrawMoney(Integer accountId, Double amount) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new  ApiException("Account not found");
        }

     Double currentBalance = account.getBalance();
        if (currentBalance.compareTo(amount) < 0) {
            throw new ApiException("low balance");
        }

        Double newBalance = currentBalance - (amount);
        account.setBalance(newBalance);
        accountRepository.save(account);
    }

    public void transferFunds(Integer fromAccountId, Integer toAccountId, Double amount) {
        Account fromAccount = accountRepository.findAccountById(fromAccountId);
        if (fromAccount == null) {
            throw new  ApiException("from Account not found");
        }

        Account toAccount = accountRepository.findAccountById(toAccountId);
        if (toAccount == null) {
            throw new  ApiException("to Account not found");
        }
        Double fromAccountBalance = fromAccount.getBalance();
        if (fromAccountBalance.compareTo(amount) < 0) {
            throw new ApiException("Low balance in the account to transe");
        }

        Double newFromAccountBalance = fromAccountBalance-(amount);
        fromAccount.setBalance(newFromAccountBalance);

        Double toAccountBalance = toAccount.getBalance();
        Double newToAccountBalance = toAccountBalance+(amount);
        toAccount.setBalance(newToAccountBalance);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }



}
