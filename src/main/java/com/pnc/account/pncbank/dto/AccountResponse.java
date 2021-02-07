package com.pnc.account.pncbank.dto;

import com.pnc.account.pncbank.entity.AccountBalanceEntity;
import lombok.Data;

@Data
public class AccountResponse {

    private AccountBalanceEntity accountBalanceEntity;
    private boolean isSuccess;
    private String errorMessage;

    public AccountResponse(AccountBalanceEntity accountBalanceEntity, boolean isSuccess, String errorMessage) {
        this.accountBalanceEntity = accountBalanceEntity;
        this.isSuccess = isSuccess;
        this.errorMessage = errorMessage;
    }
}
