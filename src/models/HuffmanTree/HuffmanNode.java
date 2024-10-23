package models.HuffmanTree;

/**
 * Classe que representa um nó de uma árvore de Huffman.
 * 
 * Um nó de uma árvore de Huffman armazena a frequência de um caractere e
 * referências para seus filhos esquerdo e direito. Esta classe é usada na
 * construção da árvore de Huffman para compressão de dados.
 */
public class HuffmanNode {
    public int frequency;
    public char character;
    public HuffmanNode left;
    public HuffmanNode right;

    /**
     * Construtor que inicializa um nó de Huffman com a frequência, caractere e
     * referências para os filhos esquerdo e direito.
     * 
     * @param frequency A frequência do caractere.
     * @param character O caractere armazenado no nó.
     * @param left      A referência para o filho esquerdo.
     * @param right     A referência para o filho direito.
     */
    public HuffmanNode(int frequency, char character, HuffmanNode left, HuffmanNode right) {
        this.frequency = frequency;
        this.character = character;
        this.left = left;
        this.right = right;
    }

    /**
     * Construtor que inicializa um nó de Huffman com a frequência e o caractere.
     * Os filhos esquerdo e direito são inicializados como null.
     * 
     * @param frequency A frequência do caractere.
     * @param character O caractere armazenado no nó.
     */
    public HuffmanNode(int frequency, char character) {
        this(frequency, character, null, null);
    }

}
