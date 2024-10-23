package models.Heap;

/**
 * Classe que representa uma MinHeap.
 * 
 * Uma MinHeap é uma estrutura de dados baseada em árvore binária que satisfaz a
 * propriedade de heap:
 * - O nó pai é sempre menor ou igual aos seus filhos.
 * 
 * Esta classe fornece a implementação específica de uma MinHeap, incluindo
 * métodos para inserir (push), remover (pop) e acessar o nó raiz (peek).
 * Os métodos heapUp() e heapDown() são implementados para manter a propriedade
 * da MaxHeap durante as operações de inserção e remoção.
 */

public class MinHeap<T> extends Heap<T> {

    public MinHeap(int capacity) {
        super(capacity);
    }

    /**
     * Método que sobe o nó recém-inserido até a posição correta no MinHeap.
     * A posição correta é quando o nó pai é menor que o nó filho.
     * O nó recém-inserido é comparado com seu pai e trocado se for menor,
     * repetindo o processo até que a propriedade do MinHeap seja restaurada.
     * 
     * @return void
     */
    @Override
    public void heapUp() {
        int index = current_size - 1;
        while (hasParent(index) && heap[index].key < parent(index).key) {
            swap(index, parentIndex(index));
            index = parentIndex(index);
        }
    }

    /**
     * Método que desce o nó raiz até a posição correta no MinHeap.
     * A posição correta é quando o nó pai é menor que ambos os nós filhos.
     * O nó raiz é comparado com seus filhos e trocado com o menor deles,
     * repetindo o processo até que a propriedade do MinHeap seja restaurada.
     * 
     * @return void
     */
    @Override
    protected void heapDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = leftChildIndex(index);
            if (hasRightChild(index) && rightChild(index).key < leftChild(index).key) {
                smallerChildIndex = rightChildIndex(index);
            }
            if (heap[index].key <= heap[smallerChildIndex].key) {
                break;
            }
            swap(index, smallerChildIndex);
            index = smallerChildIndex;
        }
    }

    /**
     * Método que insere um elemento na MinHeap.
     * 
     * @param element O elemento a ser inserido.
     * @param code    A chave do elemento a ser inserido.
     * @return void
     */
    static MinHeap<Object> heapify(Object[] elements, int[] priorities) {
        MinHeap<Object> heap = new MinHeap<>(elements.length);
        for (int i = 0; i < elements.length; i++) {
            heap.push(elements[i], priorities[i]);
        }
        return heap;
    }
}
