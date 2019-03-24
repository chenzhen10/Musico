package by.epam.kimbar.util.file;

import java.io.FileWriter;
import java.io.IOException;

/**
 * This class create and write data to file
 */
public class NewsWriter {
    /**
     * private empty constructor  don't need to create an entity
     */
    private NewsWriter() {
    }

    /**
     * Method create new file and write data into it
     * @param content
     *         Content of th file
     * @param fileName
     *         Name of the file
     * @throws IOException
     * @see by.epam.kimbar.util.file.FileCreator#create(String)
     */
    public static void write(String content, String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(FileCreator.create(fileName));
        fileWriter.write(content);
        fileWriter.flush();
        fileWriter.close();
    }
}
