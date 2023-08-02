package com.gateway.crm.service.onboarding;


import com.gateway.crm.service.onboarding.dto.OnboardingEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class OnboardingEventListener implements ApplicationListener<OnboardingEvent> {
    @Autowired
    private OnboardingService onboardingService;

    @Override
    public void onApplicationEvent(OnboardingEvent event){
        //DO the async processing here
        log.info("OB event listener -> {}", event);
        if(event.getActionType().equalsIgnoreCase("c")){
            onboardingService.createOpportunityOnboardedEventData(event);
        }

        if(event.getActionType().equalsIgnoreCase("u")){
            //TODO: update events on KYC update should be called
        }
    }
}
