package models.Heap;

/**
 * Classe que representa uma MaxHeap.
 * 
 * Uma MaxHeap é uma estrutura de dados baseada em árvore binária que satisfaz a
 * propriedade de heap:
 * - O nó pai é sempre maior ou igual aos seus filhos.
 * 
 * Esta classe fornece a implementação específica de uma MaxHeap, incluindo
 * métodos para inserir (push),remover (pop) e acessar o nó raiz (peek). Os
 * métodos heapUp() e heapDown() são implementados para manter a propriedade da
 * MaxHeap durante as operações de inserção e remoção.
 * 
 * @param <T> O tipo de elemento armazenado na MaxHeap.
 */

public class MaxHeap<T> extends Heap<T> {

    /**
     * Construtor que inicializa a MaxHeap com a capacidade especificada.
     * 
     * @param capacity A capacidade inicial da MaxHeap.
     */
    public MaxHeap(int capacity) {
        super(capacity);
    }

    /**
     * Método que sobe o nó recém-inserido até a posição correta na MaxHeap.
     * A posição correta é quando o nó pai é maior que o nó filho.
     * O nó recém-inserido é comparado com seu pai e trocado se for maior,
     * repetindo o processo até que a propriedade da MaxHeap seja restaurada.
     * 
     * @return void
     */
    @Override
    protected void heapUp() {
        int index = current_size - 1;
        while (hasParent(index) && heap[index].key > parent(index).key) {
            swap(index, parentIndex(index));
            index = parentIndex(index);
        }
    }

    /**
     * Método que desce o nó raiz até a posição correta na MaxHeap.
     * A posição correta é quando o nó pai é maior que ambos os nós filhos.
     * O nó raiz é comparado com seus filhos e trocado com o maior deles,
     * repetindo o processo até que a propriedade da MaxHeap seja restaurada.
     * 
     * @return void
     */
    @Override
    protected void heapDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int greaterChildIndex = leftChildIndex(index);
            if (hasRightChild(index) && rightChild(index).key > leftChild(index).key) {
                greaterChildIndex = rightChildIndex(index);
            }
            if (heap[index].key >= heap[greaterChildIndex].key) {
                break;
            }
            swap(index, greaterChildIndex);
            index = greaterChildIndex;
        }
    }

    /**
     * Método que insere um elemento na MaxHeap.
     * 
     * @param element O elemento a ser inserido.
     * @param key     A chave do elemento a ser inserido.
     * @return void
     */
    static MaxHeap<Object> heapify(Object[] array, int[] priorities) {
        MaxHeap<Object> heap = new MaxHeap<>(array.length);
        for (int i = 0; i < array.length; i++) {
            heap.push(array[i], priorities[i]);
        }
        return heap;
    }
}
