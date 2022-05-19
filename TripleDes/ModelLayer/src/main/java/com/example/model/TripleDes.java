package com.example.model;

public class TripleDes {
    public byte[] encrypt(byte[] plainText, Key[] key) throws Exception {
        return algorithmTripleDes(plainText, key, true);
    }

    public byte[] decrypt(byte[] cipherText, Key[] key) throws Exception {
        return algorithmTripleDes(cipherText, key, false);
    }

    public String encrypt(String plainText, Key[] key) throws Exception {
        return algorithmTripleDes(plainText, key, true);
    }

    public String decrypt(String cipherText, Key[] key) throws Exception {
        return algorithmTripleDes(cipherText, key, false);
    }
    private static final Des des = new Des();

    private byte[] algorithmTripleDes(byte[] input, Key[] key, boolean encryption) throws Exception {
        byte[] output;
        output = des.algorithmDes(input, key[0], encryption);
        output = des.algorithmDes(output, key[1], !encryption);
        output = des.algorithmDes(output, key[2], encryption);

        return output;
    }
    private String algorithmTripleDes(String input, Key[] key, boolean encryption) throws Exception {
        byte[] inputBytes = StringByteConverter.StringToByte(input);
        byte[] outputBytes = algorithmTripleDes(inputBytes, key, encryption);
        return StringByteConverter.BytesToString(outputBytes);
    }
}
