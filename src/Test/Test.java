package Test;

import Estructures.Item;

public class Test {
    public static void main(String[] args) {
        Item<String, Integer> inteiro = new Item<>("Numero", 1);
        Item<String, Integer> outroInt = new Item<>("Numero", 1);

        System.out.println(inteiro.compareTo(outroInt));
    }
}
