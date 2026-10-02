import estruturas.Pilha;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import modelos.Paciente;
import modelos.Prioridade;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    // Arquivo onde os pacientes serão salvos
    static final String ARQUIVO = "pacientes.txt";

    // Array que armazena os pacientes
    static Paciente[] pacientes = new Paciente[100];

    // Quantidade atual de pacientes cadastrados
    static int quantidadePacientes = 0;

    // Pilha que armazena o histórico de operações
    static Pilha<String> historico = new Pilha<>(100);

    public static void main(String[] args) {

        // Carrega os pacientes salvos anteriormente
        carregarPacientes();

        int opcao;

        do {

            mostrarMenu();

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    cadastrarPaciente();
                    break;

                case 2:
                    listarPacientes();
                    break;

                case 3:
                    buscarPaciente();
                    break;

                case 4:
                    ordenarPacientes();
                    break;

                case 5:
                    registrarAtendimento();
                    break;

                case 6:
                    desfazerOperacao();
                    break;

                case 7:
                    verUltimaOperacao();
                    break;

                case 0:
                    salvarPacientes();
                    System.out.println("\nSistema encerrado.");
                    break;

                default:
                    System.out.println("\nOpção inválida!");
            }

        } while (opcao != 0);

        scanner.close();
    }

    public static void mostrarMenu() {

        System.out.println("\n================================");
        System.out.println("   SISTEMA DE ATENDIMENTO");
        System.out.println("================================");
        System.out.println("1 - Cadastrar paciente");
        System.out.println("2 - Listar pacientes");
        System.out.println("3 - Buscar paciente");
        System.out.println("4 - Ordenar pacientes");
        System.out.println("5 - Registrar atendimento");
        System.out.println("6 - Desfazer última operação");
        System.out.println("7 - Ver última operação");
        System.out.println("0 - Sair");
        System.out.println("================================");
        System.out.print("Escolha uma opção: ");
    }

    public static void cadastrarPaciente() {

        if (quantidadePacientes >= pacientes.length) {
            System.out.println("\nLimite de pacientes atingido!");
            return;
        }

        System.out.println("\n--- CADASTRO DE PACIENTE ---");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nPrioridade:");
        System.out.println("1 - Normal");
        System.out.println("2 - Urgente");
        System.out.print("Escolha: ");

        int opcaoPrioridade = scanner.nextInt();
        scanner.nextLine();

        Prioridade prioridade;

        if (opcaoPrioridade == 2) {
            prioridade = Prioridade.URGENTE;
        } else {
            prioridade = Prioridade.NORMAL;
        }

        int id = quantidadePacientes + 1;

        Paciente paciente = new Paciente(
                id,
                nome,
                idade,
                prioridade
        );

        pacientes[quantidadePacientes] = paciente;
        quantidadePacientes++;

        historico.empilhar("CADASTRO: " + nome);

        // Salva automaticamente
        salvarPacientes();

        System.out.println("\nPaciente cadastrado com sucesso!");
    }

    public static void listarPacientes() {

        System.out.println("\n--- LISTA DE PACIENTES ---");

        if (quantidadePacientes == 0) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }

        for (int i = 0; i < quantidadePacientes; i++) {
            System.out.println(pacientes[i]);
        }
    }

    public static void buscarPaciente() {

        System.out.println("\n--- BUSCAR PACIENTE ---");

        System.out.print("Digite o nome: ");
        String nomeBusca = scanner.nextLine();

        boolean encontrado = false;

        for (int i = 0; i < quantidadePacientes; i++) {

            if (pacientes[i].getNome().equalsIgnoreCase(nomeBusca)) {

                System.out.println("\nPaciente encontrado:");
                System.out.println(pacientes[i]);

                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("\nPaciente não encontrado.");
        }
    }

    public static void ordenarPacientes() {

        System.out.println("\n--- ORDENANDO PACIENTES ---");

        for (int i = 0; i < quantidadePacientes - 1; i++) {

            for (int j = 0; j < quantidadePacientes - 1 - i; j++) {

                if (pacientes[j].getNome()
                        .compareToIgnoreCase(pacientes[j + 1].getNome()) > 0) {

                    Paciente temp = pacientes[j];

                    pacientes[j] = pacientes[j + 1];

                    pacientes[j + 1] = temp;
                }
            }
        }

        // Salva a nova ordem
        salvarPacientes();

        System.out.println("Pacientes ordenados por nome!");
    }

    public static void registrarAtendimento() {

        System.out.println("\n--- REGISTRAR ATENDIMENTO ---");

        if (quantidadePacientes == 0) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }

        listarPacientes();

        System.out.print("\nDigite o ID do paciente atendido: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Paciente paciente = encontrarPacientePorId(id);

        if (paciente == null) {
            System.out.println("Paciente não encontrado.");
            return;
        }

        historico.empilhar("ATENDIMENTO: " + paciente.getNome());

        System.out.println(
                "\nAtendimento registrado para "
                + paciente.getNome()
        );
    }

    public static Paciente encontrarPacientePorId(int id) {

        for (int i = 0; i < quantidadePacientes; i++) {

            if (pacientes[i].getId() == id) {
                return pacientes[i];
            }
        }

        return null;
    }

    public static void desfazerOperacao() {

        String operacao = historico.desempilhar();

        if (operacao == null) {
            System.out.println("\nNão existem operações para desfazer.");
            return;
        }

        System.out.println("\nÚltima operação removida:");
        System.out.println(operacao);
    }

    public static void verUltimaOperacao() {

        String operacao = historico.topo();

        if (operacao == null) {
            System.out.println("\nNenhuma operação registrada.");
            return;
        }

        System.out.println("\nÚltima operação:");
        System.out.println(operacao);
    }

    // ============================================================
    // SALVAR PACIENTES
    // ============================================================

    public static void salvarPacientes() {

        try {

            BufferedWriter escritor = new BufferedWriter(
                    new FileWriter(ARQUIVO)
            );

            for (int i = 0; i < quantidadePacientes; i++) {

                Paciente paciente = pacientes[i];

                escritor.write(
                        paciente.getId()
                        + ";"
                        + paciente.getNome()
                        + ";"
                        + paciente.getIdade()
                        + ";"
                        + paciente.getPrioridade()
                );

                escritor.newLine();
            }

            escritor.close();

        } catch (IOException e) {

            System.out.println(
                    "\nErro ao salvar pacientes: "
                    + e.getMessage()
            );
        }
    }

    // ============================================================
    // CARREGAR PACIENTES
    // ============================================================

    public static void carregarPacientes() {

        try {

            BufferedReader leitor = new BufferedReader(
                    new FileReader(ARQUIVO)
            );

            String linha;

            while ((linha = leitor.readLine()) != null) {

                if (quantidadePacientes >= pacientes.length) {
                    break;
                }

                String[] dados = linha.split(";");

                if (dados.length == 4) {

                    int id = Integer.parseInt(dados[0]);
                    String nome = dados[1];
                    int idade = Integer.parseInt(dados[2]);

                    Prioridade prioridade =
                            Prioridade.valueOf(dados[3]);

                    pacientes[quantidadePacientes] =
                            new Paciente(
                                    id,
                                    nome,
                                    idade,
                                    prioridade
                            );

                    quantidadePacientes++;
                }
            }

            leitor.close();

            if (quantidadePacientes > 0) {
                System.out.println(
                        quantidadePacientes
                        + " paciente(s) carregado(s)."
                );
            }

        } catch (IOException e) {

            // Se o arquivo ainda não existir,
            // simplesmente começa sem pacientes.
            if (!e.getMessage().contains("não encontrado")) {
                System.out.println(
                        "\nAviso ao carregar pacientes: "
                        + e.getMessage()
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "\nErro ao carregar pacientes: "
                    + e.getMessage()
            );
        }
    }
}