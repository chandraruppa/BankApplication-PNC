package com.pnc.account.pncbank.repository;

import com.pnc.account.pncbank.entity.AccountTransactionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AccountTransactionRepository extends CrudRepository<AccountTransactionEntity, Long> {

    @Query(value = "select a from AccountTransactionEntity a where a.accountNumber = :accountNumber AND a.createdTimestamp BETWEEN :startDate AND :endDate")
     List<AccountTransactionEntity> findByAccountNumberAndCreatedTimestamp(@Param("accountNumber") Integer accountNumber,@Param("startDate") Date startDate,@Param("endDate") Date endDate);

    @Query(value = "select a from AccountTransactionEntity a where a.accountNumber = :accountNumber AND a.transactionType = :transactionType ")
    List<AccountTransactionEntity> findByAccountNumberAndTransactionType(@Param("accountNumber") Integer accountNumber,@Param("transactionType") String transactionType);

}
