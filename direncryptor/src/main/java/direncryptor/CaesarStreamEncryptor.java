package direncryptor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CaesarStreamEncryptor implements StreamEncryptor {

    private final int key;

    public CaesarStreamEncryptor(int key) {
        assert key >= 0;
        assert key <= 255;
        this.key = key;
    }

    @Override
    public void encrypt(InputStream source, OutputStream target) throws IOException {
        while (source.available() > 0) {
            //No deliberate wrap around necessary since internal casting from int to byte will take care of it
            int encryptedByte = source.read() + key;
            target.write(encryptedByte);
        }
    }

}
