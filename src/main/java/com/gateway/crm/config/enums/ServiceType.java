package com.gateway.crm.config.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.gateway.crm.config.LowercaseEnum;

public enum ServiceType implements LowercaseEnum {
    TOKEN_INQUIRY("OPENAPI","/gateway/api/v2/payvantage/user-registration-inquiry"),
    PAYMENT_STATUS("API.PV.PAYMT.STATUS","/gateway/api/v2/payvantage/rib/dv-payment-inquiry"),
    VALIDATE_IFSC("API.PV.VALIDATE.IFSC","/gateway/api/v2/payvantage/rib/validate-ifsc-code"),
    REQUEST_CHEQUE("API.PV.CHEQUE.BOOK.REQ","/gateway/api/v2/payvantage/rib/cheque-book-request"),
    CHEQUE_INQUIRY("API.PV.CHEQUE.BOOK.INQRY","/gateway/api/v2/payvantage/rib/cheque-book-inquiry"),
    DELETE_BENEFICIARY("API.PV.DELETE.BENE","/gateway/api/v2/payvantage/rib/delete-beneficiary"),
    GET_BENEFICIARY("API.PV.FETCH.BENE","/gateway/api/v2_1/payvantage/rib/fetch-beneficiary"),
    ADD_BENEFICIARY("API.PV.ADD.BENE","/gateway/api/v2/payvantage/rib/add-beneficiary"),
    ACCOUNT_STATEMENT("API.PV.ACC.STMT","/gateway/api/v2/payvantage/rib/get-account-statements"),
    MINI_STATEMENT("API.PV.MINI.STMT","/gateway/api/v2/payvantage/rib/mini-statement"),
    STOP_CHEQUE_PAYMENT("OPENAPI","/gateway/api/v2/payvantage/rib/stop-cheque-instruction"),
    CHEQUE_DELIVERY_STATUS("OPENAPI","/gateway/api/v2/payvantage/rib/cheque-book-delivery-status"),
    INTERNAL_TRANSFER("API.PV.PAYMT","/gateway/api/v2/payvantage/rib/feba/fund-transfer"),
    IMPS_TRANSFER("API.PV.PAYMT","/gateway/api/v2/payvantage/rib/feba/imps-payment"),
    RTGS_TRANSFER("API.PV.PAYMT","/gateway/api/v2/payvantage/rib/feba/rtgs-payment"),
    NEFT_TRANSFER("API.PV.PAYMT","/gateway/api/v2/payvantage/rib/feba/neft-payment"),
    STANDING_INSTRUCTION("API.PV.PAYMT","/gateway/api/v2/payvantage/rib/feba/create-payment-standing-instruction"),
    BALANCE_INQUIRY("API.PV.BAL.INQRY","/gateway/api/v2/payvantage/rib/balance-inquiry");

    private String displayName;
    private String endPoint;

    ServiceType(String displayName, String endPoint) {
        this.displayName = displayName;
        this.endPoint = endPoint;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEndPoint() {
        return endPoint;
    }

    @JsonCreator
    public static ServiceType deserialize(String name) {
        return LowercaseEnum.deserialize(ServiceType.class, name);
    }

    public static ServiceType of(String name) {
        return LowercaseEnum.forValue(ServiceType.class, name);
    }
}