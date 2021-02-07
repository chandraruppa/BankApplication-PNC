package com.pnc.account.pncbank.dto;

import com.pnc.account.pncbank.entity.AccountTransactionEntity;
import lombok.Data;

@Data
public class AccountTransactionResponse {

    private AccountTransactionEntity accountTransactionEntity;
    private boolean isSuccess;
    private String error;

    public AccountTransactionResponse(AccountTransactionEntity accountTransactionEntity, boolean isSuccess, String error) {
        this.accountTransactionEntity = accountTransactionEntity;
        this.isSuccess = isSuccess;
        this.error = error;
    }
}
