package com.ips.gateway.crm.dao.operator.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Operator {
    private Integer id;
    private Integer partnerId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String uid;
    private String mobileNumber;
}
