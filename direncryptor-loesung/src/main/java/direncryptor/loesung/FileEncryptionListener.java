package direncryptor.loesung;

import java.io.File;

public interface FileEncryptionListener {
    void success(File source, File target, StreamEncryptor encryptor);
    void failure(File source, String failureReason, StreamEncryptor encryptor);
}
