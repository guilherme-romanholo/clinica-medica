package clinica.medica.gui;

import clinica.medica.usuarios.Medico;
import clinica.medica.usuarios.Paciente;
import clinica.medica.usuarios.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static clinica.medica.gui.LoginUI.frame;
import com.toedter.calendar.JCalendar;

public class TelaLogadaUI extends JFrame implements ActionListener {
    private String ultimaTela, telaAtual;
    private JPanel menuPanel;
    private JPanel headerPanel;
    private JPanel contentPanel;
    private CardLayout cardLayout;

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

        JPanel nomeClinicaPanel = new JPanel(new BorderLayout());
        nomeClinicaPanel.setSize(156, 50);
        nomeClinicaPanel.setBackground(Color.decode("#98f8c7"));

        JLabel nomeClinicaLabel = new JLabel("        Healthy");
        nomeClinicaPanel.add(nomeClinicaLabel, BorderLayout.CENTER);

        nomeClinicaLabel.setForeground(Color.WHITE);
        nomeClinicaLabel.setFont(new Font("Roboto", Font.BOLD, 16));

        headerPanel.add(nomeClinicaPanel);

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
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        button.setBackground(Color.decode("#98f8c7"));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setFocusPainted(false);
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
        frame.setVisible(false);

        JPanel painelPrincipal = new JPanel();

        painelPrincipal.setVisible(true);
        painelPrincipal.setSize(800, 600);

        JCalendar calendar = new JCalendar();
        painelPrincipal.add(calendar);

        /*
        if (user instanceof Medico medicoLogado) {
            infoLabel.setText("============Bem Vindo, " + medicoLogado.getNome() + "============!\n\nÁrea de atuação: " + medicoLogado.getAreaAtuacao() + "\nCRM: " + medicoLogado.getCRM());
            infoLabel.setEditable(false);
        } else if (user instanceof Paciente pacienteLogado) {
            infoLabel.setText("============Bem Vindo, " + pacienteLogado.getNome() + "!============");
            infoLabel.setEditable(false);
        }
        */

        return painelPrincipal;
    }

    public static void mostrarTela(Usuario user) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {
                    e.printStackTrace();
                }

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
