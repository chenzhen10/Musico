package by.epam.kimbar.util.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Thuis class is for reading data from file
 */
public class NewsFileReader {
    /**
     * private empty constructor  don't need to create an entity
     */
    private NewsFileReader() {
    }

    /**
     * Method read data from file
     * @param path
     *          Path to file
     * @return content of the file
     * @throws IOException
     */
    public static String readData(String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        FileReader fr = new FileReader(path);
        BufferedReader bf = new BufferedReader(fr);

        String line = bf.readLine();
        while (line != null) {
            sb.append(line);
            sb.append("\n");
            line = bf.readLine();
        }
        bf.close();
        fr.close();
        return sb.toString();
    }
}
