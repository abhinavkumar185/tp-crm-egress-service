package com.gateway.crm.dao.quickcash.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder(toBuilder = true)
public class QuickCashApplicationDetail {

    private Integer loanStagesId;
    private String borrowerFirstName;
    private String borrowerLastName;
    private String fatherFirstName;
    private String fatherLastName;
    private String mobileNumber;
    private String pan;
    private String aadhaar;
    private String gender;
    private String consent;
    private String residenceType;
    private String residenceAge;
    private BigDecimal amount;
    private Integer loanTenure;
    private String loanApplicationId;
    private String loanBorrowerId;
    private Date dateOfBirth;
    private String currentAdressLine1;
    private String currentAdressLine2;
    private String currentCity;
    private String currentPostalZip;
    private String currentState;
    private Integer currentStateProvienceId;
    private Integer currentCityId;
    private Integer currentCountryId;
    private Integer currentAddressTypeId;
    private String permanentAdressLine1;
    private String permanentAdressLine2;
    private String permanentCity;
    private String permanentPostalZip;
    private String permanentState;
    private Integer permanentStateProvienceId;
    private Integer permanentCityId;
    private Integer permanentCountryId;
    private Integer permanentAddressTypeId;
    private Integer loanProviderId;
    private String reason;
    private String bankName;
    private String ifsc;


}
