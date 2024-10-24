package models.HashTable;

public class Node<T> {
    public Integer key;
    public T value;
    public Node<T> next;

    public Node(Integer key, T value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public Node() {
    }

}
