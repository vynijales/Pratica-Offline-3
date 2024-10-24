package services;

import services.Cache.CacheHash;

import java.io.IOException;
import java.util.Scanner;

// Testar HashTable
public class Cliente {
    private Servidor servidor;
    private CacheHash cache;
    private Scanner scanner;

    public Cliente(Servidor servidor) {
        this.servidor = servidor;
        this.cache = new CacheHash();
        this.scanner = new Scanner(System.in);

    }

    public void get() {
        System.out.print("Digite o código da Ordem de Serviço a ser buscada: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        OS os = cache.get(codigo);
        if (os != null) {
            System.out.println("Ordem de Serviço encontrada na cache: " + os);
            return;
        } else {
            System.out.println("Ordem de Serviço não encontrada na cache.");
        }

        os = servidor.get(codigo);
        if (os != null) {
            cache.add(os);
            System.out.println("Ordem de Serviço encontrada no servidor: " + os);
            return;
        } else {
            System.out.println("Ordem de Serviço não encontrada no servidor.");
        }
    }

    public void add() throws IOException {
        System.out.print("Digite o código da Ordem de Serviço a ser adicionado: ");
        Integer code = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        System.out.println();

        if (isInCache(code)) {
            System.out.println("Ordem de serviço encontrada na cache.");
            return;
        }

        if (isInServidor(code)) {
            OS os = servidor.get(code);
            cache.add(os);
            System.out.println("Ordem de serviço encontrada no servidor, adicionada na cache.");
            return;
        }

        System.out.print("Digite o nome da Ordem de Serviço: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a descrição da Ordem de Serviço: ");
        String descricao = scanner.nextLine();

        OS os = new OS(code, nome, descricao);
        cache.add(os);
        servidor.add(os);
        System.out.println("Ordem de serviço adicionada no servidor e na cache.");
    }

    public void update() throws IOException {
        System.out.print("Digite o código da Ordem de Serviço a ser alterada: ");
        Integer codigo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        if (!isInCache(codigo) && !isInServidor(codigo)) {
            System.out.println("Ordem de Serviço não existe.");
            return;
        }

        System.out.print("Digite o novo nome da Ordem de Serviço: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a nova descrição da Ordem de Serviço: ");
        String descricao = scanner.nextLine();

        System.out.println("A data e hora da Ordem de Serviço serão atualizadas para a hora atual.");

        OS osNova = new OS(codigo, nome, descricao);
        servidor.update(codigo, osNova);
        cache.remove(codigo);
        cache.add(servidor.get(codigo));
        System.out.println("Ordem de Serviço alterada.");
    }

    public void remove() throws IOException {
        System.out.print("Digite o código da Ordem de Serviço a ser removida:");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        if (!isInCache(codigo) && !isInServidor(codigo)) {
            System.out.println("Ordem de Serviço não encontrada.");
            return;
        }

        servidor.remove(codigo);
        cache.remove(codigo);
        System.out.println("Ordem de Serviço removida.");
    }

    private boolean isInCache(int codigo) {
        return cache.get(codigo) != null;
    }

    private boolean isInServidor(int codigo) {
        return servidor.get(codigo) != null;
    }

    public void print() {
        cache.print();
    }
}
