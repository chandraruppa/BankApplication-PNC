package com.pnc.account.pncbank.config;

import com.pnc.account.pncbank.dto.AccountRequest;
import com.pnc.account.pncbank.dto.AccountTransactionRequest;
import com.pnc.account.pncbank.service.AccountBalanceService;
import com.pnc.account.pncbank.service.AccountTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    @Autowired
    private AccountBalanceService accountBalanceService;

    @Autowired
    private AccountTransactionService accountTransactionService;

    @Scheduled(fixedDelay = 300000)
    private void setDemoData(){

        AccountRequest accountRequest1 = new AccountRequest(400001, 2000.00);
        accountBalanceService.createAccount(accountRequest1);

        AccountRequest accountRequest2 = new AccountRequest(400002, 3000.00);
        accountBalanceService.createAccount(accountRequest2);

        AccountRequest accountRequest3 = new AccountRequest(400003, 4000.00);
        accountBalanceService.createAccount(accountRequest3);

        AccountRequest accountRequest4 = new AccountRequest(400004, 5000.00);
        accountBalanceService.createAccount(accountRequest4);

        AccountRequest accountRequest5 = new AccountRequest(400005, 6000.00);
        accountBalanceService.createAccount(accountRequest5);


        AccountTransactionRequest accountTransactionRequest1 = new AccountTransactionRequest(400001, "DEPOSIT", 300.00);
        accountTransactionService.saveTransaction(accountTransactionRequest1);
        AccountTransactionRequest accountTransactionRequest2 = new AccountTransactionRequest(400001, "WITHDRAW", 100.00);
        accountTransactionService.saveTransaction(accountTransactionRequest2);
        AccountTransactionRequest accountTransactionRequest3 = new AccountTransactionRequest(400001, "DEPOSIT", 50.00);
        accountTransactionService.saveTransaction(accountTransactionRequest3);
        AccountTransactionRequest accountTransactionRequest4 = new AccountTransactionRequest(400001, "DEPOSIT", 300.00);
        accountTransactionService.saveTransaction(accountTransactionRequest4);
        AccountTransactionRequest accountTransactionRequest5 = new AccountTransactionRequest(400001, "WITHDRAW", 300.00);
        accountTransactionService.saveTransaction(accountTransactionRequest5);


        AccountTransactionRequest accountTransactionRequest6 = new AccountTransactionRequest(400002, "DEPOSIT", 100.00);
        accountTransactionService.saveTransaction(accountTransactionRequest6);
        AccountTransactionRequest accountTransactionRequest7 = new AccountTransactionRequest(400002, "WITHDRAW", 150.00);
        accountTransactionService.saveTransaction(accountTransactionRequest7);
        AccountTransactionRequest accountTransactionRequest8 = new AccountTransactionRequest(400002, "DEPOSIT", 50.00);
        accountTransactionService.saveTransaction(accountTransactionRequest8);
        AccountTransactionRequest accountTransactionRequest9 = new AccountTransactionRequest(400002, "DEPOSIT", 200.00);
        accountTransactionService.saveTransaction(accountTransactionRequest9);
        AccountTransactionRequest accountTransactionRequest10 = new AccountTransactionRequest(400002, "WITHDRAW", 100.00);
        accountTransactionService.saveTransaction(accountTransactionRequest10);



        AccountTransactionRequest accountTransactionRequest11 = new AccountTransactionRequest(400003, "DEPOSIT", 900.00);
        accountTransactionService.saveTransaction(accountTransactionRequest11);
        AccountTransactionRequest accountTransactionRequest12 = new AccountTransactionRequest(400003, "WITHDRAW", 100.00);
        accountTransactionService.saveTransaction(accountTransactionRequest12);
        AccountTransactionRequest accountTransactionRequest13 = new AccountTransactionRequest(400003, "DEPOSIT", 50.00);
        accountTransactionService.saveTransaction(accountTransactionRequest13);
        AccountTransactionRequest accountTransactionRequest14 = new AccountTransactionRequest(400003, "DEPOSIT", 400.00);
        accountTransactionService.saveTransaction(accountTransactionRequest14);
        AccountTransactionRequest accountTransactionRequest15 = new AccountTransactionRequest(400003, "WITHDRAW", 200.00);
        accountTransactionService.saveTransaction(accountTransactionRequest15);



        AccountTransactionRequest accountTransactionRequest16 = new AccountTransactionRequest(400004, "DEPOSIT", 300.00);
        accountTransactionService.saveTransaction(accountTransactionRequest16);
        AccountTransactionRequest accountTransactionRequest17 = new AccountTransactionRequest(400004, "DEPOSIT", 100.00);
        accountTransactionService.saveTransaction(accountTransactionRequest17);
        AccountTransactionRequest accountTransactionRequest18 = new AccountTransactionRequest(400004, "WITHDRAW", 50.00);
        accountTransactionService.saveTransaction(accountTransactionRequest18);
        AccountTransactionRequest accountTransactionRequest19 = new AccountTransactionRequest(400004, "DEPOSIT", 300.00);
        accountTransactionService.saveTransaction(accountTransactionRequest19);
        AccountTransactionRequest accountTransactionRequest20 = new AccountTransactionRequest(400004, "WITHDRAW", 300.00);
        accountTransactionService.saveTransaction(accountTransactionRequest20);
    }

}
