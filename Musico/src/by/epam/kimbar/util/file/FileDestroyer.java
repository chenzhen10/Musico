package by.epam.kimbar.util.file;

import java.io.File;

/**
 * This class is for deleting file
 */
public class FileDestroyer {
    /** private empty constructor  don't need to create an entity  */
    private FileDestroyer() {
    }

    /**
     * This method delete file by name
     * @param path
     * @return true - file deleted, false - not
     */
    public static boolean delete(String path) {
        File file = new File(path);
        return file.delete();
    }

}
