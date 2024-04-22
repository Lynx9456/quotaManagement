package com.test.quota.enums;


public enum TransactionTypeEnum {

    DEPOSIT("DEPOSIT","存钱"),
    WITHDRAW("WITHDRAW","取钱");

    private String code;
    private String name;

    TransactionTypeEnum(String code,String name){
        this.code=code;
        this.name=name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static TransactionTypeEnum getByCode(String code){
        for (TransactionTypeEnum value : TransactionTypeEnum.values()) {
            if (value.code.equals(code)){
                return value;
            }
        }
        return null;
    }
}
