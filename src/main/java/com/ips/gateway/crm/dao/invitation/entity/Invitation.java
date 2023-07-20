package com.ips.gateway.crm.dao.invitation.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Invitation {
    private Integer syncId;
    private Integer id;
    private String businessName;
    private String isdCode;
    private String mobileNumber;
    private String phoneNumber;
    private Integer countryId;
    private String email;
    private String firstName;
    private String lastName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private Integer stateProvince;
    private String postalZip;
    private Integer roleNestingId;
    private String operatorUid;
    private String passcode;
    private String clientId;
}