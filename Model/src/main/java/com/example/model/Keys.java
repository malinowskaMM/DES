package com.example.model;

public class Keys {
    public int[] keyLeftShiftTable = {
            1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1
    };

    public int[] PC1KeyPermutationTable = {
            57, 49, 41, 33, 25, 17,  9,
            1,  58, 50, 42, 34, 26, 18,
            10,  2, 59, 51, 43, 35, 27,
            19, 11,  3, 60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,
            7,  62, 54, 46, 38, 30, 22,
            14,  6, 61, 53, 45, 37, 29,
            21, 13, 5, 28, 20, 12,  4
    };

    public int[] PC2KeyPermutationTable = {
            14, 17, 11, 24,  1,  5,
            3,  28, 15,  6, 21, 10,
            23, 19, 12,  4, 26,  8,
            16,  7, 27, 20, 13,  2,
            41, 52, 31, 37, 47, 55,
            30, 40, 51, 45, 33, 48,
            44, 49, 39, 56, 34, 53,
            46, 42, 50, 36, 29, 32
    };

    BitOperations bo = new BitOperations();

    private BlockHalf moveLeft(BlockHalf block)
    {
        for (int i = 0; i < 27; i++)
            {
                block.setBit(i, block.getBit(i + 1));
            }
        return block;
    }

    public Block48[] generate16SubKeys(Block key) {
        Block56 block56 = (Block56) BitOperations.permutation(key, PC1KeyPermutationTable, 56);
        BlockHalf blockL = new BlockHalf();
        BlockHalf blockR = new BlockHalf();
        //nie jestem pewna czy zera dopisywac na koncu czy na poczatku zeby z 28 zrobic 32 bity
        for(int i = 0; i < 32; i++) {
            if(i < 28) {
                blockL.setBit(i,block56.getBit(i));
                blockR.setBit(i,block56.getBit(i+28));
            }/* else {
                blockL.setBit(i, false);
                blockR.setBit(i, false);
            }*/
        }

        BlockHalf[] leftBlocks = new BlockHalf[16];
        BlockHalf[] rightBlocks = new BlockHalf[16];

        for(int i = 0; i < 16; i++) {
            BlockHalf tempBlockL = blockL;
            BlockHalf tempBlockR = blockR;
            for(int j = 0; j < keyLeftShiftTable[i]; j++) {
                tempBlockL = moveLeft(tempBlockL);
                tempBlockR = moveLeft(tempBlockR);
            }
            leftBlocks[i] = tempBlockL;
            rightBlocks[i] = tempBlockR;
        }

        Block56[] subkeys = new Block56[16];
        for(int i = 0; i < 16; i++) {
            Block56 subkey = new Block56();
            for(int j = 0; j < 28; j++) {
                    subkey.setBit(j, leftBlocks[i].getBit(j));
                    subkey.setBit(j + 28, rightBlocks[i].getBit(j));
            }
            subkeys[i] = subkey;
        }

        Block48[] finalSubkeys = new Block48[16];
        for(int i = 0; i < 16; i++) {
            Block48 finalSubkey = (Block48) BitOperations.permutation(subkeys[i],PC2KeyPermutationTable,48);
            finalSubkeys[i] = finalSubkey;
        }
        return finalSubkeys;
    }


}
