package com.pnc.account.pncbank.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class AccountTransactionRequest {

    @NotNull(message = "accountNumber should not be null")
    @Min(value = 100000 , message = "accountNumber should be six digit number")
    @Max(value = 999999, message = "accountNumber should be six digit number")
    private Integer accountNumber;
    private String transactionType;
    private Double transactionAmount;

}
