package com.gateway.crm.dao.kyc.opportunitykyc.entity;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class OpportunityKyc {

	private Integer id;
	private Integer opportunityId;
	private String documentNumber;
	private Integer cpdtId;
	private Integer kycStatusId;
	private Integer createdBy;
	private Integer channelId;
	private Integer activeStatusId;
	private JsonNode signzy_response;
	private JsonNode merchantResponse;
	private JsonNode signzyVerifyResponse;

}
