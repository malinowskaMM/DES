package com.example.model;

public class DES {
    public int[][][] SBoxes = {
                   {{14,  4, 13,  1,  2, 15, 11,  8,  3, 10,  6, 12,  5,  9,  0,  7},
                    {0, 15,  7,  3, 14,  2, 13,  1, 10,  6, 12, 11,  9,  5,  3,  8},
                    {4,  1, 14,  8, 13,  6,  2, 11, 15, 12,  9,  7, 13, 10,  5,  0},
                    {15, 12,  8,  2,  4,  9,  1,  7,  5, 11,  3, 14, 10,  0,  6, 13}},

                   {{15,  1,  8, 14,  6, 11,  3,  4,  9,  7,  2, 13, 12,  0,  5, 10},
                           {3, 13,  4,  7, 15,  2,  8, 14, 12,  0,  1, 10,  6,  9, 11,  5},
                                   {0, 14,  7, 11, 10,  4, 13,  1,  5,  8, 12,  6,  9,  3,  2, 15},
                                           {13,  8, 10,  1,  3, 15,  4,  2, 11,  6,  7, 12,  0,  5, 14,  9}},

                   {{10,  0,  9, 14,  6,  3, 15,  5,  1, 13, 12,  7, 11,  4,  2,  8 },
                           {13,  7,  0,  9,  3,  4,  6, 10,  2,  8,  5, 14, 12, 11, 15,  1},
                                   {13,  6,  4,  9,  8, 15,  3,  0, 11,  1,  2, 12,  5, 10, 14,  7},
                                           {1, 10, 13,  0,  6,  9,  8,  7,  4, 15, 14,  3, 11,  5,  2, 12}},

                  { {7, 13, 14,  3,  0,  6,  9, 10,  1,  2,  8,  5, 11, 12,  4, 15},
                          {13,  8, 11,  5,  6, 15,  0,  3,  4,  7,  2, 12,  1, 10, 14,  9},
                                  {10,  6,  9,  0, 12, 11,  7, 13, 15,  1,  3, 14,  5,  2,  8,  4},
                                          {3, 15,  0,  6, 10,  1, 13,  8,  9,  4,  5, 11, 12,  7,  2, 14}},

                  { {2, 12,  4,  1,  7, 10, 11,  6,  8,  5,  3, 15, 13,  0, 14,  9},
                          {14, 11,  2, 12,  4,  7, 13,  1,  5,  0, 15, 10,  3,  9,  8,  6},
                                  {4,  2,  1, 11, 10, 13,  7,  8, 15,  9, 12,  5,  6,  3,  0, 14},
                                          {11,  8, 12,  7,  1, 14,  2, 13,  6, 15,  0,  9, 10,  4,  5,  3}},

                   {{12,  1, 10, 15,  9,  2,  6,  8,  0, 13,  3,  4, 14,  7,  5, 11},
                           {10, 15,  4,  2,  7, 12,  9,  5,  6,  1, 13, 14,  0, 11,  3,  8},
                                   {9, 14, 15,  5,  2,  8, 12,  3,  7,  0,  4, 10,  1, 13, 11,  6},
                                           {4,  3,  2, 12,  9,  5, 15, 10, 11, 14,  1,  7,  6,  0,  8, 13}},

                   {{4, 11,  2, 14, 15,  0,  8, 13,  3, 12,  9,  7,  5, 10,  6,  1},
                           {13,  0, 11,  7,  4,  9,  1, 10, 14,  3,  5, 12,  2, 15,  8,  6},
                                   {1,  4, 11, 13, 12,  3,  7, 14, 10, 15,  6,  8,  0,  5,  9,  2},
                                           {6, 11, 13,  8,  1,  4, 10,  7,  9,  5,  0, 15, 14,  2,  3, 12}},

                   {{13,  2,  8,  4,  6, 15, 11,  1, 10,  9,  3, 14,  5,  0, 12,  7},
                           {1, 15, 13,  8, 10,  3,  7,  4, 12,  5,  6, 11,  0, 14,  9,  2},
                                   {7, 11,  4,  1,  9, 12, 14,  2,  0,  6, 10, 13, 15,  3,  5,  8},
                                           {2,  1, 14,  7,  4, 10,  8, 13, 15, 12,  9,  0,  3,  5,  6, 11}}
    };

    public int[] PPermutationTable = {
            16,  7, 20, 21,
            29, 12, 28, 17,
            1, 15, 23, 26,
            5, 18, 31, 10,
            2,  8, 24, 14,
            32, 27,  3,  9,
            19, 13, 30,  6,
            22, 11,  4, 25
    };

    public int[] initialPermutationTable = {
            58, 50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6,
            64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17, 9,  1,
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7
    };

    public int[] inversedInitialPermutationTable = {
            40,  8, 48, 16, 56, 24, 64, 32,
            39,  7, 47, 15, 55, 23, 63, 31,
            38,  6, 46, 14, 54, 22, 62, 30,
            37,  5, 45, 13, 53, 21, 61, 29,
            36,  4, 44, 12, 52, 20, 60, 28,
            35,  3, 43, 11, 51, 19, 59, 27,
            34,  2, 42, 10, 50, 18, 58, 26,
            33,  1, 41,  9, 49, 17, 57, 25
    };

