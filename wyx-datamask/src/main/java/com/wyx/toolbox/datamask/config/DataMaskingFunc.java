package com.wyx.toolbox.datamask.config;



/**
 * @author wangyu
 */
public enum DataMaskingFunc {

    /**
     *  脱敏转换器
     */
    NO_MASK((str, maskChar) -> str),
    ALL_MASK((str, maskChar) -> {
        if (null != str && str.length() > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                sb.append(null != maskChar && maskChar.length() > 0 ? maskChar : DataMaskingOperation.MASK_CHAR);
            }
            return sb.toString();
        }
        return str;
    }),

    USERNAME(MyDesensitizedUtil::name),

    ID_CARD(MyDesensitizedUtil::idCardNum),

    PHONE(MyDesensitizedUtil::mobilePhone),

    ADDRESS(MyDesensitizedUtil::address),

    EMAIL(MyDesensitizedUtil::email),

    BANK_CARD(MyDesensitizedUtil::bankCard),

    ;

    private final DataMaskingOperation operation;

    DataMaskingFunc(DataMaskingOperation operation) {
        this.operation = operation;
    }

    public DataMaskingOperation operation() {
        return this.operation;
    }

}
