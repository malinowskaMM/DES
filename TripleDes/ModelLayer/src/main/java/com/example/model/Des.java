package com.example.model;

public class Des {
    static final int numberOfRounds = 16;

    final static int[] permutationPBlockTable = {
            16, 7, 20, 21,
            29, 12, 28, 17,
            1, 15, 23, 26,
            5, 18, 31, 10,
            2, 8, 24, 14,
            32, 27, 3, 9,
            19, 13, 30, 6,
            22, 11, 4, 25
    };
    static final int[] permutationInitialTable = {
            58, 50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6,
            64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17, 9, 1,
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7
    };
    static final int[] permutationFinalTable =
            {
                    40, 8, 48, 16, 56, 24, 64, 32,
                    39, 7, 47, 15, 55, 23, 63, 31,
                    38, 6, 46, 14, 54, 22, 62, 30,
                    37, 5, 45, 13, 53, 21, 61, 29,
                    36, 4, 44, 12, 52, 20, 60, 28,
                    35, 3, 43, 11, 51, 19, 59, 27,
                    34, 2, 42, 10, 50, 18, 58, 26,
                    33, 1, 41, 9, 49, 17, 57, 25
            };


    static final byte[][] sBoxes = {
            {
                    14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7,
                    0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8,
                    4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0,
                    15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13
            },
            {
                    15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10,
                    3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5,
                    0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15,
                    13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9
            },
            {
                    10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8,
                    13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1,
                    13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7,
                    1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12
            },
            {
                    7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15,
                    13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9,
                    10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4,
                    3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14
            },
            {
                    2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9,
                    14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6,
                    4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14,
                    11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3
            },
            {
                    12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11,
                    10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8,
                    9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6,
                    4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13
            },
            {
                    4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1,
                    13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6,
                    1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2,
                    6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12
            },
            {
                    13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7,
                    1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2,
                    7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8,
                    2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11
            }
    };
    final int[] permutationExpansionTable = {
            32, 1, 2, 3, 4, 5,
            4, 5, 6, 7, 8, 9,
            8, 9, 10, 11, 12, 13,
            12, 13, 14, 15, 16, 17,
            16, 17, 18, 19, 20, 21,
            20, 21, 22, 23, 24, 25,
            24, 25, 26, 27, 28, 29,
            28, 29, 30, 31, 32, 1
    };


    byte[] algorithmDes(byte[] text, Key key, boolean encryption) throws Exception {
       Block[] blocks = splitFileToBlocks(text);
       BlockHalf left = new BlockHalf();
       BlockHalf right = new BlockHalf();
       SubKey subKeys = new SubKey(16, key);
       Block48[] block48 = new Block48[16];
       if(!encryption) {
           Block48[] straight =  subKeys.subkeys.clone();
           for(int i = 0; i < 16; i++) {
               block48[i] = new Block48();
               block48[i].blocks = straight[numberOfRounds - 1 - i].blocks.clone();
           }
       } else {
           block48 =  subKeys.subkeys.clone();
       }

        for (int i = 0; i < blocks.length; i++)
        {
            initialPermutation(blocks[i]);
            for (int j = 0; j < 4; j++) {
                left.blocks[j] = blocks[i].blocks[j];
                right.blocks[j] = blocks[i].blocks[j + 4];
            }
            for(int j = 0; j < numberOfRounds; j++) {
                round(left, right, block48[j]);
            }
            mergeHalvesTo64BitBlock(right, left, blocks[i]);
            finalPermutation(blocks[i]);
        }
        return mergeBlocksToOutput(blocks);
    }

    String algorithmDes(String inputText, Key key, boolean encryption) throws Exception {
        byte [] inputBytes = StringByteConverter.StringToByte(inputText);
        byte [] outputBytes = algorithmDes(inputBytes,key,encryption);
        return StringByteConverter.BytesToString(outputBytes);
    }

    private void round(BlockHalf leftBlock32, BlockHalf rightBlock32, Block48 key)
    {
        BlockHalf copyRightBlock32 = new BlockHalf();
        copyRightBlock32.blocks = rightBlock32.blocks.clone();

        FFunction(rightBlock32, key);

        for (int i = 0; i < 4; i++)
            leftBlock32.blocks[i] ^= rightBlock32.blocks[i];

        rightBlock32.blocks = leftBlock32.blocks.clone();
        leftBlock32.blocks = copyRightBlock32.blocks.clone();
    }

