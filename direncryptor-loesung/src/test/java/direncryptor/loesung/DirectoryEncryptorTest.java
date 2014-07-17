package direncryptor.loesung;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.io.File;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class DirectoryEncryptorTest {

    private FileEncryptor fileEncryptor;
    private DirectoryService directoryService;

    @Before
    public void setUp() throws Exception {
        fileEncryptor = mock(FileEncryptor.class);
        directoryService = mock(DirectoryService.class);
    }

    @Test
    public void encryptingEmptyDirectoryDoesNothing() throws Exception {
        when(directoryService.listFilesInDirectory(any(File.class))).thenReturn(new File[0]);
        DirectoryEncryptor encryptor = new DirectoryEncryptor(fileEncryptor, directoryService);

        File emptyDirectory = new File("./");
        encryptor.encryptDirectory(emptyDirectory);

        verify(fileEncryptor, times(0)).encrypt(any(File.class), any(File.class));
        verify(directoryService).listFilesInDirectory(emptyDirectory);
    }

    @Test
    public void defaultDirectoryServiceIsFileBased() throws Exception {
        StreamEncryptor anyStreamEncryptor = new CaesarStreamEncryptor(0);
        DirectoryEncryptor encryptor = new DirectoryEncryptor(anyStreamEncryptor);

        assertTrue(encryptor.getDirectoryService() instanceof FileBasedDirectoryService);
    }

    @Test
    public void streamEncryptorIsWrappedInFileEncryptorByDefault() throws Exception {
        StreamEncryptor streamEncryptor = new CaesarStreamEncryptor(0);
        DirectoryEncryptor encryptor = new DirectoryEncryptor(streamEncryptor);

        StreamBasedFileEncryptor defaultFileEncryptor = (StreamBasedFileEncryptor) encryptor.getFileEncryptor();
        assertSame(streamEncryptor, defaultFileEncryptor.getStreamEncryptor());
    }

    @Test
    public void encryptionTargetIsEncryptedByFileEncryptorAndSourceIsDeletedAfterwards() throws Exception {
        File directory = new File("./");
        File encryptionSource = new File("test.txt");
        File encryptionTarget = new File("test.txt.enc");
        when(directoryService.listFilesInDirectory(any(File.class))).thenReturn(new File[] {encryptionSource});

        DirectoryEncryptor encryptor = new DirectoryEncryptor(fileEncryptor, directoryService);

        encryptor.encryptDirectory(directory);

        InOrder orderOfFileEncryption = inOrder(fileEncryptor, directoryService);
        orderOfFileEncryption.verify(fileEncryptor).encrypt(encryptionSource, encryptionTarget);
        orderOfFileEncryption.verify(directoryService).deleteFile(encryptionSource);
    }

}
