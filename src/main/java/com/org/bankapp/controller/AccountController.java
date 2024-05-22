package com.org.bankapp.controller;

import com.org.bankapp.dto.AccountDto;
import com.org.bankapp.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //add account  RESTAPI
    @PostMapping("/add")
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);

    }

    //get account RESTAPI

    @GetMapping("/get/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto =accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    //deposit
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id , @RequestBody Map<String, Double> request){

        Double amount=request.get("amount");
        AccountDto accountDto= accountService.deposit(id,amount);
        return ResponseEntity.ok(accountDto);
    }
    //withdraw
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id , @RequestBody Map<String, Double> request){

        Double amount=request.get("amount");
        AccountDto accountDto= accountService.withdraw(id,amount);
        return ResponseEntity.ok(accountDto);
    }
    //getall
    @GetMapping
    public  ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accounts =accountService.getAllAcount();
        return ResponseEntity.ok(accounts);
    }

    //delete
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("account is deleted successfully");
    }
}
