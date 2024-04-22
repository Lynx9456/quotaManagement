package com.test.quota.request;

public class AccountRequest {
    private String accountName;
    private double initialQuota;
    private String quotaType;
    private String description;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getInitialQuota() {
        return initialQuota;
    }

    public void setInitialQuota(double initialQuota) {
        this.initialQuota = initialQuota;
    }

    public String getQuotaType() {
        return quotaType;
    }

    public void setQuotaType(String quotaType) {
        this.quotaType = quotaType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
