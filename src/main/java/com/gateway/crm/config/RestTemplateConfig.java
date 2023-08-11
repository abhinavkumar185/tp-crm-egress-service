package com.gateway.crm.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.security.KeyStore;

@Slf4j
@Configuration
public class RestTemplateConfig {

    @Autowired
    private Environment env;

    @Bean
    @Primary
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Add request logging if debugging is enabled
//        if (log.isDebugEnabled()) {
//            return builder
//                    .requestFactory(() -> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
//                    .additionalInterceptors(new RestTemplateRequestLoggingInterceptor())
//                    .build();
//        }
        try{
            KeyStore clientStore = KeyStore.getInstance("PKCS12");
            clientStore.load(new FileInputStream(env.getProperty("keyStorePath")), 
                    env.getProperty("keyStorePassword").toCharArray());

            SSLContext sslContext = SSLContextBuilder.create()
                    .loadKeyMaterial(clientStore, env.getProperty("keyStorePassword").toCharArray())
//                .setProtocol(properties.getProtocol())
                    .loadTrustMaterial(new TrustSelfSignedStrategy())
                    .build();
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext);
            CloseableHttpClient httpClient = HttpClients.custom()
                    .setSSLSocketFactory(sslConnectionSocketFactory)
                    .build();

            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

            return builder
                    .requestFactory(() -> requestFactory)
                    .additionalInterceptors(new RestTemplateRequestLoggingInterceptor())
                    .build();
//            return new RestTemplate(requestFactory);
        }
        catch (Exception e){
            log.error("Error building the rest template ", e);
            return builder.build();
        }
    }
}
