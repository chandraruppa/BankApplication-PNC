package com.pnc.account.pncbank.controller;


import com.pnc.account.pncbank.util.AccountBalanceUtil;
import com.pnc.account.pncbank.dto.AccountResponse;
import com.pnc.account.pncbank.dto.AccountRequest;
import com.pnc.account.pncbank.service.AccountBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author c.ruppa
 * @Description: Purpose of this Class is to Create Account & Checks Balances for an account
 */

@CrossOrigin
@RestController
public class AccountBalanceController {

    @Autowired
    private AccountBalanceService accountBalanceService;

    @PostMapping("/getAccountBalance")
    public ResponseEntity<Object> getAccountBalance(@Valid @RequestBody AccountRequest accountRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(AccountBalanceUtil.getBindingErrorList(bindingResult),HttpStatus.OK);
        }
        Object accountBalance = accountBalanceService.getAccountBalance(accountRequest);
        return new ResponseEntity<>(accountBalance, HttpStatus.OK);
    }


    @PostMapping("/createAccount")
    public ResponseEntity<Object> createAccount(@Valid @RequestBody AccountRequest accountRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(AccountBalanceUtil.getBindingErrorList(bindingResult), HttpStatus.OK);
        }
        AccountResponse accountResponse = accountBalanceService.createAccount(accountRequest);
        return new ResponseEntity<>(accountResponse, HttpStatus.OK);
    }

}
