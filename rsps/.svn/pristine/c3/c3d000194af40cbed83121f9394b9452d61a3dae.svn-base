package com.izhuixin.rsps.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class VerifyKey {
    private static PublicKey publicKey=null;

    static{
        Resource resource = new ClassPathResource("public.crt");

        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) cf.generateCertificate(resource.getInputStream());
            publicKey = cert.getPublicKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static PublicKey getPublicKey(){
        return publicKey;
    }
}
