package com.test.quota.enums;


public enum QuotaTypeEnum {

    CASH("CASH","现金"),
    POINT("POINT","积分");

    private String code;
    private String name;

    QuotaTypeEnum(String code, String name){
        this.code=code;
        this.name=name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static QuotaTypeEnum getByCode(String code){
        for (QuotaTypeEnum value : QuotaTypeEnum.values()) {
            if (value.getCode().equals(code)){
                return value;
            }
        }
        return null;
    }
}
