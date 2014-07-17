package direncryptor.loesung;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import static org.junit.Assert.assertArrayEquals;

public class FileBasedStreamEncryptorTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();


    @Test
    public void encryptingASourceToATargetWillDelegateToInnerStreamEncryptor() throws Exception {
        File source = folder.newFile();
        File target = folder.newFile();

        FileOutputStream out = new FileOutputStream(source);
        out.write(new byte[]{1, 2, 3, 4});
        out.close();

        FileEncryptor encryptor = new StreamBasedFileEncryptor(new CaesarStreamEncryptor(1));
        encryptor.encrypt(source, target);

        FileInputStream in = new FileInputStream(target);
        byte[] result = new byte[4];
        in.read(result);

        assertArrayEquals(new byte[]{2, 3, 4, 5}, result);
    }
}
