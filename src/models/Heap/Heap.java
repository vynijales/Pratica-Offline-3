package models.Heap;

/**
 * Classe abstrata que representa uma Heap.
 * 
 * Uma Heap é uma estrutura de dados baseada em árvore binária que satisfaz a
 * propriedade de heap:
 * - Em uma MinHeap, o nó pai é sempre menor ou igual aos seus filhos.
 * - Em uma MaxHeap, o nó pai é sempre maior ou igual aos seus filhos.
 * 
 * Esta classe fornece a implementação básica de uma Heap, incluindo métodos
 * para inserir (push),
 * remover (pop) e acessar o nó raiz (peek). As subclasses devem implementar os
 * métodos abstratos
 * heapUp() e heapDown() para manter a propriedade da heap durante as operações
 * de inserção e remoção.
 * 
 * @param <T> O tipo de elemento armazenado na Heap.
 */

public abstract class Heap<T> {
    protected HeapNode<T>[] heap;
    public int capacity;
    public int current_size;

    /**
     * Construtores que inicializa a Heap com a capacidade especificada.
     * 
     * @param capacity A capacidade inicial da Heap.
     */
    @SuppressWarnings("unchecked")
    public Heap(int capacity) {
        this.heap = new HeapNode[capacity];
        this.current_size = 0;
        this.capacity = capacity;
    }

    // Métodos abstratos que devem ser implementados pelas subclasses

    /**
     * Método abstrato que sobe o nó recém-inserido até a posição correta na Heap.
     * Deve ser implementado pelas subclasses.
     */
    abstract protected void heapUp();

    /**
     * Método abstrato que desce o nó raiz até a posição correta na Heap.
     * Deve ser implementado pelas subclasses.
     */
    abstract protected void heapDown();

    // Métodos públicos que podem ser utilizados por qualquer classe

    /**
     * Retorna o nó raiz da Heap sem removê-lo.
     * 
     * @return O nó raiz da Heap.
     */
    final public HeapNode<T> peek() {
        return peekAt(0);
    }

    /**
     * Insere um novo elemento na Heap com a chave especificada.
     * 
     * @param element O elemento a ser inserido.
     * @param key     A chave associada ao elemento.
     */
    final public void push(T element, int key) {
        HeapNode<T> node = new HeapNode<T>(key, element);

        if (exists(node)) {
            return;
        }

        if (current_size == capacity) {
            resize();
        }

        this.heap[current_size] = node;
        current_size++;
        heapUp();
    }

    /**
     * Remove e retorna o elemento do nó raiz da Heap.
     * 
     * @return O elemento do nó raiz removido da Heap.
     */
    final public T pop() {
        if (current_size == 0)
            return null;
        HeapNode<T> root = heap[0];
        heap[0] = heap[current_size - 1];
        current_size--;
        heapDown();
        return root.element;
    }

    // Métodos protegidos que podem ser utilizados pelas subclasses

    /**
     * Verifica se um nó já existe na Heap.
     * 
     * @param node O nó a ser verificado.
     * @return true se o nó existir na Heap, false caso contrário.
     */
    final protected boolean exists(HeapNode<T> node) {
        for (int i = 0; i < current_size; i++) {
            if (heap[i].equals(node)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna o nó na posição especificada da Heap.
     * 
     * @param index A posição do nó.
     * @return O nó na posição especificada, ou null se a posição for inválida.
     */
    final protected HeapNode<T> peekAt(int index) {
        if (index < 0 || index >= current_size) {
            return null;
        }
        return heap[index];
    }

    /**
     * Retorna o índice de um nó na Heap.
     * 
     * @param node O nó a ser localizado.
     * @return O índice do nó, ou -1 se o nó não for encontrado.
     */
    final protected int indexOf(HeapNode<T> node) {
        for (int i = 0; i < current_size; i++) {
            if (heap[i].equals(node)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Troca dois nós na Heap.
     * 
     * @param i O índice do primeiro nó.
     * @param j O índice do segundo nó.
     */
    final protected void swap(int i, int j) {
        HeapNode<T> temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    /**
     * Verifica se um índice é válido na Heap.
     * 
     * @param index O índice a ser verificado.
     * @return true se o índice for válido, false caso contrário.
     */
    final protected boolean isValidIndex(int index) {
        return index >= 0 && index < capacity;
    }

    /**
     * Redimensiona a Heap para o dobro da capacidade atual.
     */
    @SuppressWarnings("unchecked")
    final protected void resize() {
        capacity *= 2;
        HeapNode<T>[] new_heap = new HeapNode[capacity];
        for (int i = 0; i < current_size; i++) {
            new_heap[i] = heap[i];
        }
        heap = new_heap;
    }

    // Métodos internos de acesso

    /**
     * Verifica se um nó tem pai.
     * 
     * @param index O índice do nó.
     * @return true se o nó tiver pai, false caso contrário.
     */
    final protected boolean hasParent(int index) {
        return index > 0;
    }

    /**
     * Retorna o nó pai de um nó na Heap.
     * 
     * @param index O índice do nó.
     * @return O nó pai, ou null se o índice for inválido ou se o nó não tiver pai.
     */
    final protected HeapNode<T> parent(int index) {
        if (!isValidIndex(index) && !hasParent(index)) {
            return null;
        }
        return heap[parentIndex(index)];
    }

    /**
     * Retorna o índice do nó pai de um nó na Heap.
     * 
     * @param index O índice do nó.
     * @return O índice do nó pai.
     */
    final protected int parentIndex(int index) {
        return (index - 1) / 2;
    }

    /**
     * Verifica se um nó tem filho esquerdo.
     * 
     * @param index O índice do nó.
     * @return true se o nó tiver filho esquerdo, false caso contrário.
     */
    protected boolean hasLeftChild(int index) {
        return leftChildIndex(index) < current_size;
    }

    /**
     * Verifica se um nó tem filho direito.
     * 
     * @param index O índice do nó.
     * @return true se o nó tiver filho direito, false caso contrário.
     */
    protected boolean hasRightChild(int index) {
        return rightChildIndex(index) < current_size;
    }

    /**
     * Retorna o filho esquerdo de um nó na Heap.
     * 
     * @param index O índice do nó.
     * @return O filho esquerdo, ou null se o índice for inválido.
     */
    protected HeapNode<T> leftChild(int index) {
        if (!isValidIndex(index)) {
            return null;
        }
        return heap[leftChildIndex(index)];
    }

    /**
     * Retorna o filho direito de um nó na Heap.
     * 
     * @param index O índice do nó.
     * @return O filho direito, ou null se o índice for inválido.
     */
    protected HeapNode<T> rightChild(int index) {
        if (!isValidIndex(index)) {
            return null;
        }
        return heap[rightChildIndex(index)];
    }

    /**
     * Retorna o índice do filho esquerdo de um nó na Heap.
     * 
     * @param index O índice do nó.
     * @return O índice do filho esquerdo.
     */
    protected int leftChildIndex(int index) {
        return 2 * index + 1;
    }

    /**
     * Retorna o índice do filho direito de um nó na Heap.
     * 
     * @param index O índice do nó.
     * @return O índice do filho direito.
     */
    protected int rightChildIndex(int index) {
        return 2 * index + 2;
    }
}
