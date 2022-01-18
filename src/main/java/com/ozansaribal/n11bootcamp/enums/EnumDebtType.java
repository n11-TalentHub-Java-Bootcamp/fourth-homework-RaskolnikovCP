package com.ozansaribal.n11bootcamp.enums;

public enum EnumDebtType {

    LATE_FEE("LATE_FEE"),
    NORMAL("NORMAL")
    ;

    private String debtType;

    EnumDebtType(String debtType) {
        this.debtType = debtType;
    }

    public String getType() {
        return debtType;
    }

    @Override
    public String toString() {
        return debtType;
    }

}
