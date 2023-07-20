package com.ips.gateway.crm.service.onboarding;

import com.ips.gateway.crm.service.onboarding.dto.OnboardingEvent;

public interface OnboardingService {
    void createOpportunityOnboardedEventData(OnboardingEvent onboardingEvent);
}
