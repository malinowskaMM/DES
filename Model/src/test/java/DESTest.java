import org.junit.jupiter.api.Test;

import java.io.File;

public class DESTest {
    @Test
    public void DESTest() {
        File file = new File("ok.jpg");
        byte[] bytes = new byte[(int) file.length()];
        System.out.printf(String.valueOf(file.length()));


    }
}
