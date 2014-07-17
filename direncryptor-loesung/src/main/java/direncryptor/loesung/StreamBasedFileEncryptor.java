package direncryptor.loesung;

import java.io.File;

public class StreamBasedFileEncryptor implements FileEncryptor {
    private StreamEncryptor streamEncryptor;

    public StreamBasedFileEncryptor(StreamEncryptor streamEncryptor) {
        this.streamEncryptor = streamEncryptor;
    }

    @Override
    public void encrypt(File source, File target) {

    }

    public StreamEncryptor getStreamEncryptor() {
        return streamEncryptor;
    }
}
