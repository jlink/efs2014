package direncryptor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface StreamEncryptor {

    /**
     * Encrypt a source stream and write the result to the target stream.
     * Do not close either stream when done.
     * @param source
     * @param target
     */
    void encrypt(InputStream source, OutputStream target) throws IOException;
}
