package com.ips.gateway.crm.service.invitation;

import com.ips.gateway.crm.dao.invitation.entity.Invitation;

public interface InvitationService {
    Invitation findById(Integer invitationId, Integer syncId);
}
