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

public class TelaLogadaUI extends JFrame implements ActionListener {
    private String ultimaTela, telaAtual;
    private JPanel menuPanel;
    private JPanel contentPanel;
    private CardLayout cardLayout;

    public TelaLogadaUI(Usuario user) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tela Logada");
        setSize(1200, 800);

        menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());
        menuPanel.setBackground(UIManager.getColor(new JButton()));

        JPanel menuButtonsPanel = botoesNavegacao(user);

        menuPanel.add(menuButtonsPanel, BorderLayout.WEST);

        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);

        criaPaineisConteudo(user);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(menuPanel, BorderLayout.NORTH);
        container.add(contentPanel, BorderLayout.CENTER);
        
        telaAtual = "Principal";
        cardLayout.show(contentPanel, "Principal");
    }

    public void actionPerformed(ActionEvent e) {
        String nomePainel = e.getActionCommand();
        if (nomePainel.equals("Sair")){
            dispose();
            LoginUI.frame.setVisible(true);
        }else if(nomePainel.equals("Voltar")){
            cardLayout.show(contentPanel, ultimaTela);
            telaAtual = ultimaTela;
        } else {
            ultimaTela = telaAtual;
            telaAtual = nomePainel;
            cardLayout.show(contentPanel, nomePainel);
        }
    }

    public JButton criaBotaoMenu(String nome) {
        JButton button = new JButton(nome);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        // Adicionar fonte, cores, etc ...
        return button;
    }

    public JPanel botoesNavegacao(Usuario user) {
        JPanel menuButtonsPanel = new JPanel();
        menuButtonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

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

        for (JButton btn: buttons) {
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
            contentPanel.add(TelaLogadaMedicoUI.showExames((Medico) user,this), "Verificar exames");
            contentPanel.add(TelaLogadaMedicoUI.showExamesLaudo((Medico) user,this), "Prescrever novo laudo");
            contentPanel.add(TelaLogadaMedicoUI.showLaudos((Medico) user,this), "Verificar laudos");
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

        painelPrincipal.setLayout(new GridBagLayout());
        painelPrincipal.setVisible(true);
        painelPrincipal.setSize(800, 600);

        JTextArea infoLabel = new JTextArea();

        if (user instanceof Medico medicoLogado) {
            infoLabel.setText("============Bem Vindo, " + medicoLogado.getNome() + "============!\n\nÁrea de atuação: " + medicoLogado.getAreaAtuacao() + "\nCRM: " + medicoLogado.getCRM());
            infoLabel.setEditable(false);
        } else if (user instanceof Paciente pacienteLogado) {
            infoLabel.setText("============Bem Vindo, " + pacienteLogado.getNome() + "!============");
            infoLabel.setEditable(false);
        }

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        constraints.gridy = 1;
        painelPrincipal.add(infoLabel, constraints);

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
