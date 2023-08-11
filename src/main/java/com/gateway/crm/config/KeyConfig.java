package com.gateway.crm.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Slf4j
@Configuration
public class KeyConfig {

    @Autowired
    private Environment env;

    @Bean
    @Primary
    public RSAPrivateKey readPrivateKey() throws IOException, NoSuchAlgorithmException, KeyStoreException,
            CertificateException, UnrecoverableKeyException {
        KeyStore keystore = KeyStore.getInstance("PKCS12");
        String password = env.getProperty("keyStorePassword");
        keystore.load(new FileInputStream(env.getProperty("keyStorePath")), password.toCharArray());
        return (RSAPrivateKey)keystore.getKey(env.getProperty("keyStoreAlias").toUpperCase(), password.toCharArray());
    }

    @Bean
    @Primary
    public RSAPublicKey readPublicKey() throws IOException, CertificateException {
        FileInputStream fin = new FileInputStream(env.getProperty("axisPublicCertificate"));
        CertificateFactory f = CertificateFactory.getInstance("X.509");
        X509Certificate certificate = (X509Certificate)f.generateCertificate(fin);
        return (RSAPublicKey)certificate.getPublicKey();
    }
}
