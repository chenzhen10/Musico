package by.epam.kimbar.util.file;

import java.io.File;
import java.io.IOException;

/**
 * This class is for creating new file
 */
public class FileCreator {
    /**
     * Extensions and path to file
     */
    private static final String PATH = "D:\\News\\";
    private static final String FILE_EXTENSION = ".txt";

    /** private empty constructor  don't need to create an entity  */
    private FileCreator() {
    }

    /**
     * Method create file by name
     * @param name
     *          Name of the file
     * @return new file
     * @throws IOException
     */
    public static File create(String name) throws IOException {
        File file = new File(PATH + name + FILE_EXTENSION);
        file.createNewFile();
        return  file;
    }

}
