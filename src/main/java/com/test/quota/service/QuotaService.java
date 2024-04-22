package com.test.quota.service;

import com.test.quota.bean.Account;

public interface QuotaService {
    Account initializeAccount(String accountName, double initialQuota, String quotaType, String description);
    boolean increaseQuota(int accountId, double amount);
    boolean decreaseQuota(int accountId, double amount);
    Account getAccountInfo(int accountId);
    double queryQuota(int accountId,String quotaType);
}
