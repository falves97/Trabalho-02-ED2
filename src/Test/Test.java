package Test;

import Estructures.HashTable;
import File.FileManager;

import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        try {
            HashTable<String, Integer> words = FileManager.hashWords("/home/nando/Documentos/GitHub/Trabalho-02-ED2/src/Test/Texts/texto01.txt");
            System.out.println(words);

        } catch (IOException e) {
            System.out.println("Coloque o arquivo direito, seu animal!");;
        }
    }
}
