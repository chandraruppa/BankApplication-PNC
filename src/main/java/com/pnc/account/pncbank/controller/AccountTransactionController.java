package com.pnc.account.pncbank.controller;

import com.pnc.account.pncbank.dto.AccountFilterRequest;
import com.pnc.account.pncbank.dto.AccountTransactionRequest;
import com.pnc.account.pncbank.service.AccountTransactionService;
import com.pnc.account.pncbank.util.AccountBalanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin
@RestController
public class AccountTransactionController {

    @Autowired
    private AccountTransactionService accountTransactionService;

    @PostMapping("/getTransactionList/ByDate")
    public ResponseEntity<Object> getTransactionListByDate(@Valid @RequestBody AccountFilterRequest accountFilterRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity(AccountBalanceUtil.getBindingErrorList(bindingResult), HttpStatus.OK);
        }
        return new ResponseEntity(accountTransactionService.getTransactionListByDate(accountFilterRequest), HttpStatus.OK);
    }

    @PostMapping("/getTransactionList/ByType")
    public ResponseEntity<Object> getTransactionsByType(@Valid @RequestBody AccountFilterRequest accountFilterRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity(AccountBalanceUtil.getBindingErrorList(bindingResult), HttpStatus.OK);
        }
        return new ResponseEntity(accountTransactionService.getTransactionListByType(accountFilterRequest), HttpStatus.OK);
    }

    @PostMapping("/createTransaction")
    public ResponseEntity<Object> saveTransaction(@Valid @RequestBody AccountTransactionRequest accountTransactionRequest, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return new ResponseEntity(AccountBalanceUtil.getBindingErrorList(bindingResult), HttpStatus.OK);
        }
        return new ResponseEntity(accountTransactionService.saveTransaction(accountTransactionRequest), HttpStatus.OK);
    }

}
