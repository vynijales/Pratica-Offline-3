package models.HashTable;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class OpenAddressing<T> {
    int capacity;
    int current_size;
    HashNode[] table;

    public OpenAddressing(int capacity) {
        this.table = new HashNode[capacity];
        this.current_size = 0;
        this.capacity = capacity;
    }

    // Métodos públicos que podem ser utilizados por qualquer classe

    public T get(int key) {
        HashNode<T> node = searchNode(key);
        if (node != null) {
            System.out.println("Elemento encontrado.");
            return node.element;
        }
        System.out.println("Elemento não encontrado.");
        return null;
    }

    public void add(int code, T new_element) {
        int index;
        int num_attempts = 0;

        HashNode<T> new_node = new HashNode<T>();
        new_node.element = new_element;

        do {
            num_attempts++;
            index = linearAttempt(code, num_attempts);
            HashNode node = table[index];

            if (node != null && node.code == code) {
                System.out.println("Elemento já existe na tabela.");
                return;
            }

        } while (num_attempts < capacity && table[index] != null);

        if (num_attempts >= capacity) {
            System.out.println("Quantidade de tentativas excedida.");
            return;
        }

        table[index] = new HashNode<>();
        table[index].element = new_element;
        current_size++;
    }

    public void update(int key, T element) {
        HashNode node = searchNode(key);
        if (node != null) {
            node.element = element;
            System.out.println("Elemento atualizado.");
        }
    }

    public T remove(int key) {
        HashNode node = searchNode(key);
        T element = null;
        if (node != null) {
            element = (T) node.element;
            node.element = null;
            table[hash(key)] = null;
            current_size--;
            System.out.println("Elemento removido.");
        }
        return element;
    }

    public void print() {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                System.out.println("Index: " + i + " da tabela hash: " + table[i].element);
            }
        }
    }

    public boolean isFull() {
        return current_size == capacity;
    }

    public boolean isEmpty() {
        return current_size == 0;
    }

    // Métodos privados que só podem ser utilizados por esta classe

    private int hash(int code) {
        final double goldenNumber = (Math.sqrt(5) - 1) / 2;
        return (int) (capacity * ((code * goldenNumber) % 1));
    }

    private int linearAttempt(int code, int num_attempts) {
        int index = (hash(code) + num_attempts) % capacity;
        return index;
    }

    // Métodos de acesso
    private HashNode<T> searchNode(int key) {
        int index;
        int num_attempts = 0;

        do {
            index = linearAttempt(key, num_attempts);
            if (table[index].code == key) {
                return table[index];
            }

            num_attempts++;

        } while (num_attempts < capacity && table[index] != null);

        return null;
    }

}
