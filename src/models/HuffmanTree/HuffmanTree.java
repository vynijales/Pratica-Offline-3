package models.HuffmanTree;

import models.HashTable.OpenAddressing;
import models.Heap.MinHeap;

public class HuffmanTree {
    public HuffmanNode root;
    private OpenAddressing<Character, String> hashtable;

    public HuffmanTree(Character[] chars, Integer[] frequencies) {
        MinHeap<HuffmanNode> minHeap = new MinHeap<>(chars.length);
        HuffmanNode node;
        hashtable = new OpenAddressing<Character, String>(chars.length);
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

            HuffmanNode parent = new HuffmanNode(freq, c, left, right);
            root = parent;

            minHeap.push(parent, parent.frequency);
        }
    }

    public void buildCodeTree(HuffmanNode node, String code) {
        if (node == null) {
            return;
        }

        if (node.character != '\0') {
            hashtable.put(node.character, code);
        }

        buildCodeTree(node.left, code + "0");
        buildCodeTree(node.right, code + "1");
    }

    public void print() {
        // _printRecursive(root, "");
        for (char c : hashtable.keys()) {
            System.out.println(c + ": " + hashtable.get(c));
        }
    }

    @SuppressWarnings("unused")
    private void _printRecursive(HuffmanNode node, String code) {
        if (node == null) {
            return;
        }
        if (node.character != '\0') {
            System.out.println(node.character + ": " + code);
        }
        _printRecursive(node.left, code + "0");
        _printRecursive(node.right, code + "1");
    }

    public void encode(String text) {
        String result = "";
        for (char c : text.toCharArray()) {
            result += hashtable.get(c);
        }

        System.out.println(result);
    }
}
