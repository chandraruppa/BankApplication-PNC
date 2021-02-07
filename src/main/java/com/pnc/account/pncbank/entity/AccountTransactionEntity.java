package com.pnc.account.pncbank.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name="ACCOUNT_TRANSACTION")
@Entity
public class AccountTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer accountNumber;
    private double transactionAmount;
    private String createdBy;
    private Date createdTimestamp;
    private String transactionType;

}
