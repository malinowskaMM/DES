package com.example.model;

public class SubKey {
    public Block48[] subkeys;
    private Key key;
    public SubKey(int subKeysCounter, Key inKey) throws Exception {
        subkeys = new Block48[subKeysCounter];
        key = inKey;
        subkeys = generateRoundKeys(inKey);
    }

    static final int numberOfRounds = 16;
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
    static final int[] numberOfKeyShiftsPerRound = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

    private Block56 dropParityBits(Block block64) {
        Block56 block56 = new Block56();

        for (int i = 0; i < permutationParityDropTable.length; i++)
            block56.setBit(i, block64.getBit(permutationParityDropTable[i] - 1));

        return block56;
    }

    void rotateBitsLeft(Block56 block)
    {
        BlockHalf blockLeft28 = new BlockHalf();
        BlockHalf blockRight28 = new BlockHalf();
        for (int i = 0; i < 28; i++) {
            blockLeft28.setBit(i, block.getBit(i));
            blockRight28.setBit(i, block.getBit(i + 28));
        }

        moveBitsLeft28(blockLeft28);
        moveBitsLeft28(blockRight28);

        for (int i = 0; i < 28; i++) {
            block.setBit(i, blockLeft28.getBit(i));
            block.setBit(i + 28, blockRight28.getBit(i));
        }

    }
    private void moveBitsLeft28(BlockHalf block28)
    {
        boolean orphanBit = block28.getBit(0);

        for (int i = 0; i < 27; i++)
            block28.setBit(i, block28.getBit(i + 1));

        block28.setBit(27, orphanBit);
    }

    private Block48 compressKey(Block56 block56) {
        Block48 block48 = new Block48();

        for (int i = 0; i < 48; i++)
            block48.setBit(i, block56.getBit(permutationTableKeyCompression[i] - 1));

        return block48;
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
}
