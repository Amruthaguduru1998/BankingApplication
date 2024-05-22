package com.org.bankapp.service.impl;

import com.org.bankapp.dto.AccountDto;
import com.org.bankapp.entity.Account;
import com.org.bankapp.mapper.AccountMapper;
import com.org.bankapp.repository.AccountRepository;
import com.org.bankapp.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {

        Account account= AccountMapper.mapToAccount(accountDto);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
       Account account= accountRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Account doest not exits"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit( Long id, double amount) {
        Account account= accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account doest not exits"));

        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount =accountRepository.save(account);
        return  AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account= accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account doest not exits"));
        if(account.getBalance() < amount){
            throw new RuntimeException("insufficient amount");
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAcount() {
        List<Account> accounts =accountRepository.findAll();
        return  accounts.stream().map(account -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account= accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account doest not exits"));
        accountRepository.deleteById(id);

    }

}
