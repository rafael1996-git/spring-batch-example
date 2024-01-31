package com.eglobal.encryptpassword.encryptdecrypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.springframework.stereotype.Component;

@Component
public class EncryptDecrypt {

    StandardPBEStringEncryptor stringEncryptor;
    EnvironmentStringPBEConfig eConfig;

    public EncryptDecrypt() {
        stringEncryptor = new StandardPBEStringEncryptor();
        eConfig = new EnvironmentStringPBEConfig();

        eConfig.setAlgorithm("PBEWithSHA1AndDESede");
        eConfig.setKeyObtentionIterations(100000);
        eConfig.setPassword("Aspis2014");

        stringEncryptor.setConfig(eConfig);
    }
    
    public String encryptBase64(String texto) {
        return stringEncryptor.encrypt(texto);
    }
    
    public String decryptBase64(String texto) {
    	return stringEncryptor.decrypt(texto);
    }
}