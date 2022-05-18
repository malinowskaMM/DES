package com.example.model;

public class TripleDES {
    Keys keys;
    DES des;

    public byte[] encrypt(Block key, byte[] file) {
        keys = new Keys();
        des = new DES();
        Block48[] keysTable = keys.generate16SubKeys(key);
        return des.encrypted(file, keysTable);
    }

    public byte[] decrypt(Block key, byte[] file) {
        keys = new Keys();
        des = new DES();
        Block48[] keysTable = keys.generate16SubKeys(key);
        return des.decrypted(file, keysTable);
    }

}
