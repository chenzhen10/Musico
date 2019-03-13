package by.epam.kimbar.util.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NewsFileReader {



    public static String readData(String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        FileReader fr = new FileReader(path);
        BufferedReader bf = new BufferedReader(fr);

        String line = bf.readLine();
        while (line != null){
            sb.append(line);
            sb.append("\n");
            line = bf.readLine();
        }
        return sb.toString();
    }
}
