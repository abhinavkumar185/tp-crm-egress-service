package com.ips.gateway.crm.dao.opportunitysynclog.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class OpportunitySyncLog {
    private Integer id;
    private Integer opportunityId;
    private Integer invitationId;
    private Integer activeStatusId;
    private Integer batchEntryStatusId;
}
