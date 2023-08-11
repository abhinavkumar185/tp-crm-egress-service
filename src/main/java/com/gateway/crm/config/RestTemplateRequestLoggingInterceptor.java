package com.gateway.crm.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j
public class RestTemplateRequestLoggingInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
//        logResponse(response);

        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) throws IOException {
        log.info(
                "============================ HTTP Request Begin =============================\n" +
                        "URI         : " + request.getURI() + "\n" +
                        "Method      : " + request.getMethod() + "\n" +
                        "Headers     : " + request.getHeaders() + "\n" +
                        "Request body: " + new String(body, "UTF-8") + "\n" +
                        "============================= HTTP Request End =============================="
        );
    }

    private void logResponse(ClientHttpResponse response) throws IOException {
        log.info(
                "============================ HTTP Response Begin ============================\n" +
                        "Status code  : " + response.getStatusCode() + "\n" +
                        "Status text  : " + response.getStatusText() + "\n" +
                        "Headers      : " + response.getHeaders() + "\n" +
                        "Response body: " + StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()) + "\n" +
                        "============================= HTTP Response End ============================="
        );
    }
}