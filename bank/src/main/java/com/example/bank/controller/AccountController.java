package com.example.bank.controller;

import com.example.bank.Repository.AccountsRepository;
import com.example.bank.Repository.CustomerRepository;
import com.example.bank.model.Accounts;
import com.example.bank.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @GetMapping("/myAccount")
    public Accounts getAccountDetails(@RequestParam String email) {

        Optional<Customer> optionalCustomer =  customerRepository.findByEmail(email);

        if(optionalCustomer.isPresent()){
            Accounts accounts= accountsRepository.findByCustomerId(optionalCustomer.get().getId());
            if (accounts != null) {
                return accounts;
            } else {
                return null;
            }
        }else{
            return null;
        }

    }

}