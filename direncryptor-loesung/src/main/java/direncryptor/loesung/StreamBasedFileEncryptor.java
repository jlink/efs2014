package direncryptor.loesung;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StreamBasedFileEncryptor implements FileEncryptor {
    private StreamEncryptor streamEncryptor;

    public StreamBasedFileEncryptor(StreamEncryptor streamEncryptor) {
        this.streamEncryptor = streamEncryptor;
    }

    @Override
    public void encrypt(File source, File target) throws IOException {
        //Todo: I'm quite sure I am missing some error cases with unclosed streams
        FileInputStream in = new FileInputStream(source);
        FileOutputStream out = new FileOutputStream(target);
        try {
            streamEncryptor.encrypt(in, out);
        } finally {
            in.close();
            out.close();
        }
    }

    public StreamEncryptor getStreamEncryptor() {
        return streamEncryptor;
    }
}
