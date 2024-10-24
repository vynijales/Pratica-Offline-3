package services.Cache;

import models.HashTable.OpenAddressing;
import services.OS;

public class CacheHash {
    public OpenAddressing<Integer, OS> cache;

    // Construtores

    public CacheHash(int capacity) {
        this.cache = new OpenAddressing<>(capacity);
    }

    public CacheHash() {
        this(20);
    }

    // Métodos públicos que podem ser utilizados por qualquer classe

    /**
     * Método que percorre a cache e retornar a OS associada a ela, se não exisir
     * retorna null.
     * 
     * @return OS associada a uma chave ou null.
     */
    public OS get(Integer code) {
        OS os = cache.get(code);
        if (os != null) {
            return os;
        }
        return null;
    }

    /**
     * Método que adiciona uma OS na cache.
     * 
     * @param os OS a ser adicionada.
     */
    public void add(OS os) {
        if (os == null) {
            return;
        }
        if (cache.get(os.code) == null) {
            cache.put(os.code, os);
        }
    }

    /**
     * Método que atualiza uma OS na cache.
     * 
     * @param os OS a ser atualizada.
     */
    public void update(Integer code, OS os) {
        cache.update(code, os);

    }

    /**
     * Método que remove uma OS da cache.
     * 
     * @param os OS a ser removida.
     */

    public void remove(Integer key) {
        cache.remove(key);
    }

    public void print() {
        cache.print();
    }

}
