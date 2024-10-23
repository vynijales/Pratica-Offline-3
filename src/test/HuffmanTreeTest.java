package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.HuffmanTree.HuffmanTree;

public class HuffmanTreeTest {
    public static void main(String[] args) {
        String input = "aaaaabbbbbbbbbccccccccccccdddddddddddddeeeeeeeeeeeeeeeefffffffffffffffffffffffffffffffffffffffffffff";
        List<Character> caracteres = new ArrayList<>();
        List<Integer> frequencias = new ArrayList<>();

        Map<Character, Integer> mapaFrequencia = new HashMap<>();

        // Contar a frequência de cada caractere
        for (char c : input.toCharArray()) {
            mapaFrequencia.put(c, mapaFrequencia.getOrDefault(c, 0) + 1);
        }

        // Preencher as listas de caracteres e frequências
        for (Map.Entry<Character, Integer> entry : mapaFrequencia.entrySet()) {
            caracteres.add(entry.getKey());
            frequencias.add(entry.getValue());
        }

        // Converter listas para arrays
        Character[] charsArray = caracteres.toArray(new Character[0]);
        Integer[] freqArray = frequencias.toArray(new Integer[0]);

        HuffmanTree tree = new HuffmanTree(charsArray, freqArray);
        tree.buildCodeTree(tree.root, "");
        tree.print();
    }
}
