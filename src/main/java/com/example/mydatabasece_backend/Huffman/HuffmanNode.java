package com.example.mydatabasece_backend.Huffman;

/**
 * Los nodos del arbol
 */
public class HuffmanNode implements Comparable<HuffmanNode> {
    char character;
    int frequency;
    HuffmanNode left;
    HuffmanNode right;

    /**
     * Saber los datos de ese nodo
     * @param character
     * @param frequency
     */
    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    public HuffmanNode(char character, int frequency, HuffmanNode left, HuffmanNode right) {
        this.character = character;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    /**
     * Es la hoja del arbol
     * @return
     */
    public boolean isLeaf() {
        return left == null && right == null;
    }

    /**
     * Compara las frecuencias
     * @param other the object to be compared.
     * @return
     */
    @Override
    public int compareTo(HuffmanNode other) {
        return this.frequency - other.frequency;
    }
}
