package com.example.model;

public class StringByteConverter {
    static byte [] StringToByte(String text) //konwersja Stringów na Bajty
    {
        byte[] bytes = new byte[text.length()<<1];
        int position = 0;
        for(char character: text.toCharArray())
        {
            bytes[position++] = (byte) ((character & 0xFF00) >> 8);
            bytes[position++] = (byte) (character & 0x00FF);
        }
        return bytes;
    }
    static String BytesToString(byte[] bytes) { // Konwersja Bajtów na Stringi
        char[] buffer = new char[bytes.length >>1];
        for (int i = 0; i < buffer.length; i++) {
            int bpos = i << 1;
            char c = (char) (((bytes[bpos] & 0x00FF) << 8) + (bytes[bpos + 1] & 0x00FF));
            buffer[i] = c;
        }
        return new String(buffer);
    }
}
