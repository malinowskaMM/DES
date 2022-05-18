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
}
