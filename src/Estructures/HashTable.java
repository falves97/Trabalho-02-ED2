package Estructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HashTable <K extends Comparable<K> , V extends Comparable<V>> {
    private int firstPrimeNumber;
    private int secondPrimeNumber;
    private int initialCapacity;
    private int capacity;
    private final int maxColisions;
    private Item<K, V>[] elements;

    public HashTable() {
        initialCapacity = 1024;
        capacity = initialCapacity;
        maxColisions = 3;
        firstPrimeNumber = findPrimeNumber(capacity / 3);
        secondPrimeNumber = findPrimeNumber(capacity / 2);
        this.elements = new Item[capacity];
    }


    /*private long firstHash(K keyElement) {
        return hashMult(keyElement) + firstPrimeNumber;
    }*/


    /*private long secondtHash(K keyElement) {
        int second = Math.abs(keyElement.hashCode()) % this.initialCapacity + secondPrimeNumber;
        return second % 2 == 0 ? second + 1 : second;
    }*/

    /*private int doubleHash(K key, int countColisions) {
        return Math.toIntExact(Math.abs(firstHash(key) + countColisions * secondtHash(key)) % this.capacity);
    }*/

    /**
     * Em caso de ultrapassar o limite de colisões aceitas, esse métedo irá dobrar a capacidade da tabela
     */
    private void doubleSize() {
        this.capacity *= 2;

        Item<K, V>[] elementsAux = this.elements.clone();
        this.elements = new Item[capacity];

        for (int i = 0; i < elementsAux.length; i++) {
            this.elements[i] = elementsAux[i];
        }
    }

    private int hashMult(K key){
        double constA = (Math.sqrt(5) - 1) / 2;
        return (int) Math.floor((Math.abs(key.hashCode()) * constA) % 1 * capacity);
    }

    private int hashQuadratic(K key, int colisions) {
        if(colisions == 0) {
            return hashMult(key);
        }
        else{
            colisions--;
            return (hashQuadratic(key, colisions) + colisions + 1) % capacity;
        }
    }

    private void insert(Item<K, V> item, int colisions) {
        int index = hashQuadratic(item.getKey(), colisions);

        if (elements[index] == null) {
            elements[index] = item;
        }
        else if (++colisions < maxColisions){
            if (elements[index].compareTo(item) != 0) {
                insert(item, colisions);
            }
        }
        else {
            doubleSize();
            insert(item, 0);
        }
    }

    private int hashDivision(K key) {
        return 0;
    }

    /**
     * Métedo chamado para inserir uma nova chave e valor
     * @param key Chave a ser inseriada usando o hash
     * @param value Valor atribuido a chave
     */
    public void put(K key, V value) {
        Item<K, V> item = new Item<>(key, value);
        insert(item, 0);
    }

    /**
     * Buscar um valor a partir de uma chave
     * @param key Chave
     * @return Valor
     */
    public V find(K key) {
        int capacityAux = this.capacity;

        while (capacityAux >= initialCapacity) {
            int index;

            for (int i = 0; i < maxColisions; i++) {
                index = hashQuadratic(key, 0);
                if (this.elements[index] != null) {
                    if (this.elements[index].getKey().hashCode() == key.hashCode()) {
                        return this.elements[index].getValue();
                    }
                }
            }

            capacityAux = capacityAux / 2;
        }

        return null;
    }

    /**
     * Métedo para obter todos os valores atribuídos a uma chave
     * @param key Chave
     * @return Valores
     */
    public List<V> findAll(K key) {
        List<V> ocurrences = new ArrayList<>();

        searchAll(key, 0, ocurrences, 32);

        if (ocurrences.size() > 0) {
            return ocurrences;
        }

        return null;
    }

    /**
     * Métedo que encontra todos os valores de uma mesma classe e os reune em uma List
     * @param key Chave
     * @param countColisions Conta quantas colisões ocorreram, para saber se o hash para encontrar a próxima
     * ocorrencia da chave, deve ou não mudar
     * @param ocurrences Valores para essa chave
     * @param capacity Capacidade da tabela, considerando as colisões
     */
    private void searchAll(K key, int countColisions, List<V> ocurrences, int capacity) {
        if (capacity <= this.capacity) {
            int index = Math.toIntExact(hashQuadratic(key, 0));

            if (this.elements[index] != null) {
                if (ocurrences != null) {
                    if (!ocurrences.contains(this.elements[index].getValue())) {
                        ocurrences.add(this.elements[index].getValue());
                    }

                    countColisions++;

                    if (countColisions < this.maxColisions) {
                        searchAll(key, countColisions, ocurrences, capacity);
                    }
                    else {
                        searchAll(key, 0, ocurrences, capacity *= 2);
                    }
                }
            }
        }
    }

    /**
     * Métedo calcula qual o mair número primo menor que ou igual ao valor passado como parâmentro
     * @param number
     * @return Maior primo menor ou igual a number
     */
    private static int findPrimeNumber(int number) {
        int prime = number;
        boolean isPrime = false;

        while (!isPrime) {
            int i;
            for ( i = 2; i <= 10; i++) {
                if (prime % i == 0) {
                    prime--;
                    break;
                }
            }
            if (i > 10) {
                isPrime = true;
            }
        }

        return prime;
    }

    @Override
    public String toString() {
        return "HashTable{" +
                "firstPrimeNumber=" + firstPrimeNumber +
                ", secondPrimeNumber=" + secondPrimeNumber +
                ", capacity=" + capacity +
                ", maxColisions=" + maxColisions +
                ", elements=" + Arrays.toString(elements) +
                '}';
    }
}
