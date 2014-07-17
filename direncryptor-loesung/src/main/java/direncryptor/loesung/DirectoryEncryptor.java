package direncryptor.loesung;

import java.io.File;
import java.io.IOException;

public class DirectoryEncryptor {
    private final DirectoryService directoryService;
    private final FileEncryptor fileEncryptor;

    public DirectoryEncryptor(StreamEncryptor streamEncryptor) {
        this(new StreamBasedFileEncryptor(streamEncryptor), new FileBasedDirectoryService());
    }

    public DirectoryEncryptor(FileEncryptor fileEncryptor, DirectoryService directoryService) {
        this.fileEncryptor = fileEncryptor;
        this.directoryService = directoryService;
    }

    public void setProgressIndicator(ProgressIndicator progressIndicator) {
    }

    public void encryptDirectory(File directory) throws IOException {
        File[] filesToEncrypt = directoryService.listFilesInDirectory(directory);
        for (File source: filesToEncrypt) {
            File target = new File(source.getParentFile(), source.getName() + ".enc");
            fileEncryptor.encrypt(source, target);
            directoryService.deleteFile(source);
        }
    }

    public DirectoryService getDirectoryService() {
        return directoryService;
    }

    public FileEncryptor getFileEncryptor() {
        return fileEncryptor;
    }
}
