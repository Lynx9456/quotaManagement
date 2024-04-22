package com.test.quota.service.impl;

import com.test.quota.bean.QuotaRecords;
import com.test.quota.enums.OperatorEnum;
import com.test.quota.enums.TransactionTypeEnum;
import com.test.quota.exception.ConcurrencyException;
import org.apache.log4j.Logger;
import com.test.quota.bean.Account;
import com.test.quota.dao.AccountMapper;
import com.test.quota.dao.QuotaRecordsMapper;
import com.test.quota.service.QuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class QuotaServiceImpl implements QuotaService {

    private final static Logger logger= Logger.getLogger(QuotaServiceImpl.class);

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private QuotaRecordsMapper quotaRecordsMapper;

    @Override
    @Transactional
    public Account initializeAccount(String accountName, double initialQuota, String quotaType, String description) {
        Account account = new Account();
        account.setAccountName(accountName);
        account.setQuota(initialQuota);
        account.setQuotaType(quotaType);
        account.setDesc(description);
        account.setGmtCreate(new Date());
        account.setGmtModified(new Date());
        int id = accountMapper.insert(account);
        account.setId(id);
        return account;
    }

    @Override
    @Transactional
    public boolean increaseQuota(int accountId, double amount) {
        // Check account exists
        Account account = accountMapper.findById(accountId);
        if (account==null){
            logger.info("Account not found with id={} " + accountId);
            return false;
        }
        // Update quota
        Double newQuota = account.getQuota()+amount;
        account.setQuota(newQuota);
        account.setGmtModified(new Date());
        int updateRows = accountMapper.update(account);
        if (updateRows==0){
            throw new ConcurrencyException("Updated failed due to concurrent modification for accountId: " + accountId);
        }
        createQuotaRecord(account, TransactionTypeEnum.DEPOSIT.getCode(), amount, newQuota);
        return true;
    }

    @Override
    @Transactional
    public boolean decreaseQuota(int accountId, double amount) {
        Account account = accountMapper.findById(accountId);
        if (account==null){
            logger.info("Account not found with id={} " + accountId);
            return false;
        }
        Double newQuota = account.getQuota()-amount;
        if (newQuota < 0) {
            throw new IllegalArgumentException("Insufficient quota to withdraw.");
        }
        account.setQuota(newQuota);
        account.setGmtModified(new Date());
        accountMapper.update(account);
        createQuotaRecord(account, TransactionTypeEnum.WITHDRAW.getCode(), amount, newQuota);
        return true;
    }

    @Override
    public Account getAccountInfo(int accountId) {
        return accountMapper.findById(accountId);
    }

    @Override
    public double queryQuota(int accountId,String quotaType) {
        return getAccountInfo(accountId).getQuota();
    }

    private int createQuotaRecord(Account account, String transactionType, double amount, double quotaAfter) {
        QuotaRecords quotaRecord = new QuotaRecords();
        quotaRecord.setAccountId(account.getId());
        quotaRecord.setTransactionType(transactionType);
        quotaRecord.setAmount(amount);
        quotaRecord.setQuotaAfter(quotaAfter);
        quotaRecord.setQuotaType(account.getQuotaType());
        quotaRecord.setGmtCreate(new Date());
        quotaRecord.setGmtModified(new Date());
        quotaRecord.setOperator(OperatorEnum.SYSTEM.getCode());
        quotaRecord.setDesc("some important info"+String.valueOf(System.currentTimeMillis()));
        return quotaRecordsMapper.insert(quotaRecord);
    }
}
