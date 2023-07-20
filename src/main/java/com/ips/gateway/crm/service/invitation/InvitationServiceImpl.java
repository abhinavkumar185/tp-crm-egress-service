package com.ips.gateway.crm.service.invitation;

import com.ips.gateway.crm.dao.invitation.InvitationDao;
import com.ips.gateway.crm.dao.invitation.entity.Invitation;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class InvitationServiceImpl implements InvitationService{
    
    private final InvitationDao invitationDao;
    public InvitationServiceImpl(InvitationDao invitationDao) {
        this.invitationDao = invitationDao;
    }
    
    @Override
    public Invitation findById(Integer invitationId, Integer syncId){
        return invitationDao.findById(invitationId , syncId);
    }
}
