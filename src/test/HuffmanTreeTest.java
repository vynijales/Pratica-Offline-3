package test;

import models.HuffmanTree.HuffmanTree;

public class HuffmanTreeTest {
    public static void main(String[] args) {
        Character[] chars = { 'a', 'b', 'c', 'd', 'e', 'f' };
        int[] frequencies = { 5, 9, 12, 13, 16, 45 };

        HuffmanTree tree = new HuffmanTree(chars, frequencies);
        tree.buildCodeTree(tree.root, "");
        tree.print();
    }
}
