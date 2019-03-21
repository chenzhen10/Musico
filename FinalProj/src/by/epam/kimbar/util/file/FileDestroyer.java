package by.epam.kimbar.util.file;

import java.io.File;

public class FileDestroyer {
    private FileDestroyer() {
    }

    public static boolean delete(String path) {
        File file = new File(path);
        return file.delete();
    }

}
