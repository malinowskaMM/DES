import com.example.model.Block;
import com.example.model.Block8;
import com.example.model.DES;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.io.File;

public class DESTest {
    @Test
    public void Block8ToInt2() {
        DES des = new DES();

        Block8 b = new Block8();
        b.setBit(0,true);
        b.setBit(1,true);
        Assert.assertEquals(des.from8BlockToInt(b,2),3);
    }
    @Test
    public void Block8ToInt4() {
        DES des = new DES();

        Block8 b = new Block8();
        b.setBit(0,true);
        b.setBit(1,true);
        Assert.assertEquals(des.from8BlockToInt(b,4),12);
    }

    @Test
    public void Int4ToBlock8() {
        DES des = new DES();

        int j = 15;

        Block8 b = new Block8();
        b.setBit(0,true);
        b.setBit(1,true);
        b.setBit(2,true);
        b.setBit(3,true);

        Block8 k = des.fromIntTo4Block(j);

        for(int i =0; i < 8; i++) {
            Assert.assertEquals(b.getBit(i),k.getBit(i));
        }
    }

    @Test
    public void Int4ToBlock8_2() {
        DES des = new DES();

        int j = 8;

        Block8 b = new Block8();
        b.setBit(0,true);
        b.setBit(1,false);
        b.setBit(2,false);
        b.setBit(3,false);

        Block8 k = des.fromIntTo4Block(j);

        for(int i =0; i < 8; i++) {
            Assert.assertEquals(b.getBit(i),k.getBit(i));
        }
    }

    @Test
    public void ReadValueFRomSbox() {
        DES des = new DES();
        int[][] sbox = des.SBoxes[0];
        Assert.assertEquals(sbox[1][10], 12);
    }
}
