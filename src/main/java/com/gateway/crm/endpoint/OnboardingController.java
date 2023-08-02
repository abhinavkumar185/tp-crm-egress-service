package com.gateway.crm.endpoint;

import com.gateway.crm.service.onboarding.OnboardingService;
import com.gateway.crm.service.onboarding.dto.OnboardingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/sync")
public class OnboardingController {
    private static final Logger logger = LoggerFactory.getLogger(OnboardingController.class);
    private OnboardingService onboardingService;

    @Autowired
    public OnboardingController(OnboardingService onboardingService) {
        this.onboardingService = onboardingService;
    }

    @PostMapping(path = "/v1/sync", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String process(@Valid @RequestBody OnboardingEvent event) {
        onboardingService.createOpportunityOnboardedEventData(event);
        return "Success";
    }

}
