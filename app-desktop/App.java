import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class App {

    // =========================================================
    // CORES DO SISTEMA
    // =========================================================

    static final Color FUNDO = new Color(18, 22, 28);
    static final Color SIDEBAR = new Color(24, 29, 37);
    static final Color CARD = new Color(31, 37, 46);
    static final Color CARD_HOVER = new Color(39, 46, 57);
    static final Color TEXTO = new Color(235, 238, 242);
    static final Color TEXTO_SECUNDARIO = new Color(150, 158, 170);
    static final Color AZUL = new Color(52, 152, 219);
    static final Color VERDE = new Color(46, 204, 113);
    static final Color VERMELHO = new Color(231, 76, 60);
    static final Color AMARELO = new Color(241, 196, 15);

    // =========================================================
    // DADOS
    // =========================================================

    static String[] nomes = new String[100];
    static int[] idades = new int[100];
    static String[] prioridades = new String[100];

    static int quantidadePacientes = 0;
    static int quantidadeAtendimentos = 0;

    // =========================================================
    // PILHA
    // =========================================================

    static String[] historico = new String[100];
    static int topo = -1;

    // =========================================================
    // COMPONENTES PRINCIPAIS
    // =========================================================

    static JFrame janela;
    static JPanel conteudo;
    static JLabel tituloPagina;

    static JLabel totalPacientes;
    static JLabel totalAtendimentos;
    static JLabel totalUrgentes;

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> criarInterface());
    }

    // =========================================================
    // CRIAR INTERFACE
    // =========================================================

    public static void criarInterface() {

        janela = new JFrame(
                "Clinic System - Sistema de Atendimento"
        );

        janela.setSize(1100, 700);

        janela.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        janela.setLocationRelativeTo(null);

        janela.setLayout(
                new BorderLayout()
        );

        // MENU LATERAL
        janela.add(
                criarSidebar(),
                BorderLayout.WEST
        );

        // ÁREA PRINCIPAL
        conteudo = new JPanel(
                new BorderLayout()
        );

        conteudo.setBackground(FUNDO);

        janela.add(
                conteudo,
                BorderLayout.CENTER
        );

        mostrarDashboard();

        janela.setVisible(true);
    }

    // =========================================================
    // SIDEBAR
    // =========================================================

    public static JPanel criarSidebar() {

        JPanel sidebar = new JPanel();

        sidebar.setPreferredSize(
                new Dimension(230, 700)
        );

        sidebar.setBackground(SIDEBAR);

        sidebar.setLayout(
                new BorderLayout()
        );

        // LOGO
        JPanel logo = new JPanel(
                new GridLayout(2, 1)
        );

        logo.setBackground(SIDEBAR);

        logo.setBorder(
                new EmptyBorder(
                        30,
                        20,
                        20,
                        20
                )
        );

        JLabel nomeSistema = new JLabel(
                "🏥 CLINIC SYSTEM"
        );

        nomeSistema.setForeground(TEXTO);

        nomeSistema.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        JLabel status = new JLabel(
                "● SISTEMA ONLINE"
        );

        status.setForeground(VERDE);

        status.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        logo.add(nomeSistema);
        logo.add(status);

        sidebar.add(
                logo,
                BorderLayout.NORTH
        );

        // MENU
        JPanel menu = new JPanel();

        menu.setBackground(SIDEBAR);

        menu.setLayout(
                new GridLayout(
                        8,
                        1,
                        0,
                        8
                )
        );

        menu.setBorder(
                new EmptyBorder(
                        10,
                        15,
                        10,
                        15
                )
        );

        JButton dashboard =
                criarBotaoMenu(
                        "🏠  Dashboard"
                );

        JButton cadastrar =
                criarBotaoMenu(
                        "👤  Cadastrar"
                );

        JButton pacientes =
                criarBotaoMenu(
                        "📋  Pacientes"
                );

        JButton buscar =
                criarBotaoMenu(
                        "🔎  Buscar"
                );

        JButton atendimento =
                criarBotaoMenu(
                        "🩺  Atendimento"
                );

        JButton historico =
                criarBotaoMenu(
                        "📜  Histórico"
                );

        JButton ordenar =
                criarBotaoMenu(
                        "↕  Ordenar"
                );

        JButton sair =
                criarBotaoMenu(
                        "🚪  Sair"
                );

        menu.add(dashboard);
        menu.add(cadastrar);
        menu.add(pacientes);
        menu.add(buscar);
        menu.add(atendimento);
        menu.add(historico);
        menu.add(ordenar);
        menu.add(sair);

        sidebar.add(
                menu,
                BorderLayout.CENTER
        );

        // AÇÕES
        dashboard.addActionListener(
                e -> mostrarDashboard()
        );

        cadastrar.addActionListener(
                e -> abrirCadastro()
        );

        pacientes.addActionListener(
                e -> listarPacientes()
        );

        buscar.addActionListener(
                e -> buscarPaciente()
        );

        atendimento.addActionListener(
                e -> registrarAtendimento()
        );

        historico.addActionListener(
                e -> mostrarHistorico()
        );

        ordenar.addActionListener(
                e -> ordenarPacientes()
        );

        sair.addActionListener(
                e -> System.exit(0)
        );

        return sidebar;
    }

    // =========================================================
    // BOTÃO MENU
    // =========================================================

    public static JButton criarBotaoMenu(
            String texto
    ) {

        JButton botao =
                new JButton(texto);

        botao.setForeground(TEXTO);

        botao.setBackground(SIDEBAR);

        botao.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        botao.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        botao.setBorder(
                new EmptyBorder(
                        12,
                        15,
                        12,
                        10
                )
        );

        botao.setFocusPainted(false);

        botao.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        botao.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        botao.setBackground(
                                CARD_HOVER
                        );
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e
                    ) {

                        botao.setBackground(
                                SIDEBAR
                        );
                    }
                }
        );

        return botao;
    }

    // =========================================================
    // DASHBOARD
    // =========================================================

    public static void mostrarDashboard() {

        conteudo.removeAll();

        JPanel pagina =
                new JPanel(
                        new BorderLayout()
                );

        pagina.setBackground(FUNDO);

        pagina.setBorder(
                new EmptyBorder(
                        30,
                        35,
                        30,
                        35
                )
        );

        // CABEÇALHO
        JPanel cabecalho =
                new JPanel(
                        new BorderLayout()
                );

        cabecalho.setBackground(FUNDO);

        tituloPagina =
                new JLabel(
                        "Dashboard"
                );

        tituloPagina.setForeground(TEXTO);

        tituloPagina.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        JLabel subtitulo =
                new JLabel(
                        "Visão geral do sistema de atendimento"
                );

        subtitulo.setForeground(
                TEXTO_SECUNDARIO
        );

        subtitulo.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        JPanel textos =
                new JPanel(
                        new GridLayout(
                                2,
                                1
                        )
                );

        textos.setBackground(FUNDO);

        textos.add(tituloPagina);
        textos.add(subtitulo);

        cabecalho.add(
                textos,
                BorderLayout.WEST
        );

        pagina.add(
                cabecalho,
                BorderLayout.NORTH
        );

        // CENTRO
        JPanel centro =
                new JPanel();

        centro.setBackground(FUNDO);

        centro.setLayout(
                new BoxLayout(
                        centro,
                        BoxLayout.Y_AXIS
                )
        );

        centro.add(
                Box.createVerticalStrut(30)
        );

        // CARDS
        JPanel cards =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                20,
                                0
                        )
                );

        cards.setBackground(FUNDO);

        totalPacientes =
                new JLabel(
                        String.valueOf(
                                quantidadePacientes
                        )
                );

        totalAtendimentos =
                new JLabel(
                        String.valueOf(
                                quantidadeAtendimentos
                        )
                );

        totalUrgentes =
                new JLabel(
                        String.valueOf(
                                contarUrgentes()
                        )
                );

        cards.add(
                criarCard(
                        "👤 PACIENTES",
                        totalPacientes,
                        AZUL
                )
        );

        cards.add(
                criarCard(
                        "🩺 ATENDIMENTOS",
                        totalAtendimentos,
                        VERDE
                )
        );

        cards.add(
                criarCard(
                        "🚨 URGENTES",
                        totalUrgentes,
                        VERMELHO
                )
        );

        centro.add(cards);

        centro.add(
                Box.createVerticalStrut(30)
        );

        // ÚLTIMA OPERAÇÃO
        JPanel atividade =
                criarPainelCard();

        atividade.setLayout(
                new BorderLayout()
        );

        JLabel titulo =
                new JLabel(
                        "📜 Última atividade"
                );

        titulo.setForeground(TEXTO);

        titulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        atividade.add(
                titulo,
                BorderLayout.NORTH
        );

        String ultima =
                consultarTopo();

        if (ultima == null) {

            ultima =
                    "Nenhuma operação registrada.";
        }

        JLabel atividadeTexto =
                new JLabel(
                        "<html><br>"
                                + ultima
                                + "</html>"
                );

        atividadeTexto.setForeground(
                TEXTO_SECUNDARIO
        );

        atividadeTexto.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        atividade.add(
                atividadeTexto,
                BorderLayout.CENTER
        );

        centro.add(atividade);

        pagina.add(
                centro,
                BorderLayout.CENTER
        );

        conteudo.add(
                pagina,
                BorderLayout.CENTER
        );

        atualizarTela();
    }

    // =========================================================
    // CARD
    // =========================================================

    public static JPanel criarCard(
            String titulo,
            JLabel valor,
            Color destaque
    ) {

        JPanel card =
                new JPanel(
                        new BorderLayout()
                );

        card.setBackground(CARD);

        card.setBorder(
                new EmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        JLabel label =
                new JLabel(titulo);

        label.setForeground(
                TEXTO_SECUNDARIO
        );

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        valor.setForeground(
                destaque
        );

        valor.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        36
                )
        );

        card.add(
                label,
                BorderLayout.NORTH
        );

        card.add(
                valor,
                BorderLayout.CENTER
        );

        return card;
    }

    // =========================================================
    // PAINEL CARD
    // =========================================================

    public static JPanel criarPainelCard() {

        JPanel painel =
                new JPanel();

        painel.setBackground(CARD);

        painel.setBorder(
                new EmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        return painel;
    }

    // =========================================================
    // CADASTRO
    // =========================================================

    public static void abrirCadastro() {

        JTextField nome =
                new JTextField();

        JTextField idade =
                new JTextField();

        JComboBox<String> prioridade =
                new JComboBox<>(
                        new String[]{
                                "NORMAL",
                                "URGENTE"
                        }
                );

        JPanel painel =
                new JPanel(
                        new GridLayout(
                                3,
                                2,
                                10,
                                15
                        )
                );

        painel.setBorder(
                new EmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        painel.add(
                new JLabel("Nome:")
        );

        painel.add(nome);

        painel.add(
                new JLabel("Idade:")
        );

        painel.add(idade);

        painel.add(
                new JLabel("Prioridade:")
        );

        painel.add(prioridade);

        int resultado =
                JOptionPane.showConfirmDialog(
                        janela,
                        painel,
                        "Cadastrar paciente",
                        JOptionPane.OK_CANCEL_OPTION
                );

        if (
                resultado !=
                        JOptionPane.OK_OPTION
        ) {

            return;
        }

        String nomeTexto =
                nome.getText().trim();

        if (
                nomeTexto.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    janela,
                    "Digite o nome do paciente."
            );

            return;
        }

        int idadeNumero;

        try {

            idadeNumero =
                    Integer.parseInt(
                            idade.getText()
                    );

        } catch (
                NumberFormatException erro
        ) {

            JOptionPane.showMessageDialog(
                    janela,
                    "Digite uma idade válida."
            );

            return;
        }

        if (
                quantidadePacientes >=
                        nomes.length
        ) {

            JOptionPane.showMessageDialog(
                    janela,
                    "Limite de pacientes atingido."
            );

            return;
        }

        nomes[
                quantidadePacientes
        ] = nomeTexto;

        idades[
                quantidadePacientes
        ] = idadeNumero;

        prioridades[
                quantidadePacientes
        ] =
                (String)
                        prioridade
                                .getSelectedItem();

        quantidadePacientes++;

        empilhar(
                "Paciente cadastrado: "
                        + nomeTexto
        );

        JOptionPane.showMessageDialog(
                janela,
                "Paciente cadastrado com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
        );

        mostrarDashboard();
    }

    // =========================================================
    // LISTAR
    // =========================================================

    public static void listarPacientes() {

        if (
                quantidadePacientes == 0
        ) {

            JOptionPane.showMessageDialog(
                    janela,
                    "Nenhum paciente cadastrado."
            );

            return;
        }

        String[] colunas = {
                "ID",
                "NOME",
                "IDADE",
                "PRIORIDADE"
        };

        DefaultTableModel modelo =
                new DefaultTableModel(
                        colunas,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {

                        return false;
                    }
                };

        for (
                int i = 0;
                i < quantidadePacientes;
                i++
        ) {

            modelo.addRow(
                    new Object[]{
                            i + 1,
                            nomes[i],
                            idades[i],
                            prioridades[i]
                    }
            );
        }

        JTable tabela =
                new JTable(modelo);

        tabela.setRowHeight(30);

        tabela.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        tabela.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                14
                        )
                );

        JScrollPane scroll =
                new JScrollPane(tabela);

        scroll.setPreferredSize(
                new Dimension(
                        750,
                        450
                )
        );

        JOptionPane.showMessageDialog(
                janela,
                scroll,
                "Pacientes cadastrados",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =========================================================
    // BUSCAR
    // =========================================================

    public static void buscarPaciente() {

        if (
                quantidadePacientes == 0
        ) {

            JOptionPane.showMessageDialog(
                    janela,
                    "Nenhum paciente cadastrado."
            );

            return;
        }

        String busca =
                JOptionPane.showInputDialog(
                        janela,
                        "Digite o nome:",
                        "Buscar paciente",
                        JOptionPane.QUESTION_MESSAGE
                );

        if (
                busca == null ||
                busca.trim().isEmpty()
        ) {

            return;
        }

        for (
                int i = 0;
                i < quantidadePacientes;
                i++
        ) {

            if (
                    nomes[i]
                            .equalsIgnoreCase(
                                    busca.trim()
                            )
            ) {

                JOptionPane.showMessageDialog(
                        janela,
                        "PACIENTE ENCONTRADO\n\n"
                                + "ID: "
                                + (i + 1)
                                + "\nNome: "
                                + nomes[i]
                                + "\nIdade: "
                                + idades[i]
                                + "\nPrioridade: "
                                + prioridades[i]
                );

                return;
            }
        }

        JOptionPane.showMessageDialog(
                janela,
                "Paciente não encontrado."
        );
    }

    // =========================================================
    // ORDENAR
    // =========================================================

    public static void ordenarPacientes() {

        if (
                quantidadePacientes < 2
        ) {

            JOptionPane.showMessageDialog(
                    janela,
                    "Cadastre pelo menos 2 pacientes."
            );

            return;
        }

        // BUBBLE SORT
        for (
                int i = 0;
                i < quantidadePacientes - 1;
                i++
        ) {

            for (
                    int j = 0;
                    j <
                            quantidadePacientes
                                    - 1
                                    - i;
                    j++
            ) {

                if (
                        nomes[j]
                                .compareToIgnoreCase(
                                        nomes[j + 1]
                                ) > 0
                ) {

                    String nomeTemp =
                            nomes[j];

                    nomes[j] =
                            nomes[j + 1];

                    nomes[j + 1] =
                            nomeTemp;

                    int idadeTemp =
                            idades[j];

                    idades[j] =
                            idades[j + 1];

                    idades[j + 1] =
                            idadeTemp;

                    String prioridadeTemp =
                            prioridades[j];

                    prioridades[j] =
                            prioridades[j + 1];

                    prioridades[j + 1] =
                            prioridadeTemp;
                }
            }
        }

        empilhar(
                "Pacientes ordenados por nome"
        );

        JOptionPane.showMessageDialog(
                janela,
                "Pacientes ordenados com sucesso!"
        );

        listarPacientes();
    }

    // =========================================================
    // ATENDIMENTO
    // =========================================================

    public static void registrarAtendimento() {

        if (
                quantidadePacientes == 0
        ) {

            JOptionPane.showMessageDialog(
                    janela,
                    "Nenhum paciente cadastrado."
            );

            return;
        }

        String idTexto =
                JOptionPane.showInputDialog(
                        janela,
                        "Digite o ID do paciente:",
                        "Registrar atendimento",
                        JOptionPane.QUESTION_MESSAGE
                );

        if (
                idTexto == null
        ) {

            return;
        }

        int id;

        try {

            id =
                    Integer.parseInt(
                            idTexto
                    );

        } catch (
                NumberFormatException erro
        ) {

            JOptionPane.showMessageDialog(
                    janela,
                    "ID inválido."
            );

            return;
        }

        if (
                id < 1 ||
                id > quantidadePacientes
        ) {

            JOptionPane.showMessageDialog(
                    janela,
                    "Paciente não encontrado."
            );

            return;
        }

        String nome =
                nomes[id - 1];

        quantidadeAtendimentos++;

        empilhar(
                "Atendimento: "
                        + nome
        );

        JOptionPane.showMessageDialog(
                janela,
                "ATENDIMENTO REGISTRADO\n\n"
                        + "Paciente: "
                        + nome
                        + "\nID: "
                        + id
        );

        mostrarDashboard();
    }

    // =========================================================
    // PILHA - PUSH
    // =========================================================

    public static void empilhar(
            String operacao
    ) {

        if (
                topo >=
                        historico.length - 1
        ) {

            return;
        }

        topo++;

        historico[topo] =
                operacao;
    }

    // =========================================================
    // PILHA - TOP
    // =========================================================

    public static String consultarTopo() {

        if (
                topo == -1
        ) {

            return null;
        }

        return historico[topo];
    }

    // =========================================================
    // PILHA - POP
    // =========================================================

    public static String desempilhar() {

        if (
                topo == -1
        ) {

            return null;
        }

        String operacao =
                historico[topo];

        historico[topo] =
                null;

        topo--;

        return operacao;
    }

    // =========================================================
    // HISTÓRICO
    // =========================================================

    public static void mostrarHistorico() {

        if (
                topo == -1
        ) {

            JOptionPane.showMessageDialog(
                    janela,
                    "Nenhuma operação registrada."
            );

            return;
        }

        StringBuilder texto =
                new StringBuilder();

        texto.append(
                "HISTÓRICO DE OPERAÇÕES\n"
        );

        texto.append(
                "================================\n\n"
        );

        for (
                int i = topo;
                i >= 0;
                i--
        ) {

            texto.append(
                    "• "
            );

            texto.append(
                    historico[i]
            );

            texto.append("\n");
        }

        JTextArea area =
                new JTextArea(
                        texto.toString()
                );

        area.setEditable(false);

        area.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );

        JScrollPane scroll =
                new JScrollPane(area);

        scroll.setPreferredSize(
                new Dimension(
                        650,
                        400
                )
        );

        JOptionPane.showMessageDialog(
                janela,
                scroll,
                "Histórico",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =========================================================
    // DESFAZER
    // =========================================================

    public static void desfazerOperacao() {

        String operacao =
                desempilhar();

        if (
                operacao == null
        ) {

            JOptionPane.showMessageDialog(
                    janela,
                    "Nenhuma operação para desfazer."
            );

            return;
        }

        JOptionPane.showMessageDialog(
                janela,
                "Operação removida da PILHA:\n\n"
                        + operacao
        );

        mostrarDashboard();
    }

    // =========================================================
    // CONTAR URGENTES
    // =========================================================

    public static int contarUrgentes() {

        int contador = 0;

        for (
                int i = 0;
                i < quantidadePacientes;
                i++
        ) {

            if (
                    "URGENTE".equals(
                            prioridades[i]
                    )
            ) {

                contador++;
            }
        }

        return contador;
    }

    // =========================================================
    // ATUALIZAR TELA
    // =========================================================

    public static void atualizarTela() {

        if (
                totalPacientes != null
        ) {

            totalPacientes.setText(
                    String.valueOf(
                            quantidadePacientes
                    )
            );
        }

        if (
                totalAtendimentos != null
        ) {

            totalAtendimentos.setText(
                    String.valueOf(
                            quantidadeAtendimentos
                    )
            );
        }

        if (
                totalUrgentes != null
        ) {

            totalUrgentes.setText(
                    String.valueOf(
                            contarUrgentes()
                    )
            );
        }

        conteudo.revalidate();
        conteudo.repaint();
    }
}