package com.test.quota.bean;

import com.baomidou.mybatisplus.annotation.Version;

import java.util.Date;

public class Account {

    private int id;
    private Date gmtCreate;

    private Date gmtModified;

    private Integer version;
    private String accountName;
    private Double quota;
    //@See QuotaTypeEnum
    private String  quotaType;
    private String desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Double getQuota() {
        return quota;
    }

    public void setQuota(Double quota) {
        this.quota = quota;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getQuotaType() {
        return quotaType;
    }

    public void setQuotaType(String quotaType) {
        this.quotaType = quotaType;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
