package services;

import java.io.FileWriter;
import java.io.IOException;

// import modelo.ArvoreAVL;
import models.HashTable.ChainedHashTable;

public class Servidor {
    // private ArvoreAVL bancoDeDados;
    private ChainedHashTable<OS> hashtable;
    private FileWriter arquivoLog;

    // ----------------------------------------------------------------------------------------------------------
    // Construtor
    public Servidor() throws IOException {
        // bancoDeDados = new ArvoreAVL();
        hashtable = new ChainedHashTable<OS>(11); // Tamanho inicial da tabela hash
        arquivoLog = new FileWriter("servidor_log.txt", true);
    }

    // ----------------------------------------------------------------------------------------------------------
    // CRUD do servidor
    public OS get(Integer code) {
        OS os = hashtable.get(code);
        return os;
    }

    public void add(OS os) throws IOException {
        hashtable.add(os.code, os); // Insere a ordem de serviço no banco de dados
        registrarOperacao("ADD", os.code); // Registra no log
    }

    public void remove(Integer codigo) throws IOException {
        if (this.get(codigo) == null) {
            return;
        }
        registrarOperacao("REMOVE", codigo);
        hashtable.remove(codigo);
    }

    public void update(Integer codigo, OS novaOs) throws IOException {
        if (hashtable.get(codigo) == null) {
            System.out.println("Ordem de Serviço não encontrada no servidor.");
            return;
        }
        hashtable.update(codigo, novaOs);
        registrarOperacao("UPDATE", codigo);
    }

    public void print() {
        System.out.println("===============SERVER================");
        hashtable.print();
    }

    // ----------------------------------------------------------------------------------------------------------
    // Métodos de registro e impressão

    private void registrarOperacao(String operacao, int codigo) throws IOException {
        OS os = hashtable.get(codigo);

        String log = operacao + " - " + os + "\n";

        arquivoLog.write(log);
        arquivoLog.flush();
    }

    public void fechar() throws IOException {
        if (arquivoLog != null) {
            arquivoLog.close();
        }
    }

    public String quantidadeOrdensServico() {
        return "Quantidade de Ordens de Serviço: " + hashtable.capacity;
    }

}
