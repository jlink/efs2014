package direncryptor.loesung;

import java.io.File;
import java.io.IOException;

public interface FileEncryptor {
    void encrypt(File source, File target) throws IOException;
}
