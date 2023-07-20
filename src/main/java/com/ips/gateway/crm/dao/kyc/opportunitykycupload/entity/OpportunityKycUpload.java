package com.ips.gateway.crm.dao.kyc.opportunitykycupload.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class OpportunityKycUpload {

	private Integer opportunityId;
	private Integer opportunityKycId;
	private Integer cpdtId;
	private String fileName;
	private Integer uploadedById;
	private Integer activeStatusId;
	private Integer channelId;
	

}
