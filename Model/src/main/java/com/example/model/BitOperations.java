package com.example.model;

import java.util.BitSet;

public class BitOperations {

    public static Block permutation(Block bits, int[] table, int resultBitsCounter) {
        Block block = switch (resultBitsCounter) {
            case 32 -> new BlockHalf();
            case 48 -> new Block48();
            case 56 -> new Block56();
            default -> new Block();
        };
        for(int i = 0; i < resultBitsCounter; i ++)
        {
            block.setBit(i, bits.getBit(table[i] - 1));
        }

        return block;
    }

    public static byte[][] splitInHalf(byte[] block, int size) {
        int splitIndex = size / 2;
        byte[] left = new byte[splitIndex];
        byte[] right = new byte[splitIndex];
        for(int i = 0; i < splitIndex; i++) {
            left[i]= block[i];
            right[i]=block[splitIndex+i];
        }
        return new byte[][] {left, right};
    }

    public static byte[] shiftLeft(byte[] b1, int bytes) {
        byte[] result = new byte[b1.length];
        System.arraycopy(b1, bytes, result, 0, bytes);
        return result;
    }
}
