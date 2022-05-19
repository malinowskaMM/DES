package com.example.model;

public class Des {
    //Stałe dane do szyfrowania/deszyfrowania
    static final int numberOfRounds = 16;
    static final int[] numberOfKeyShiftsPerRound = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};
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
    static final int[] permutationParityDropTable =
            {
                    57, 49, 41, 33, 25, 17, 9, 1,
                    58, 50, 42, 34, 26, 18, 10, 2,
                    59, 51, 43, 35, 27, 19, 11, 3,
                    60, 52, 44, 36, 63, 55, 47, 39,
                    31, 23, 15, 7, 62, 54, 46, 38,
                    30, 22, 14, 6, 61, 53, 45, 37,
                    29, 21, 13, 5, 28, 20, 12, 4
            };
    static final int[] permutationTableKeyCompression =
            {
                    14, 17, 11, 24, 1, 5, 3, 28,
                    15, 6, 21, 10, 23, 19, 12, 4,
                    26, 8, 16, 7, 27, 20, 13, 2,
                    41, 52, 31, 37, 47, 55, 30, 40,
                    51, 45, 33, 48, 44, 49, 39, 56,
                    34, 53, 46, 42, 50, 36, 29, 32
            };
    static final byte[][] substitutionBoxesTable = {
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



    byte[] algorithmBase(byte[] text, Key key, boolean encryption) throws Exception {
       Block[] blocks64 = divideBlocks64Bits(text);
       BlockHalf block32Left = new BlockHalf();
       BlockHalf block32Right = new BlockHalf();
       Block48[] block48 = generateRoundKeys(key);
        for (int i = 0; i < blocks64.length; i++)
        {
            initialPermutation(blocks64[i]); // Wywołanie permutacji początkowa
            divide64BitBlockIntoHalves(blocks64[i], block32Left, block32Right); // Podział bloku na dwie części

            for(int j = 0; j < numberOfRounds; j++)
                performRound(block32Left, block32Right, block48[(encryption ? j : (numberOfRounds - 1 - j))]);//Wykonanie rundy funkcji Feistela

            merge32BitBlockHalvesInto64BitBlock(block32Right, block32Left, blocks64[i]); // Połącznie podzielonych bloków
            finalPermutation(blocks64[i]); // Końcowa permutacja
        }

        return merge64BitBlocks(blocks64);

    }

    String algorithmBase(String inputText, Key key, boolean encryption) throws Exception {
        byte [] inputBytes = StringByteConverter.StringToByte(inputText);
        byte [] outputBytes = algorithmBase(inputBytes,key,encryption);
        return StringByteConverter.BytesToString(outputBytes);
    }
    //Generowanie poszczególnych klczy funkcji Feistela
    private Block48[] generateRoundKeys(Key key) throws Exception
    {
        Block48[] generatedKeys = new Block48[numberOfRounds];

        Block56 key56 = dropParityBits(new Block(key.getBytes()));

        for (int i = 0; i < numberOfRounds; i++)
        {
            for (int j = 0; j < numberOfKeyShiftsPerRound[i]; j++)
                rotateBitsLeft(key56);

            generatedKeys[i] = compressKey(key56);
        }

        return generatedKeys;
    }
    //Zmaina bitów podkluczy
    void rotateBitsLeft(Block56 block)
    {
        BlockHalf blockLeft28 = new BlockHalf();
        BlockHalf blockRight28 = new BlockHalf();
        divide56BitBlockIntoHalves(block, blockLeft28, blockRight28);

        moveBitsLeft28(blockLeft28);
        moveBitsLeft28(blockRight28);

        merge28BitBlockHalvesInto56BitBlock(blockLeft28, blockRight28, block);
    }

    private void performRound(BlockHalf leftBlock32, BlockHalf rightBlock32, Block48 key)
    {
        BlockHalf copyRightBlock32 = new BlockHalf();
        copyRightBlock32.blocks = rightBlock32.blocks.clone();

        roundFunction(rightBlock32, key);

        for (int i = 0; i < 4; i++)
            leftBlock32.blocks[i] ^= rightBlock32.blocks[i];

        rightBlock32.blocks = leftBlock32.blocks.clone();
        leftBlock32.blocks = copyRightBlock32.blocks.clone();
    }
    //Rozszerzanie 32 bitów do 48 bitów
    private void roundFunction(BlockHalf block32, Block48 key48)
    {
        Block48 block48 = expandTo48Bits(block32);

        for (int i = 0; i < block48.blocks.length; i++)
            block48.blocks[i] ^= key48.blocks[i];

        Block8[] block6Table = divide48BitBlockInto6BitBlocks(block48);

        Block8[] block4Table = new Block8[8];
        for (int i = 0; i < block4Table.length; i++)
        {
            block4Table[i] = new Block8();
            block4Table[i].blocks[0] = performSubstitution(i, block6Table[i]).blocks[0];
        }

        merge4BitBlocksInto32BitBlock(block4Table, block32);

        permutationP(block32);
    }

    //Podział Bloków na części 64 bitowe
    private Block[] divideBlocks64Bits(byte[] bytes) {
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

    //Złączenie bloków
    private byte[] merge64BitBlocks(Block[] blocks64) {
        byte[] bytes = new byte[8 * blocks64.length];

        for (int i = 0; i < bytes.length; i++)
            bytes[i] = blocks64[i / 8].blocks[i % 8];

        return bytes;
    }

    // Podział bloku 64 bitowego na dwa 32 bitowe
    private void divide64BitBlockIntoHalves(Block block64, BlockHalf block32Left, BlockHalf block32Right) {
        for (int i = 0; i < 4; i++) {
            block32Left.blocks[i] = block64.blocks[i];
            block32Right.blocks[i] = block64.blocks[i + 4];
        }
    }

    // W drugą stronę
    private void merge32BitBlockHalvesInto64BitBlock(BlockHalf blockLeftHalf, BlockHalf blockRightHalf, Block block) {
        for (int i = 0; i < 32; i++) {
            block.setBit(i, blockLeftHalf.getBit(i));
            block.setBit(i + 32, blockRightHalf.getBit(i));
        }
    }

    //Podział bloku 56 bitowego na dwie cześci
    private void divide56BitBlockIntoHalves(Block56 block56, BlockHalf blockLeft28, BlockHalf blockRight28) {
        for (int i = 0; i < 28; i++) {
            blockLeft28.setBit(i, block56.getBit(i));
            blockRight28.setBit(i, block56.getBit(i + 28));
        }
    }

    //W drugą stronę
    private void merge28BitBlockHalvesInto56BitBlock(BlockHalf blockLeftHalf, BlockHalf blockRightHalf, Block56 block) {
        for (int i = 0; i < 28; i++) {
            block.setBit(i, blockLeftHalf.getBit(i));
            block.setBit(i + 28, blockRightHalf.getBit(i));
        }
    }

    // Podział bloku 48 bitowego
    private Block8[] divide48BitBlockInto6BitBlocks(Block48 block) {
        Block8[] block6Table = new Block8[8];

        for (int i = 0; i < 48; i++) {
            block6Table[i / 6] = new Block8();
            block6Table[i / 6].setBit(i % 6, block.getBit(i));
        }

        return block6Table;
    }

    // Złączenie małych bloków w blok 32 bitowy
    private void merge4BitBlocksInto32BitBlock(Block8[] block4Table, BlockHalf block) {
        for (int i = 0; i < block.blocks.length * Block.bitsAmount; i++)
            block.setBit(i, block4Table[i / 4].getBit(i % 4));
    }

    //Permutacje
    //Początkowa permutacja zgodna z permutationInitialTable
    private void initialPermutation(Block block64) {
        Block temp = new Block();
        for (int i = 0; i < permutationInitialTable.length; i++) {
            temp.setBit(i, block64.getBit(permutationInitialTable[i] - 1));
        }
        block64.blocks = temp.blocks.clone();
    }

    //Końcowa permutacja zgodna z permutationFinalTable
    private void finalPermutation(Block block64) {
        Block output = new Block();
        for (int i = 0; i < 64; i++)
            output.setBit(i, block64.getBit(permutationFinalTable[i] - 1));
        block64.blocks = output.blocks.clone();
    }

    //
    private Block56 dropParityBits(Block block64) {
        Block56 block56 = new Block56();

        for (int i = 0; i < permutationParityDropTable.length; i++)
            block56.setBit(i, block64.getBit(permutationParityDropTable[i] - 1));

        return block56;
    }

    //Ucięcie klucza do 48bitów zgodznie z permutationTableKeyCompression
    private Block48 compressKey(Block56 block56) {
        Block48 block48 = new Block48();

        for (int i = 0; i < 48; i++)
            block48.setBit(i, block56.getBit(permutationTableKeyCompression[i] - 1));

        return block48;
    }

    //Rozszeranie klucza 32 bitowego do 48 bitowego
    private Block48 expandTo48Bits(BlockHalf block32)
    {
        Block48 block48 = new Block48();

        for (int i = 0; i < 48; i++)
            block48.setBit(i, block32.getBit(permutationExpansionTable[i] - 1));

        return block48;
    }
    //Przestawianie bitów w cześciach
    private void moveBitsLeft28(BlockHalf block28)
    {
        boolean orphanBit = block28.getBit(0);

        for (int i = 0; i < 27; i++)
            block28.setBit(i, block28.getBit(i + 1));

        block28.setBit(27, orphanBit);
    }
    //Podmiana bitów
    private Block8 performSubstitution(int n, Block8 input)
    {
        Block8 output = new Block8();

        int[] inputBits = new int[6];
        for(int i = 0; i < inputBits.length; i++)
            inputBits[i] = (input.getBit(i) ? 1 : 0);

        int rowNumber = 2 * inputBits[0] + inputBits[5];
        int columnNumber = 8 * inputBits[1] + 4 * inputBits[2] + 2 * inputBits[3] + inputBits[4];

        output.blocks[0] = (byte)(substitutionBoxesTable[n][rowNumber * 16 + columnNumber] << 4);

        return output;
    }
    private BlockHalf permutationP(BlockHalf block32)
    {
        BlockHalf output = new BlockHalf();

        final byte[] permutationPBlockTable = {
                16, 7, 20, 21,
                29, 12, 28, 17,
                1, 15, 23, 26,
                5, 18, 31, 10,
                2, 8, 24, 14,
                32, 27, 3, 9,
                19, 13, 30, 6,
                22, 11, 4, 25
        };

        for (int i = 0; i < 32; i++)
            output.setBit(i, block32.getBit(permutationPBlockTable[i] - 1));

        for(int i = 0; i < 32; i++)
            block32.setBit(i, output.getBit(i));

        return output;
    }

}

