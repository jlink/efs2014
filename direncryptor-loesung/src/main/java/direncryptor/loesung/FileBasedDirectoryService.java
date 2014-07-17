package direncryptor.loesung;

import java.io.File;
import java.io.FileFilter;

public class FileBasedDirectoryService implements DirectoryService {
    @Override
    public File[] listFilesInDirectory(File directory) {
        return directory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
    }

    @Override
    public void deleteFile(File encryptionSource) {
        encryptionSource.delete();
    }
}
