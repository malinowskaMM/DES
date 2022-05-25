
import static org.junit.jupiter.api.Assertions.*;

import com.example.model.Key;
import com.example.model.StringByteConverter;
import com.example.model.TripleDes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class AlgorithmTest {


    @Test
    public void testTest() throws Exception {

        String s = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam eaque ipsa, quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt, explicabo. Nemo enim ipsam voluptatem, quia voluptas sit, aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos, qui ratione voluptatem sequi nesciunt, neque porro quisquam est, qui dolorem ipsum, quia dolor sit, amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt, ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit, qui in ea voluptate velit esse, quam nihil molestiae consequatur, vel illum, qui dolorem eum fugiat, quo voluptas nulla pariatur? [33] At vero eos et accusamus et iusto odio dignissimos ducimus, qui blanditiis praesentium voluptatum deleniti atque corrupti, quos dolores et quas molestias excepturi sint, obcaecati cupiditate non provident, similique sunt in culpa, qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio, cumque nihil impedit, quo minus id, quod maxime placeat, facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet, ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat";


        for (int i = 0; i < 50; i++) {
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
        System.out.println(Arrays.toString(s));

        for (int i = 0; i < 50; i++) {
            Key[] keys = new Key[3];
            keys[0] = new Key();
            keys[1] = new Key();
            keys[2] = new Key();
            TripleDes tripleDes = new TripleDes();

            byte[] result = tripleDes.encrypt(s, keys);
            byte[] result2 = tripleDes.decrypt(result, keys);
            System.out.println(Arrays.toString(result2));
            System.out.println(result2.length);
            System.out.println(i);


            Assertions.assertTrue(Arrays.equals(result2, s));
        }
    }
}
