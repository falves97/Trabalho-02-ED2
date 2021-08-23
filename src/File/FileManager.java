package File;

import Estructures.HashTable;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileManager {
    private static Scanner input;

    public static void openFile(String path) throws IOException {
        try
        {
            input = new Scanner(Paths.get(path));
        }
        catch (IOException ioException)
        {
            throw new IOException("Erro ao abrir o arquivo");
        }
    }

    public static void closeFile()
    {
        if (input != null)
            input.close();
    }

    public static HashTable<String, Integer> hashWords(String path) throws IOException {
        HashTable<String, Integer> words = new HashTable<>();

        openFile(path);
        int count = 0;
        while (input.hasNext()) {
            String str = input.next();
            str = str.replaceAll(",", "");
            str = str.replaceAll("\\.", "");
            str = str.replaceAll("-", "");

            words.put(str, count);
            count++;
        }

        return words;
    }
}
