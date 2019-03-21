package by.epam.kimbar.util.file;

import java.io.FileWriter;
import java.io.IOException;

public class NewsWriter {
    private NewsWriter(){}

    public static void  write(String content,String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(FileCreator.create(fileName));
        fileWriter.write(content);
        fileWriter.flush();
        fileWriter.close();
    }
}
