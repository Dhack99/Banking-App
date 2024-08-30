package com.example.Banking.App.service;

import com.example.Banking.App.dto.AccountDto;


public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id,double amount);
    AccountDto withdraw(Long id,double amount);
}
