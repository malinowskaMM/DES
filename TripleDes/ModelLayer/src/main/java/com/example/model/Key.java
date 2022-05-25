package com.example.model;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;

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

    public String getKeyText() {
        return StringByteConverter.BytesToString(bytes);
    }

    public byte[] getBytes() {
        return bytes.clone();
    }


    private void generateRandomKey() {
        Random rng = new Random();
        this.bytes = new byte[8];
        rng.nextBytes(this.bytes);

    }

}
