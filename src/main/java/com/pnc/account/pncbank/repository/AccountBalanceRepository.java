package com.pnc.account.pncbank.repository;

import com.pnc.account.pncbank.entity.AccountBalanceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountBalanceRepository extends CrudRepository<AccountBalanceEntity, Long> {

    AccountBalanceEntity findByAccountNumber(Integer accountNumber);
}
