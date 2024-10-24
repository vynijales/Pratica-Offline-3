import java.io.IOException;
import java.util.Scanner;

import services.Cliente;
import services.OS;
import services.Servidor;
import utils.format;

public class Main {
    public static void main(String[] args) {
        try {
            Servidor servidor = new Servidor();

            // Adiciona 100 ordens de serviço na base de dados
            for (int i = 1; i <= 70; i++) {
                OS os = new OS(i, "NAME " + format.formatCode(i), "DESCRIPTION " + format.formatCode(i));
                servidor.add(os);
            }

            Cliente cliente = new Cliente(servidor);

            Scanner scanner = new Scanner(System.in);

            System.out.println("\033[2J\033[1;1H");

            while (true) {
                System.out.println("===============CACHE=================");
                cliente.print();
                System.out.println("================MENU=================");

                System.out.println("Escolha uma opção:");
                System.out.println("1. Buscar Ordem de Serviço");
                System.out.println("2. Cadastrar Ordem de Serviço");
                System.out.println("3. Alterar Ordem de Serviço");
                System.out.println("4. Remover Ordem de Serviço");
                System.out.println("5. Listar Ordens de Serviço");
                System.out.println("6. Quantidade de Ordens de Serviço");
                System.out.println("0. Sair");
                System.out.println("7. Testes de performance");
                System.out.println("=====================================");
                System.out.print("Insira uma opção: ");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                switch (opcao) {
                    case 1:
                        cliente.get();
                        break;
                    case 2:
                        cliente.add();
                        break;
                    case 3:
                        cliente.update();
                        break;
                    case 4:
                        cliente.remove();
                        break;
                    case 5:
                        servidor.print();
                        break;

                    case 6:
                        System.out.println(servidor.quantidadeOrdensServico());
                        break;

                    case 0:
                        servidor.fechar();
                        scanner.close();
                        return;
                    case 7:
                        int teste;
                        do {
                            System.out.println("================TESTE================");
                            System.out.println("1. Realizar 3 consultas.");
                            System.out.println("2. Uma listagem.");
                            System.out.println("3. Realizar dois cadastros seguidos de uma listagem.");
                            System.out.println("4. Realizar duas remoções seguidas de uma listagem.");
                            System.out.println("0. Voltar para o menu principal.");
                            System.out.println("=====================================");
                            System.out.print("Insira uma opção: ");

                            teste = scanner.nextInt();

                            switch (teste) {
                                case 1:
                                    cliente.get();
                                    cliente.get();
                                    cliente.get();
                                    break;
                                case 2:
                                    servidor.print();
                                    break;
                                case 3:
                                    cliente.add();
                                    cliente.add();
                                    servidor.print();
                                    break;
                                case 4:
                                    cliente.remove();
                                    cliente.remove();
                                    servidor.print();
                                    break;
                                case 0:
                                    break;
                                default:
                                    System.out.println("Opção inválida.");
                            }
                        } while (teste != 0);
                    default:
                        System.out.println("Opção inválida.");
                }

                System.out.println("=====================================");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
