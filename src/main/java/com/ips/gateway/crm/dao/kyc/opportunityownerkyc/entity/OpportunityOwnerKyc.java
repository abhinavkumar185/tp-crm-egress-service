package com.ips.gateway.crm.dao.kyc.opportunityownerkyc.entity;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class OpportunityOwnerKyc {
	
	private Integer opportunityId;
	private String documentNumber;
	private Integer documentTypeId;
	private String fileName;
	private Integer psdsId;
	private Integer uploadedBy;
	private Integer cpdtId;
	private Integer kycStatusId;
	private Integer activeStatusId;
	private Integer createdById;
	private Integer channelId;
	private JsonNode signzy_response;
	private JsonNode merchantResponse;
	private JsonNode signzyVerifyResponse;
	
	
}
