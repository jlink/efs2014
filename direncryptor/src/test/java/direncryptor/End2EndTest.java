package direncryptor;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class End2EndTest implements ProgressIndicator {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private double lastProgress;

    @Test
    public void encryptThreeFilesWithProgressNotification() throws Exception {
        File file1 = createFileInFolder("file1.txt");
        byte[] file1Contents = Files.readAllBytes(file1.toPath());

        File file2 = createFileInFolder("file2.bin");
        File file3 = createFileInFolder("file3");

        int caesarKey = 13;
        StreamEncryptor streamEncryptor = new CaesarStreamEncryptor(caesarKey);

        DirectoryEncryptor directoryEncryptor = new DirectoryEncryptor(streamEncryptor);
        directoryEncryptor.setProgressIndicator(this);

        File directoryToEncrypt = folder.getRoot();
        directoryEncryptor.encryptDirectory(directoryToEncrypt);

        assertEquals(1.0, lastProgress, 0.0);

        String[] resultingFiles = directoryToEncrypt.list();
        Arrays.sort(resultingFiles);

        assertArrayEquals(new String[]{"file1.txt.enc", "file2.bin.enc", "file3.enc"}, resultingFiles);

        byte[] file1EncryptedContents = Files.readAllBytes(new File(directoryToEncrypt, "file1.txt.enc").toPath());

        assertEquals(file1Contents.length, file1EncryptedContents.length);
        assertEquals(file1Contents[0] + caesarKey, file1EncryptedContents[0]);
    }

    private File createFileInFolder(String fileName) throws IOException {
        File file1 = folder.newFile(fileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file1));
        writer.write("First line");
        writer.newLine();
        writer.write("Second line");
        writer.newLine();
        writer.close();
        return file1;
    }

    @Override
    public void setProgress(double progress) {
        lastProgress = progress;
    }
}
