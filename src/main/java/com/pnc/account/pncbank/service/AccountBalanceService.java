package com.pnc.account.pncbank.service;

import com.pnc.account.pncbank.dto.AccountRequest;
import com.pnc.account.pncbank.dto.AccountResponse;
import com.pnc.account.pncbank.entity.AccountBalanceEntity;
import com.pnc.account.pncbank.repository.AccountBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AccountBalanceService {

    @Autowired
    private AccountBalanceRepository accountBalanceRepository;

    public Object getAccountBalance(AccountRequest accountRequest){

        AccountBalanceEntity accountBalanceEntity = accountBalanceRepository.findByAccountNumber(accountRequest.getAccountNumber());
        if (accountBalanceEntity != null){
            return accountBalanceEntity.getBalance();
        }
        return "AccountNumber doesn't existed in DB";
    }

    public AccountResponse createAccount(AccountRequest accountRequest) {
        AccountResponse accountResponse = null;
        try {
            AccountBalanceEntity accountBalanceEntity = accountBalanceRepository.findByAccountNumber(accountRequest.getAccountNumber());
            if (null == accountBalanceEntity) {
                AccountBalanceEntity accountEntity = createAccountBalanceEntity(accountRequest);
                accountBalanceRepository.save(accountEntity);
                accountResponse = new AccountResponse(accountEntity, Boolean.TRUE, "");
            } else {
                accountResponse = new AccountResponse(null, Boolean.FALSE, "AccountNumber is already Existed");
            }
        } catch (Exception exception){
            accountResponse = new AccountResponse(null, Boolean.FALSE, exception.getMessage());
        }
        return accountResponse;
    }

    private AccountBalanceEntity createAccountBalanceEntity(AccountRequest accountRequest) {
        AccountBalanceEntity accountBalanceEntity = new AccountBalanceEntity();
        accountBalanceEntity.setAccountNumber(accountRequest.getAccountNumber());
        accountBalanceEntity.setBalance(accountRequest.getBalance());
        accountBalanceEntity.setCreatedBy("AdminUser");
        accountBalanceEntity.setCreatedDate(new Date());
        return accountBalanceEntity;
    }

    public List<AccountBalanceEntity> getAccountList(){
        List<AccountBalanceEntity> accountBalanceEntitiesList = new ArrayList<>();
        try{
         accountBalanceRepository.findAll().forEach(accountBalanceEntities -> accountBalanceEntitiesList.add(accountBalanceEntities));
        } catch (Exception exception){
            exception.printStackTrace();
            System.out.println(" Exception occurred during processing getAccountList method and Exception is :"+exception.getMessage());
        }
        return accountBalanceEntitiesList;
    }

}
