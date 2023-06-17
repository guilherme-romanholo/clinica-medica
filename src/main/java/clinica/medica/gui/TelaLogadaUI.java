package clinica.medica.gui;

import clinica.medica.usuarios.Medico;
import clinica.medica.usuarios.Paciente;
import clinica.medica.usuarios.Usuario;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import static clinica.medica.gui.LoginUI.frame;
import com.toedter.calendar.JCalendar;

public class TelaLogadaUI extends JFrame implements ActionListener {
    private String ultimaTela, telaAtual;
    private JPanel menuPanel;
    private JPanel headerPanel;
    private JPanel contentPanel;
    private CardLayout cardLayout;

    /*
    public TelaLogadaUI(Usuario user) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tela Logada");
        setSize(1200, 800);

        menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());
        menuPanel.setBackground(Color.decode("#98f8c7"));

        JPanel menuButtonsPanel = botoesNavegacao(user);
        menuButtonsPanel.setBackground(Color.decode("#98f8c7"));
        menuPanel.add(menuButtonsPanel);

        headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));

        try {
            BufferedImage logo = ImageIO.read(LoginUI.class.getResourceAsStream("/images/logo.png"));
            ImageIcon logoIcon = new ImageIcon(logo);
            JLabel logoLabel = new JLabel(logoIcon);
            frame.add(logoLabel, BorderLayout.EAST);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel nomeClinicaLabel = RecursosUI.criaImagemLabel("/images/logo.png", 150, 100);
        nomeClinicaLabel.setPreferredSize(new Dimension(150, 100));

        nomeClinicaLabel.setForeground(Color.WHITE);
        nomeClinicaLabel.setFont(new Font("Roboto", Font.BOLD, 16));

        headerPanel.add(nomeClinicaLabel);

        criaPainelInfos(user);

        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);

        criaPaineisConteudo(user);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(menuPanel, BorderLayout.WEST);
        container.add(headerPanel, BorderLayout.NORTH);
        container.add(contentPanel, BorderLayout.CENTER);

        telaAtual = "Principal";
        cardLayout.show(contentPanel, "Principal");
    }
     */
    public TelaLogadaUI (Usuario user) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tela Logada");
        setLayout(new BorderLayout());
        setSize(1200, 800);

        // ========== Menu ==========
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(Color.decode("#5e8ab3"));

        JLabel logoLabel = RecursosUI.criaImagemLabel("/images/logoMini.png");
        menuPanel.add(logoLabel);

        JPanel botoesPanel = botoesNavegacao(user);
        botoesPanel.setBackground(Color.decode("#5e8ab3"));
        menuPanel.add(botoesPanel);

        add(menuPanel, BorderLayout.WEST);

