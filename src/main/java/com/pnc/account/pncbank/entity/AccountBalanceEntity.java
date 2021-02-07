package com.pnc.account.pncbank.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "ACCOUNT_DETAILS")
@Entity
public class AccountBalanceEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private Integer accountNumber;
    private Double balance;
    private String createdBy;
    private Date createdDate;

}
