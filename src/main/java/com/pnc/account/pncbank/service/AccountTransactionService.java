package com.pnc.account.pncbank.service;

import com.pnc.account.pncbank.constants.PncBankConstants;
import com.pnc.account.pncbank.dto.AccountFilterRequest;
import com.pnc.account.pncbank.dto.AccountTransactionRequest;
import com.pnc.account.pncbank.dto.AccountTransactionResponse;
import com.pnc.account.pncbank.entity.AccountBalanceEntity;
import com.pnc.account.pncbank.entity.AccountTransactionEntity;
import com.pnc.account.pncbank.repository.AccountBalanceRepository;
import com.pnc.account.pncbank.repository.AccountTransactionRepository;
import com.pnc.account.pncbank.util.AccountBalanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AccountTransactionService {

    @Autowired
    private AccountTransactionRepository accountTransactionRepository;
    @Autowired
    private AccountBalanceRepository accountBalanceRepository;

    public AccountTransactionResponse saveTransaction(AccountTransactionRequest accountTransactionRequest){
        AccountTransactionResponse accountTransactionResponse = null;
        AccountTransactionEntity accountTransactionEntity = null;
        try {
            AccountBalanceEntity accountBalanceEntity = accountBalanceRepository.findByAccountNumber(accountTransactionRequest.getAccountNumber());

            if (null != accountBalanceEntity && null != accountTransactionRequest && PncBankConstants.TRANSACTION_TYPE_WITHDRAW.equalsIgnoreCase(accountTransactionRequest.getTransactionType())
                    && accountBalanceEntity.getBalance() >= accountTransactionRequest.getTransactionAmount()){
                accountTransactionEntity = createAccountTransactionEntity(accountTransactionRequest);
                accountTransactionRepository.save(accountTransactionEntity);
                AccountBalanceEntity accountBalanceEntity1 = createAccountBalanceEntity(accountTransactionRequest, accountBalanceEntity);
                accountBalanceRepository.save(accountBalanceEntity1);
                return new AccountTransactionResponse(accountTransactionEntity, Boolean.TRUE, "");
            } else if (null != accountBalanceEntity && null != accountTransactionRequest && PncBankConstants.TRANSACTION_TYPE_DEPOSIT.equalsIgnoreCase(accountTransactionRequest.getTransactionType())){
                accountTransactionEntity = createAccountTransactionEntity(accountTransactionRequest);
                accountTransactionRepository.save(accountTransactionEntity);
                AccountBalanceEntity accountBalanceEntity1 = createAccountBalanceEntity(accountTransactionRequest, accountBalanceEntity);
                accountBalanceRepository.save(accountBalanceEntity1);
                return new AccountTransactionResponse(accountTransactionEntity, Boolean.TRUE, "");
            } else {
                return new AccountTransactionResponse(accountTransactionEntity, Boolean.FALSE, "AccountNumber doesn't exist in DB");
            }

        } catch(Exception exception){
            return new AccountTransactionResponse(accountTransactionEntity, Boolean.FALSE, exception.getMessage());
        }
    }

    private AccountBalanceEntity createAccountBalanceEntity(AccountTransactionRequest accountTransactionRequest, AccountBalanceEntity accountBalanceEntity){
        accountBalanceEntity.setAccountNumber(accountTransactionRequest.getAccountNumber());
        if (PncBankConstants.TRANSACTION_TYPE_WITHDRAW.equalsIgnoreCase(accountTransactionRequest.getTransactionType())){
            accountBalanceEntity.setBalance(accountBalanceEntity.getBalance() - accountTransactionRequest.getTransactionAmount());
        } else if (PncBankConstants.TRANSACTION_TYPE_DEPOSIT.equalsIgnoreCase(accountTransactionRequest.getTransactionType())){
            accountBalanceEntity.setBalance(accountBalanceEntity.getBalance() + accountTransactionRequest.getTransactionAmount());
        }
        accountBalanceEntity.setCreatedDate(new Date());
        accountBalanceEntity.setCreatedBy(PncBankConstants.ADMIN_USER);
        return accountBalanceEntity;
    }

    private AccountTransactionEntity createAccountTransactionEntity(AccountTransactionRequest accountTransactionRequest){
        AccountTransactionEntity accountTransactionEntity = new AccountTransactionEntity();
        accountTransactionEntity.setAccountNumber(accountTransactionRequest.getAccountNumber());
        accountTransactionEntity.setTransactionType(accountTransactionRequest.getTransactionType());
        accountTransactionEntity.setTransactionAmount(accountTransactionRequest.getTransactionAmount());
        accountTransactionEntity.setCreatedBy(PncBankConstants.ADMIN_USER);
        accountTransactionEntity.setCreatedTimestamp(new Date());
        return accountTransactionEntity;
    }

    public List<AccountTransactionEntity> getTransactionListByDate(AccountFilterRequest accountFilterRequest){
        List<AccountTransactionEntity> accountTransactionEntityList = new ArrayList<>();
        try {
            return accountTransactionRepository.findByAccountNumberAndCreatedTimestamp(accountFilterRequest.getAccountNumber(), AccountBalanceUtil.convertStartDate(accountFilterRequest.getStartDate()), AccountBalanceUtil.convertEndDate(accountFilterRequest.getEndDate()));
        } catch(Exception exception){
            exception.printStackTrace();
            System.out.println(" Exception occurred during processing getTransactionListByDate method and exception is :"+exception.getMessage());
            return accountTransactionEntityList;
        }
    }


    public List<AccountTransactionEntity> getTransactionListByType(AccountFilterRequest accountFilterRequest){
        List<AccountTransactionEntity> accountTransactionEntityList = new ArrayList<>();
        try {
            return accountTransactionRepository.findByAccountNumberAndTransactionType(accountFilterRequest.getAccountNumber(), accountFilterRequest.getTransactionType());
        } catch(Exception exception){
            exception.printStackTrace();
            System.out.println(" Exception occurred during processing getTransactionListByType method and exception is :"+exception.getMessage());
            return accountTransactionEntityList;
        }
    }
}
