package com.example.Banking.App.service;

import com.example.Banking.App.dto.AccountDto;
import com.example.Banking.App.entity.Account;
import com.example.Banking.App.mapper.AccountMapper;
import com.example.Banking.App.repository.AccountReposirory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
