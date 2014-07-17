package direncryptor.loesung;

import java.io.File;

public class FileBasedDirectoryService implements DirectoryService {
    @Override
    public File[] listFilesInDirectory(File directory) {
        return new File[0];
    }
}
