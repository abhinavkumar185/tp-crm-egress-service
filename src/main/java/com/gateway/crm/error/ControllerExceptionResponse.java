package com.gateway.crm.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Value
@Builder
@AllArgsConstructor
@JsonDeserialize(builder = ControllerExceptionResponse.ControllerExceptionResponseBuilder.class)
public class ControllerExceptionResponse {
    private static final String UNKNOWN_OPERATION = null;
    private static final int UNKNOWN_CODE = -1;

    @JsonProperty("timestamp")
    private Date timestamp;

    @JsonProperty("httpStatus")
    private int status;

    @JsonProperty("statusMessage")
    private String statusMessage;

    @JsonProperty("message")
    private String message;

    @JsonProperty("code")
    private int code;

    @JsonProperty("operation")
    private String operation;

    @JsonProperty("correlationId")
    private String correlationId;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ControllerExceptionResponseBuilder {
        // Lombok will add constructor, setters, build method
    }

    public static ControllerExceptionResponse buildResponse(int status, String message, String correlationId){
        return ControllerExceptionResponse.builder()
                .timestamp(new Date())
                .status(status)
                .statusMessage(message)
                .message(message)
                .operation("")
                .code(0)
                .correlationId(correlationId)
                .build();
    }

    static ControllerExceptionResponse buildResponse(String correlationId, Throwable throwable) {
        ControllerExceptionResponse exceptionResponse ;
        if(throwable instanceof ServiceException){
            ServiceException exception = (ServiceException) throwable;
            exceptionResponse = ControllerExceptionResponse
                .builder()
                    .timestamp(new Date())
                    .status(exception.getError().getHttpStatus())
                    .statusMessage(exception.getError().getHttpStatusDescription())
                    .message(exception.getError().getDescription())
                    .operation(exception.getOperation())
                    .code(exception.getError().getCode())
                    .correlationId(correlationId)
                .build();
        }
        else{
            exceptionResponse = ControllerExceptionResponse
                .builder()
                    .timestamp(new Date())
                    .status( HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .statusMessage("INTERNAL_SERVER_ERROR")
                    .message(throwable.getMessage())
                    .operation("")
                    .code(9999)
                    .correlationId(correlationId)
                .build();
        }
        return exceptionResponse;
    }

}