    public int[] expansionPermutationTable = {
            32,  1,  2,  3,  4,  5,
            4,  5,  6,  7,  8,  9,
            8,  9, 10, 11, 12, 13,
            12, 13, 14, 15, 16, 17,
            16, 17, 18, 19, 20, 21,
            20, 21, 22, 23, 24, 25,
            24, 25, 26, 27, 28, 29,
            28, 29, 30, 31, 32,  1
    };

    public Block48 expansionPermutation(BlockHalf blockHalf) {
        return (Block48) BitOperations.permutation(blockHalf, expansionPermutationTable, 48);
    }
    public BlockHalf permutation(BlockHalf blockHalf) {
        return (BlockHalf) BitOperations.permutation(blockHalf, PPermutationTable , 32);
    }

    public Block48 xorWithKey(Block48 block48, Block48 key) {
        Block48 xor = new Block48();
        for(int i = 0; i < 48; i++) {
            if(block48.getBit(i) != key.getBit(i)) {
                xor.setBit(i,true);
            }
        }
        return xor;
    }

    public BlockHalf xorLeftRight(BlockHalf left, BlockHalf right) {
        BlockHalf xor = new BlockHalf();
        for(int i = 0; i < 32; i++) {
            if(left.getBit(i) != left.getBit(i)) {
                xor.setBit(i,true);
            }
        }
        return xor;
    }

    public int from8BlockToInt(Block8 block8, int size){ //4-i
        int result = 0;
        size = size -1;
        for(int i = size; i > -1; i--) {
            if (block8.getBit(i)){
                result += Math.pow(2, size-i);
            }
        }
        return result;
    }

    public Block8 fromIntTo4Block(int value) {
        Block8 result = new Block8();
        for(int i = 3; i > -1; i--) {
            boolean bool;
            if(value % 2 == 0) {
                bool = false;
            } else {
                bool = true;
            }
            result.setBit(i,bool);
            value = value/2;
        }
        return result;
    }

    public BlockHalf substitution(Block48 block48){
        Block8[] blocks =  new Block8[8];
        for(int i =0; i < 8; i++) {
            for(int j =0; j < 6; j++) {
                blocks[i].setBit(j, block48.getBit(i*8+j));
            }
        }
        int[][] coordinates =  new int[8][2];
        int iter = 0;
        for (Block8 block:blocks) {
            Block8 rowBlock = new Block8();
            Block8 colBlock = new Block8();

            rowBlock.setBit(0,block.getBit(0));
            rowBlock.setBit(1,block.getBit(5));

            int row = from8BlockToInt(rowBlock, 2);

            colBlock.setBit(0,block.getBit(1));
            colBlock.setBit(1,block.getBit(2));
            colBlock.setBit(2,block.getBit(3));
            colBlock.setBit(3,block.getBit(4));

            int col = from8BlockToInt(colBlock, 4);

            coordinates[iter][0] = row;
            coordinates[iter][1] = col;

            iter++;
        }

        BlockHalf results = new BlockHalf();

        for(int i = 0; i < 8; i++ ) {
            int[][] sbox = SBoxes[i];
            int value = sbox[coordinates[i][0]] [coordinates[i][1]];
            Block8 block8 = fromIntTo4Block(value);
            for(int j = 0; j < 4; j++) {
                results.setBit(i*8+j, block8.getBit(j));
            }
        }
        return results;
    }

    public BlockHalf FFunction(BlockHalf blockHalf, Block48 key) {
        Block48 afterExpansion = expansionPermutation(blockHalf);
        Block48 afterXorWithKey = xorWithKey(afterExpansion, key);
        BlockHalf afterSBoxes = substitution(afterXorWithKey);
        BlockHalf afterPermutation = permutation(afterSBoxes);
        return afterPermutation;
    }


    public BlockHalf OneRound(BlockHalf left, BlockHalf right, Block48 key) {
        BlockHalf newRight = FFunction(right, key);
        BlockHalf result = xorLeftRight(left, newRight);
        return result;
    }

    public Block OneBlock(Block block, Block48[] key){
        block = BitOperations.permutation(block, initialPermutationTable, 64);

        BlockHalf left = new BlockHalf();
        BlockHalf right = new BlockHalf();

        for(int i = 0; i < 32; i ++) {
            left.setBit(i,block.getBit(i));
            right.setBit(i,block.getBit(i+32));
        }

        for(int i = 0; i < 16; i++) {
            BlockHalf newRight = OneRound(left, right, key[i]);
            left = right;
            right = newRight;
        }

        Block roundResult = new Block();
        for(int i = 0; i < 32; i++) {
            roundResult.setBit(i, left.getBit(i));
            roundResult.setBit(i+32, right.getBit(i));
        }

        return BitOperations.permutation(roundResult,inversedInitialPermutationTable, 64 );
    }




}
