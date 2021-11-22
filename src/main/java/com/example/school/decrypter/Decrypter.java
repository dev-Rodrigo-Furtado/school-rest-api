package com.example.school.decrypter;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Decrypter {

    private Decrypter() {}

    public static String decrypt(String cryptedText) {
        cryptedText = cryptedText.replaceAll("ENC|[()]", "");
        String password = System.getProperty("password");
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(password);
        encryptor.setAlgorithm(System.getProperty("algorithm"));
        return encryptor.decrypt(cryptedText);
    }
}
