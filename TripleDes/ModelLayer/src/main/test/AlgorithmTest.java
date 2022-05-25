
import static org.junit.jupiter.api.Assertions.*;

import com.example.model.Key;
import com.example.model.StringByteConverter;
import com.example.model.TripleDes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Random;

public class AlgorithmTest {

    public String stringGenerator(){
        byte[] array = new byte[16]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = StringByteConverter.BytesToString(array);
        return generatedString;
    }


    @Test
    public void stringEncDecTest() throws Exception {
        int stringLen = 10;
        int keyCount = 20;
        int stringCount = 20;

        for(int j = 0; j < keyCount; j++) {
            Key[] keys = new Key[3];
            keys[0] = new Key();
            keys[1] = new Key();
            keys[2] = new Key();
            TripleDes tripleDes = new TripleDes();

            for ( int k = 0; k < stringCount; k++) {
                Random random = new Random();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < stringLen; i++) {
                    sb.append((char) (random.nextInt(26) + 'a'));
                }
                String testedStr = sb.toString();
                String encrypted = tripleDes.encrypt(testedStr, keys);
                String decrypted = tripleDes.decrypt(encrypted, keys);
                assertEquals(decrypted, testedStr);
            }
        }
    }

    @Test
    public void testTest() throws Exception {
        for (int i = 0; i < 50; i++) {

            String s = stringGenerator();
            System.out.println(s);
            Key[] keys = new Key[3];
            keys[0] = new Key();
            keys[1] = new Key();
            keys[2] = new Key();
            TripleDes tripleDes = new TripleDes();

            String result = tripleDes.encrypt(s, keys);
            String result2 = tripleDes.decrypt(result, keys);

            Assertions.assertEquals(s, result2);
        }
    }

    @Test
    public void testFileTest() throws Exception {

        byte[] s = Files.readAllBytes(Path.of("D:\\DES\\TripleDes\\skryba.txt"));
        byte[] s2 = Files.readAllBytes(Path.of("D:\\DES\\TripleDes\\plik.txt"));
        System.out.println(Arrays.toString(s2));

        for (int i = 0; i < 50; i++) {
            Key[] keys = new Key[3];
            keys[0] = new Key();
            keys[1] = new Key();
            keys[2] = new Key();
            TripleDes tripleDes = new TripleDes();

            byte[] result = tripleDes.encrypt(s2, keys);
            byte[] result2 = tripleDes.decrypt(result, keys);
            System.out.println(Arrays.toString(result2));
            System.out.println(result2.length);
            System.out.println(i);


            Assertions.assertTrue(Arrays.equals(result2, s2));
        }
    }
}
