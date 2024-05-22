package com.org.bankapp.service;

import com.org.bankapp.dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id,double amount);

    AccountDto withdraw(Long id,double amount);
    List<AccountDto> getAllAcount();

    void deleteAccount(Long id);


}
