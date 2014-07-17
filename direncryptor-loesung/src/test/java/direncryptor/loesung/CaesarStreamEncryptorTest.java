package direncryptor.loesung;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CaesarStreamEncryptorTest {

    @Test
    public void encryptEmptyStream() throws Exception {
        byte[] result = encryptWithCaesarStreamEncoder(42, new byte[0]);
        assertEquals(0, result.length);
    }

    @Test
    public void encryptingWithKey0LeavesContentUnchanged() throws Exception {
        byte[] contents = new byte[] {0, 1, 2, (byte) 255};
        byte[] result = encryptWithCaesarStreamEncoder(0, contents);
        assertArrayEquals(contents, result);
    }

    @Test
    public void encryptingWithKeyIncreasesByteValuesByKey() throws Exception {
        byte[] contents = new byte[] {0, 1, 2, (byte) 242};
        byte[] result = encryptWithCaesarStreamEncoder(13, contents);
        assertArrayEquals(new byte[] {13, 14, 15, (byte) 255}, result);
    }

    @Test
    public void encryptingWrapsValueAroundIfBiggerThan255() throws Exception {
        byte[] contents = new byte[] {0, 1, 2, (byte) 255};
        byte[] result = encryptWithCaesarStreamEncoder(254, contents);
        assertArrayEquals(new byte[] {(byte) 254, (byte) 255, 0, (byte) 253}, result);
    }

    private byte[] encryptWithCaesarStreamEncoder(int key, byte[] contents) throws IOException {
        InputStream source = createSourceWithContent(contents);
        ByteArrayOutputStream target = new ByteArrayOutputStream();
        CaesarStreamEncryptor encryptor = new CaesarStreamEncryptor(key);
        encryptor.encrypt(source, target);
        return target.toByteArray();
    }

    private ByteArrayInputStream createSourceWithContent(byte[] bytes) {
        return new ByteArrayInputStream(bytes);
    }
}
