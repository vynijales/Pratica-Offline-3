package services.Message;

import java.util.HashMap;
import java.util.Map;

import models.HuffmanTree.HuffmanTree;

public class Request extends Message {
    public Request(String text) {

        String result = "";

        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        Character[] chars = new Character[frequencyMap.size()];
        Integer[] freqs = new Integer[frequencyMap.size()];

        int index = 0;
        System.out.println("_Message: Frequência dos caracteres:");
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());

            chars[index] = entry.getKey();
            freqs[index] = entry.getValue();
            index++;
        }

        this.tree = new HuffmanTree(chars, freqs);

        System.out.println("_OpenAddressing: Frequência dos caracteres:");

        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < chars.length; j++) {
                if (text.charAt(i) == chars[j]) {
                    result += tree.getEncoded(chars[j]);
                    result += "";
                }
            }
        }
        result += "\n";
        this.output = result;

    }

    @Override
    public String toString() {
        return this.output;
    }
}
