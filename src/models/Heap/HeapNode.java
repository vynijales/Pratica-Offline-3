package models.Heap;

/**
 * Classe que representa um nó de uma Heap.
 * 
 * @param <T> Tipo do elemento armazenado no nó.
 */

public class HeapNode<T> {
    public int key;
    public T element;

    public HeapNode(int key, T value) {
        this.key = key;
        this.element = value;
    }

    public boolean equals(HeapNode<T> node) {
        return this.key == node.key;
    }
}
