package by.epam.kimbar.util.file;

import java.io.File;
import java.io.IOException;

public class FileCreator {
    private static final String PATH = "D:\\News\\";
    private static final String FILE_EXTENSION = ".txt";

    private FileCreator() {
    }

    public static File create(String name) throws IOException {
        File file = new File(PATH + name + FILE_EXTENSION);
        file.createNewFile();
        return  file;
    }

}
