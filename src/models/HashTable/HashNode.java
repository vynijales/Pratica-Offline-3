package models.HashTable;

/**
 * Classe que representa um nó em uma tabela hash.
 * 
 * @param <T> O tipo de elemento armazenado no nó.
 */
public class HashNode<T> {
    /**
     * O código hash do elemento armazenado no nó.
     */
    public int code;

    /**
     * O elemento armazenado no nó.
     */
    public T element;

    /**
     * Construtor que inicializa um nó da tabela hash com o código hash e o
     * elemento.
     * 
     * @param code    O código hash do elemento.
     * @param element O elemento armazenado no nó.
     */
    public HashNode(int code, T element) {
        this.code = code;
        this.element = element;
    }

    public HashNode() {
    }
}
