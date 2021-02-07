package com.pnc.account.pncbank.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class AccountRequest {
    @NotNull(message = "accountNumber should not be null")
    @Min(value=100000, message= "accountNumber should be six digit number")
    @Max(value=999999, message= "accountNumber should be six digit number")
    private Integer accountNumber;
    private Double balance;

    public AccountRequest(@NotNull(message = "accountNumber should not be null") @Min(value = 100000, message = "accountNumber should be six characters length") @Max(value = 999999, message = "accountNumber should be six characters length") Integer accountNumber, Double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
}
