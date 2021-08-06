package Estructures;

import java.util.ArrayList;
import java.util.List;

public class Hashtable <K extends Comparable<K> , V extends Comparable<V>> {
    private List<V> elements;

    /**
     * @param LenElements Tamanho incicial da tabela
     */
    public Hashtable(int LenElements) {
        this.elements = new ArrayList<V>(LenElements);
    }

    /**
     * Primeira função de hash para calcular a posição do elemento na tabela
     * @param element Elemento a ser inserido na tabela
     * @return Retorna a posição em que o elemento deve ser inserido
     */
    private int firstHash(V element) {
        return 0;
    }

    /**
     * Segunda função de hash para calcular a posição do elemento na tabela, caso ocorra uma colisão na utilização da
     * primeira função
     * @param element Elemento a ser inserido na tabela
     * @return Retorna a posição em que o elemento deve ser inserido
     */
    private int secondtHash(V element) {
        return 0;
    }

    public void insert(V element) {

    }

    public V find(K key) {
        return null;
    }
}
