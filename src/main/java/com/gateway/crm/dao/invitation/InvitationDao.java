package com.gateway.crm.dao.invitation;

import com.gateway.crm.dao.invitation.entity.Invitation;

public interface InvitationDao {
    Invitation findById(Integer invitationId, Integer syncId);
}
