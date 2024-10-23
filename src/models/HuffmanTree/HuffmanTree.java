package models.HuffmanTree;

import models.HashTable.OpenAddressing;
import models.Heap.MinHeap;

public class HuffmanTree {
    public HuffmanNode root;
    private OpenAddressing<String> hashtable;

    public HuffmanTree(Character[] chars, int[] frequencies) {
        MinHeap<HuffmanNode> minHeap = new MinHeap<>(chars.length);
        HuffmanNode node;
        hashtable = new OpenAddressing<>(chars.length);
        for (int i = 0; i < chars.length; i++) {
            node = new HuffmanNode(frequencies[i], chars[i]);
            minHeap.push(node, frequencies[i]);
        }

        root = null;

        while (minHeap.current_size > 1) {
            HuffmanNode left = minHeap.pop();
            HuffmanNode right = minHeap.pop();

            int freq = left.frequency + right.frequency;
            char c = '\0';
            HuffmanNode leftNode = left;
            HuffmanNode rightNode = right;

            HuffmanNode parent = new HuffmanNode(freq, c, leftNode, rightNode);
            root = parent;

            minHeap.push(parent, parent.frequency);
        }
    }

    public void buildCodeTree(HuffmanNode node, String code) {
        if (node == null) {
            return;
        }

        if (node.character != '\0') {
            System.out.println(node.character + ": " + code);
            hashtable.add(node.character, code);
        }

        buildCodeTree(node.left, code + "0");
        buildCodeTree(node.right, code + "1");
    }

    // Método para exportar a árvore de Huffman para uma tabela hash, sendo a chave
    // o código binário e o valor o caractere
}
