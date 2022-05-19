package com.example.model;

public class Block {
    byte[] blocks;
    static final int bitsAmount = 8;

    public Block() {
        blocks = new byte[8];
    }

    public Block(byte[] blocks) {
            this.blocks = blocks.clone();
    }

    public Block(int numberOfBytes) {
        blocks = new byte[numberOfBytes];
    }

    public boolean getBit(int position) {
        return (blocks[position / bitsAmount] & (1 << (bitsAmount - 1 - (position % bitsAmount)))) != 0;
    }

    public void setBit(int position, boolean bitValue) {
        if (bitValue)
            blocks[position / bitsAmount] |= (1 << (bitsAmount - 1 - (position % bitsAmount)));
        else
            blocks[position / bitsAmount] &= ~(1 << (bitsAmount - 1 - (position % bitsAmount)));
    }
}
