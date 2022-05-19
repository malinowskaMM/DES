package com.example.model;

import java.security.SecureRandom;

public class Key {
    private byte[] bytes;

    public Key() {
        generateRandomKey();
    }

    public Key(byte[] bytes) throws Exception {
        if ( bytes.length == 8 ){
            this.bytes = bytes.clone();
        }
    }

    public Key(String text) throws Exception {
        byte[] temp = StringByteConverter.StringToByte(text);
            this.bytes = temp.clone();

    }

    // Odczytanie tekstu z klucza
    public String getKeyText() {
        return StringByteConverter.BytesToString(bytes);
    }

    public byte[] getBytes() {
        return bytes.clone();
    }


    //Generowanie klucza
    private void generateRandomKey() {
        SecureRandom rng = new SecureRandom();
        this.bytes = new byte[8];
        rng.nextBytes(this.bytes);
    }

}
