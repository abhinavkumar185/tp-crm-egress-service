package com.gateway.crm.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Optional;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Error {

    SESSION_EXPIRED(1, "Session Expired, please login", HttpStatus.UNAUTHORIZED.value(), Constants.UNAUTHORIZED),
    INVALID_TOKEN(2, "Invalid/compromised/missing token", HttpStatus.UNAUTHORIZED.value(), Constants.UNAUTHORIZED),
    BAD_REQUEST(3, "Invalid request data", HttpStatus.BAD_REQUEST.value(), Constants.BAD_REQUEST),
    UNAUTHORIZED_ACTION(4, "Action cannot be performed", HttpStatus.UNAUTHORIZED.value(), Constants.UNAUTHORIZED),
    LOG_NOT_FOUND(5, "log file not found", HttpStatus.PRECONDITION_FAILED.value(), Constants.PRECONDITION_FAILED),
    INVALID_CREDENTIALS(11, "Invalid Credentials", HttpStatus.FORBIDDEN.value(), Constants.UNAUTHORIZED),

    BANK_SESSION_EXPIRED(7000, "Bank Session expired, please re-authorize", HttpStatus.FORBIDDEN.value(), Constants.FORBIDDEN),
    INVALID_VERB(9995, "Invalid method type", HttpStatus.NOT_FOUND.value(), Constants.NOT_FOUND),
    INVALID_PERMISSION(9996, "Invalid permissions", HttpStatus.NOT_FOUND.value(), Constants.NOT_FOUND),
    REDIS_DATA(9997, "Redis data not found", HttpStatus.NOT_FOUND.value(), Constants.NOT_FOUND),
    BEARER_TOKEN_INVALID(9998, "Auth token invalid.", HttpStatus.UNAUTHORIZED.value(), Constants.UNAUTHORIZED),
    SERVER_ERROR(9999, "Request cannot be completed", HttpStatus.INTERNAL_SERVER_ERROR.value(), Constants.INTERNAL_SERVER_ERROR);

    private final int code;
    private final String description;
    private final String httpStatusDescription;
    private final int httpStatusCode;

    Error(int code, String description, int httpStatusCode, String httpStatusDescription) {
        this.code = code;
        this.description = description;
        this.httpStatusCode = httpStatusCode;
        this.httpStatusDescription = httpStatusDescription;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public int getHttpStatus() {
        return httpStatusCode;
    }

    /**
     * Default getter method for field : httpStatusDescription
     *
     * @return java.lang.String
     */
    public String getHttpStatusDescription() {
        return httpStatusDescription;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }

    @JsonCreator
    static Error findValue(@JsonProperty("code") int code, @JsonProperty("description") String description) {
        Optional<Error> errorOptional= Arrays.stream(values())
                .filter(error -> error.code == code && error.description.equals(description))
                .findFirst();
        return  errorOptional.isPresent()?errorOptional.get():null;
    }

    private static class Constants {
        public static final String NOT_FOUND = "NOT FOUND";
        public static final String UNAUTHORIZED = "UNAUTHORIZED";
        public static final String FORBIDDEN = "FORBIDDEN";
        public static final String BAD_REQUEST = "BAD_REQUEST";
        public static final String PRECONDITION_FAILED = "PRECONDITION_FAILED";
        public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
    }

}
