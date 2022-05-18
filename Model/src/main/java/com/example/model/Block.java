package com.example.model;

public class Block {

    byte[] block;

    static final int numberOfBitsInByte = 8;
    public Block() {
        block = new byte[8];
    }

    public Block(byte[] bytes) {
        if(bytes.length == 8) {
            block = bytes.clone();
        }
    }
    Block(int numberOfBytes)
    {
        block = new byte[numberOfBytes];

    }

    public byte[] getBlock() {
        return block;
    }

    public boolean getBit(int position)
    {
        return (block[position / numberOfBitsInByte] & (1 << (numberOfBitsInByte - 1 - (position % numberOfBitsInByte)))) != 0;
    }

    public void setBit(int position, boolean bitValue)
    {
        if (bitValue)
            block[position / numberOfBitsInByte] |= (1 << (numberOfBitsInByte - 1 - (position % numberOfBitsInByte)));
        else
            block[position / numberOfBitsInByte] &= ~(1 << (numberOfBitsInByte - 1 - (position % numberOfBitsInByte)));
    }
}

