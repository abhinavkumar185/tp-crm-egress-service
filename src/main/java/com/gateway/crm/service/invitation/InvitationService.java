package com.gateway.crm.service.invitation;

import com.gateway.crm.dao.invitation.entity.Invitation;

public interface InvitationService {
    Invitation findById(Integer invitationId, Integer syncId);
}
