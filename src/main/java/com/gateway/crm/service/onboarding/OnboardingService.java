package com.gateway.crm.service.onboarding;

import com.gateway.crm.service.onboarding.dto.OnboardingEvent;

public interface OnboardingService {
    void createOpportunityOnboardedEventData(OnboardingEvent onboardingEvent);
}
