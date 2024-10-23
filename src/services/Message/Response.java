package services.Message;

import models.HuffmanTree.HuffmanNode;
import models.HuffmanTree.HuffmanTree;

public class Response extends Message {

    public Response(HuffmanTree tree, String encodedMessage) {
        this.tree = tree;

        String result = "";

        HuffmanNode current = tree.root;

        for (int i = 0; i < encodedMessage.length(); i++) {
            if (encodedMessage.charAt(i) == '0') {
                current = current.left;
            } else {
                current = current.right;
            }

            if (current.left == null && current.right == null) {
                result += current.character;
                current = tree.root;
            }
        }

        this.output = result;
    }

    public Response(HuffmanTree tree, Message message) {
        this(tree, message.output);
    }

    @Override
    public String toString() {
        return this.output;
    }

}
