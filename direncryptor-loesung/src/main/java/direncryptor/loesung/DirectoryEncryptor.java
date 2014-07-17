package direncryptor.loesung;

import java.io.File;

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

    public void encryptDirectory(File directory) {
        File[] filesToEncrypt = directoryService.listFilesInDirectory(directory);
    }

    public DirectoryService getDirectoryService() {
        return directoryService;
    }

    public FileEncryptor getFileEncryptor() {
        return fileEncryptor;
    }
}
