package com.ips.gateway.crm.dao.invitation;

import com.ips.gateway.crm.dao.invitation.entity.Invitation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InvitationDaoImpl implements   InvitationDao{
    @Autowired
    private InvitationRepository invitationRepository;

    @Override
    public Invitation findById(Integer invitationId, Integer syncId) {
        return invitationRepository.findById(invitationId, syncId);
    }
}
