package com.test.quota.enums;

public enum OperatorEnum {
    SYSTEM("SYSTEM","系统"),
    PERSON("PERSON","人为");

    private String code;
    private String name;

    OperatorEnum(String code, String name){
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
