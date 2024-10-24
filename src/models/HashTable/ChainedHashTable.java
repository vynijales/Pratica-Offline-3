package models.HashTable;

// Lista autoajustável
public class ChainedHashTable<T> {
    public int capacity;
    int current_size;
    Node<T>[] table;

    @SuppressWarnings("unchecked")
    public ChainedHashTable(int capacity) {
        this.table = new Node[capacity];
        this.current_size = 0;
        this.capacity = capacity;
    }

    // Métodos públicos que podem ser utilizados por qualquer classe
    public T get(Integer key) {
        Node<T> node = searchNode(key);
        if (node != null) {
            return node.value;
        }
        return null;
    }

    public void add(Integer key, T value) {
        if (getLoadFactor() > 0.75) {
            resize();
        }

        int index = key.hashCode() % capacity;
        Node<T> node = table[index];

        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
            node = node.next;
        }

        Node<T> new_node = new Node<>(key, value);
        new_node.next = table[index];
        table[index] = new_node;
        current_size++;
    }

    public void update(Integer key, T value) {
        Node<T> node = searchNode(key);
        if (node != null) {
            node.value = value;
        }
    }

    public void remove(Integer key) {
        Node target = searchNode(key);

        if (target == null) {
            return;
        }

        int index = key.hashCode() % capacity;

        Node<T> node = table[index];

    }

    public void print() {
        for (int i = 0; i < capacity; i++) {
            Node<T> node = table[i];
            System.out.print("[" + i + "]");
            while (node != null) {
                System.out.print(" -> " + node.value);
                node = node.next;
            }
            System.out.println();
        }
    }

    // Métodos privados que só podem ser utilizados pela própria classe

    private Node<T> searchNode(Integer key) {
        int index = key.hashCode() % capacity;
        Node<T> node = table[index];

        while (node != null) {
            if (node.key.equals(key)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    private double getLoadFactor() {
        return (double) current_size / capacity;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int new_capacity = capacity * 2;
        Node<T>[] new_table = new Node[new_capacity];

        for (int i = 0; i < capacity; i++) {
            Node<T> node = table[i];
            while (node != null) {
                Node<T> next = node.next;
                int index = node.key.hashCode() % new_capacity;
                node.next = new_table[index];
                new_table[index] = node;
                node = next;
            }
        }

        table = new_table;
        capacity = new_capacity;
    }

}
