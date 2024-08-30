package com.example.Banking.App.service;

import com.example.Banking.App.dto.AccountDto;
import com.example.Banking.App.entity.Account;
import com.example.Banking.App.mapper.AccountMapper;
import com.example.Banking.App.repository.AccountReposirory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountReposirory accountReposirory;

    public AccountServiceImpl(AccountReposirory accountReposirory){
        this.accountReposirory =accountReposirory;
    }


    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
         Account saveAccount = accountReposirory.save(account);
        return AccountMapper.mapToAccountDto(saveAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
       Account account = accountReposirory
               .findById(id)
               .orElseThrow(()-> new RuntimeException("Account does not exists"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountReposirory
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exists"));

        double total = account.getBalance()+ amount;
        account.setBalance(total);
        Account savedAccount = accountReposirory.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountReposirory
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exists"));

        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficent amount");
        }

        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountReposirory.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);

    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountReposirory.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountReposirory
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exists"));

            accountReposirory.deleteById(id);
    }


}
