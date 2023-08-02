package com.gateway.crm.dao.bankaccount.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder(toBuilder = true)
public class BankAccount {
    private Integer id;
    private String createdBy;
    private Integer productId;
    private Integer bankAccountId;
    private Integer bankId;
    private String accountNumber;
    private String accountHolderName;
    private String ifsc;
    private Integer opportunityId;

}