        // ========== Conteúdo ==========
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);
        contentPanel.setBackground(Color.WHITE);

        criaPaineisConteudo(user);

        add(contentPanel, BorderLayout.CENTER);

        telaAtual = "Principal";
        cardLayout.show(contentPanel, "Principal");
    }
    public void actionPerformed(ActionEvent e) {
        String nomePainel = e.getActionCommand();
        if (nomePainel.equals("Sair")) {
            int resp = JOptionPane.showOptionDialog(null, "Tem certeza que deseja sair?", "Sair", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Sim", "Não"}, "Sim");
            if (resp == JOptionPane.YES_OPTION) {
                dispose();
                LoginUI.frame.setVisible(true);
            }
        } else if (nomePainel.equals("Voltar")) {
            cardLayout.show(contentPanel, ultimaTela);
            telaAtual = ultimaTela;
        } else {
            ultimaTela = telaAtual;
            telaAtual = nomePainel;
            cardLayout.show(contentPanel, nomePainel);
        }
    }

    public <T extends Usuario> void criaPainelInfos(T user) {
        JPanel usuarioPanel = new JPanel();
        usuarioPanel.setLayout(new BoxLayout(usuarioPanel, BoxLayout.Y_AXIS));
        usuarioPanel.setBackground(Color.decode("#98f8c7"));

        if (user instanceof Medico medico) {
            JLabel medicoLabel = new JLabel(medico.getNome() + ", " + medico.getAreaAtuacao() + "     ");
            JLabel crmLabel = new JLabel("CRM: " + medico.getCRM() + "     ");

            usuarioPanel.add(medicoLabel);
            usuarioPanel.add(crmLabel);
        } else if (user instanceof Paciente paciente) {
            JLabel cpf = new JLabel(paciente.getCpf());
            JLabel idade = new JLabel(paciente.getIdade() + " anos");

            usuarioPanel.add(cpf);
            usuarioPanel.add(idade);
        }

        headerPanel.add(usuarioPanel);
    }

    public JButton criaBotaoMenu(String nome) {
        JButton button = new JButton(nome);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        button.setBackground(Color.decode("#98f8c7"));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Roboto", Font.BOLD, 14));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        // Adicionar fonte, cores, etc ...
        return button;
    }

    public JPanel botoesNavegacao(Usuario user) {
        JPanel menuButtonsPanel = new JPanel();
        menuButtonsPanel.setLayout(new BoxLayout(menuButtonsPanel, BoxLayout.Y_AXIS));

        ArrayList<JButton> buttons = new ArrayList<>();

        buttons.add(criaBotaoMenu("Principal"));

        if (user instanceof Medico) {
            buttons.add(criaBotaoMenu("Exames"));
            buttons.add(criaBotaoMenu("Laudos"));
            buttons.add(criaBotaoMenu("Consultas"));
            buttons.add(criaBotaoMenu("Receitas"));
            buttons.add(criaBotaoMenu("Cadastrar paciente"));
        } else if (user instanceof Paciente) {
            buttons.add(criaBotaoMenu("Verificar consultas"));
            buttons.add(criaBotaoMenu("Verificar laudos"));
            buttons.add(criaBotaoMenu("Verificar exames"));
            buttons.add(criaBotaoMenu("Verificar receitas"));
            buttons.add(criaBotaoMenu("Agendar consulta"));
        }

        buttons.add(criaBotaoMenu("Sair"));

        for (JButton btn : buttons) {
            menuButtonsPanel.add(btn);
            btn.addActionListener(this);
        }

        return menuButtonsPanel;
    }

    public <T extends Usuario> void criaPaineisConteudo(T user) {

        contentPanel.add(painelPrincipal(user), "Principal");

        if (user instanceof Medico) {
            contentPanel.add(TelaLogadaMedicoUI.telaPrescreverExame(this), "Exames");
            contentPanel.add(TelaLogadaMedicoUI.telaPrescreverLaudo(this), "Laudos");
            contentPanel.add(TelaLogadaMedicoUI.telaPrescreverReceita((Medico) user, this), "Receitas");
            contentPanel.add(TelaLogadaMedicoUI.telaAgendarConsulta(), "Consultas");
            contentPanel.add(CadastroUI.cadastroPaciente(), "Cadastrar paciente");
            contentPanel.add(TelaLogadaMedicoUI.telaNovoExame((Medico) user, this), "Prescrever novo exame");
            contentPanel.add(TelaLogadaMedicoUI.showExames((Medico) user, this), "Verificar exames");
            contentPanel.add(TelaLogadaMedicoUI.showExamesLaudo((Medico) user, this), "Prescrever novo laudo");
            contentPanel.add(TelaLogadaMedicoUI.showLaudos((Medico) user, this), "Verificar laudos");
            contentPanel.add(TelaLogadaMedicoUI.showPacientesReceita((Medico) user, this), "Prescrever nova receita");
            contentPanel.add(TelaLogadaMedicoUI.showReceitas((Medico) user, this), "Verificar receitas");
        } else if (user instanceof Paciente) {
            contentPanel.add(TelaLogadaPacienteUI.telaVerificarConsulta(), "Verificar consultas");
            contentPanel.add(TelaLogadaPacienteUI.telaVerificarLaudo((Paciente) user, this), "Verificar laudos");
            contentPanel.add(TelaLogadaPacienteUI.telaVerificarExame((Paciente) user, this), "Verificar exames");
            contentPanel.add(TelaLogadaPacienteUI.telaAgendarConsulta(), "Agendar consulta");
        }
    }

    public void atualizaPainel(Usuario user) {
        Component[] components = contentPanel.getComponents();

        for (Component comp : components) {
            contentPanel.remove(comp);
        }

        criaPaineisConteudo(user);
        cardLayout.show(contentPanel, telaAtual);
    }

    protected static <T extends Usuario> JPanel painelPrincipal(T user) {
        LoginUI.frame.setVisible(false);

        JPanel painelPrincipal = new JPanel();

        painelPrincipal.setVisible(true);
        painelPrincipal.setSize(800, 600);
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBackground(Color.WHITE);

        // ============ Informações do usuário ============
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        JLabel nomeLabel = new JLabel();
        JLabel info1Label = new JLabel();
        JLabel info2Label = new JLabel();

        if (user instanceof Medico medicoLogado) {
            nomeLabel.setText("Bem Vindo, Dr. " + medicoLogado.getNome() + ".");
            info1Label.setText("CRM: " + medicoLogado.getCRM());
            info2Label.setText("Área de atuação: " + medicoLogado.getAreaAtuacao());
        } else if (user instanceof Paciente pacienteLogado) {
            nomeLabel.setText("Bem Vindo, " + pacienteLogado.getNome() + ".");
            info1Label.setText("CPF: " + pacienteLogado.getCpf());
            info2Label.setText("Idade: " + pacienteLogado.getIdade());
        }

        infoPanel.add(nomeLabel);
        nomeLabel.setFont(new Font("Roboto", Font.BOLD, 20));
        nomeLabel.setForeground(Color.BLACK);
        nomeLabel.setBackground(Color.WHITE);

        infoPanel.add(info1Label);
        info1Label.setFont(new Font("Roboto", Font.BOLD, 20));
        info1Label.setForeground(Color.BLACK);
        info1Label.setBackground(Color.WHITE);

        infoPanel.add(info2Label);
        info2Label.setFont(new Font("Roboto", Font.BOLD, 20));
        info2Label.setForeground(Color.BLACK);
        info2Label.setBackground(Color.WHITE);

        painelPrincipal.add(infoPanel);

        // ============ Calendário ============
        JPanel consultasPanel = new JPanel();
        consultasPanel.setBackground(Color.WHITE);
        consultasPanel.setLayout(new GridLayout(1, 2));

        JPanel calendarPanel = new JPanel();
        calendarPanel.setBackground(Color.WHITE);
        calendarPanel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel calendarLabel = new JLabel("Calendário");
        constraints.gridx = 0;
        constraints.gridy = 0;
        calendarPanel.add(calendarLabel, constraints);

        JCalendar calendar = new JCalendar();
        calendar.setSize(400, 400);
        constraints.gridy = 1;
        calendarPanel.add(calendar, constraints);

        consultasPanel.add(calendarPanel);

        JPanel consultasDiaPanel = new JPanel();
        consultasDiaPanel.setBackground(Color.WHITE);
        consultasDiaPanel.setLayout(new GridBagLayout());

        JLabel consultasDiaLabel = new JLabel("Consultas do Dia");
        constraints.gridy = 0;
        consultasDiaPanel.add(consultasDiaLabel, constraints);

        consultasPanel.add(consultasDiaPanel);

        painelPrincipal.add(consultasPanel);

        return painelPrincipal;
    }

    public static void mostrarTela(Usuario user) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
                public void run() {
                TelaLogadaUI tela = new TelaLogadaUI(user);
                tela.setVisible(true);
            }
        });
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }
}