    private void FFunction(BlockHalf block32, Block48 key48)
    {
        Block48 block48 = expandTo48Bits(block32);

        for (int i = 0; i < block48.blocks.length; i++)
            block48.blocks[i] ^= key48.blocks[i];

        Block8[] block6Table = split48BitBlockTo8GroupsPer6BitBlocks(block48);

        Block8[] block4Table = new Block8[8];
        for (int i = 0; i < block4Table.length; i++)
        {
            block4Table[i] = new Block8();
            block4Table[i].blocks[0] = substitution(i, block6Table[i]).blocks[0];
        }
        merge4BitBlocksInto32BitBlock(block4Table, block32);
        permutationP(block32);
    }

    private Block[] splitFileToBlocks(byte[] bytes) {
        Block[] blocks64 = new Block[(bytes.length + 8 - 1) / 8];
        int bytePosition = 0;
        for (int i = 0; i < blocks64.length; i++) {
            blocks64[i] = new Block();

            for (int j = 0; j < 8; j++)
                if (bytePosition < bytes.length)
                    blocks64[i].blocks[j] = bytes[bytePosition++];
                else
                    blocks64[i].blocks[j] = 0;
        }
        return blocks64;
    }

    private byte[] mergeBlocksToOutput(Block[] blocks64) {
        byte[] bytes = new byte[8 * blocks64.length];
        for (int i = 0; i < bytes.length; i++)
            bytes[i] = blocks64[i / 8].blocks[i % 8];

        return bytes;
    }


    private void mergeHalvesTo64BitBlock(BlockHalf blockLeftHalf, BlockHalf blockRightHalf, Block block) {
        for (int i = 0; i < 32; i++) {
            block.setBit(i, blockLeftHalf.getBit(i));
            block.setBit(i + 32, blockRightHalf.getBit(i));
        }
    }

    private Block8[] split48BitBlockTo8GroupsPer6BitBlocks(Block48 block) {
        Block8[] block6Table = new Block8[8];

        for (int i = 0; i < 48; i++) {
            block6Table[i / 6] = new Block8();
            block6Table[i / 6].setBit(i % 6, block.getBit(i));
        }

        return block6Table;
    }

    private void merge4BitBlocksInto32BitBlock(Block8[] block4Table, BlockHalf block) {
        for (int i = 0; i < block.blocks.length * Block.bitsAmount; i++)
            block.setBit(i, block4Table[i / 4].getBit(i % 4));
    }

    private void initialPermutation(Block block64) {
        Block temp = new Block();
        for (int i = 0; i < permutationInitialTable.length; i++) {
            temp.setBit(i, block64.getBit(permutationInitialTable[i] - 1));
        }
        block64.blocks = temp.blocks.clone();
    }

    private void finalPermutation(Block block64) {
        Block output = new Block();
        for (int i = 0; i < 64; i++)
            output.setBit(i, block64.getBit(permutationFinalTable[i] - 1));
        block64.blocks = output.blocks.clone();
    }

    private Block48 expandTo48Bits(BlockHalf block32)
    {
        Block48 block48 = new Block48();

        for (int i = 0; i < 48; i++)
            block48.setBit(i, block32.getBit(permutationExpansionTable[i] - 1));

        return block48;
    }

    private Block8 substitution(int n, Block8 input)
    {
        Block8 output = new Block8();
        int[] bits = new int[6];
        for(int i = 0; i < bits.length; i++) {
            int value = 0;
            if (input.getBit(i)) {
                value = 1;
            }
            bits[i] = value;
        }

        int row = 2 * bits[0] + bits[5];
        int col = 8 * bits[1] + 4 * bits[2] + 2 * bits[3] + bits[4];
        output.blocks[0] = (byte)(sBoxes[n][row * 16 + col] << 4);
        return output;
    }
    private BlockHalf permutationP(BlockHalf block32)
    {
        BlockHalf output = new BlockHalf();
        for (int i = 0; i < 32; i++) {
            output.setBit(i, block32.getBit(permutationPBlockTable[i] - 1));
        }

        for(int i = 0; i < 32; i++)
            block32.setBit(i, output.getBit(i));

        return output;
    }

}