package models.HashTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

@SuppressWarnings({ "unchecked" })
public class OpenAddressing<K, V> {
    int capacity;
    int current_size;
    HashNode<K, V>[] table;

    public OpenAddressing(int capacity) {
        this.table = new HashNode[capacity];
        this.current_size = 0;
        this.capacity = capacity;
    }

    // Métodos públicos que podem ser utilizados por qualquer classe

    public V get(K key) {
        HashNode<K, V> node = searchNode(key);
        if (node != null) {
            return node.value;
        }
        return null;
    }

    public void put(K key, V value) {
        int index;
        int num_attempts = 0;

        do {
            num_attempts++;
            index = linearAttempt(key.hashCode(), num_attempts);
            HashNode<K, V> node = table[index];

            if (node != null && node.key.equals(key)) {
                node.value = value;
                return;
            }

        } while (num_attempts < capacity && table[index] != null);

        if (num_attempts >= capacity) {
            System.out.println("Quantidade de tentativas excedida.");
            return;
        }

        table[index] = new HashNode<>(key, value);
        current_size++;
    }

    public void update(K key, V value) {
        HashNode<K, V> node = searchNode(key);
        if (node != null) {
            node.value = value;
        }
    }

    public V remove(K key) {
        HashNode<K, V> node = searchNode(key);
        V value = null;
        if (node != null) {
            value = node.value;
            node.value = null;
            table[hash(key.hashCode())] = null;
            current_size--;
        }
        return value;
    }

    public void print() {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                System.out.println("Index: " + i + " da tabela hash: " + table[i].value);
            }
        }
    }

    public boolean isFull() {
        return current_size == capacity;
    }

    public boolean isEmpty() {
        return current_size == 0;
    }

    // Métodos privados que só podem ser utilizados por esta classe

    private int hash(int code) {
        final double goldenNumber = (Math.sqrt(5) - 1) / 2;
        return (int) (capacity * ((code * goldenNumber) % 1));
    }

    private int linearAttempt(int code, int num_attempts) {
        int index = (hash(code) + num_attempts) % capacity;
        return index;
    }

    // Métodos de acesso
    private HashNode<K, V> searchNode(K key) {
        int index;
        int num_attempts = 0;

        do {
            index = linearAttempt(key.hashCode(), num_attempts);
            if (table[index] != null && table[index].key.equals(key)) {
                return table[index];
            }

            num_attempts++;

        } while (num_attempts < capacity && table[index] != null);

        return null;
    }

    // Classe interna para representar um nó na tabela hash
    private static class HashNode<K, V> {
        K key;
        V value;

        HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public Entry<Character, String>[] entrySet() {
        List<Entry<Character, String>> entries = new ArrayList<>();
        for (HashNode<K, V> node : table) {
            if (node != null) {
                entries.add((Entry<Character, String>) node);
            }
        }
        return entries.toArray(new Entry[0]);
    }

    public char[] keys() {
        List<Character> keys = new ArrayList<>();
        for (HashNode<K, V> node : table) {
            if (node != null) {
                keys.add((Character) node.key);
            }
        }
        char[] keysArray = new char[keys.size()];
        for (int i = 0; i < keys.size(); i++) {
            keysArray[i] = keys.get(i);
        }
        return keysArray;
    }
}
