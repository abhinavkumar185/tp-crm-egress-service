package com.gateway.crm.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * Service exception handler
     */
    @ExceptionHandler(ServiceException.class)
    public final ResponseEntity<Object> handleServiceException(ServiceException ex, WebRequest request) {
        log.error("REST Service exception", ex);
        String requestId = request.getHeader("correlation-id");
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type","application/json");
        return new ResponseEntity<>(ControllerExceptionResponse.buildResponse(requestId,ex),
                headers,ex.getError().getHttpStatus());
    }
}
