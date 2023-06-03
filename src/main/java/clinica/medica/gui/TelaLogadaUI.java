package clinica.medica.gui;

import clinica.medica.usuarios.Medico;
import clinica.medica.usuarios.Paciente;
import clinica.medica.usuarios.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaLogadaUI extends JFrame implements ActionListener {
    private JPanel menuPanel;
    private JPanel contentPanel;
    private CardLayout cardLayout;

    public TelaLogadaUI(Usuario user) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tela Logada");
        setSize(800, 600);

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

        cardLayout.show(contentPanel, "Principal");
    }

    public void actionPerformed(ActionEvent e) {
        String nomePainel = e.getActionCommand();

        if (nomePainel.equals("Sair")){
            dispose();
            LoginUI.frame.setVisible(true);
        } else {
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

        ArrayList<JButton> buttons = new ArrayList<JButton>();

        buttons.add(criaBotaoMenu("Principal"));

        if (user instanceof Medico) {
            buttons.add(criaBotaoMenu("Prescrever exame"));
            buttons.add(criaBotaoMenu("Prescrever laudo"));
            buttons.add(criaBotaoMenu("Agendar consulta"));
            buttons.add(criaBotaoMenu("Cadastrar paciente"));
        } else if (user instanceof Paciente) {
            buttons.add(criaBotaoMenu("Verificar consultas"));
            buttons.add(criaBotaoMenu("Verificar laudos"));
            buttons.add(criaBotaoMenu("Verificar exames"));
            buttons.add(criaBotaoMenu("Agendar consulta"));
        }

        buttons.add(criaBotaoMenu("Sair"));

        for (JButton btn: buttons) {
            menuButtonsPanel.add(btn);
            btn.addActionListener(this);
        }

        return menuButtonsPanel;
    }

    public void criaPaineisConteudo(Usuario user) {
        if (user instanceof Medico) {
            contentPanel.add(TelaLogadaMedicoUI.painelMedico((Medico) user), "Principal");
            contentPanel.add(TelaLogadaMedicoUI.telaPrescreverExame(), "Prescrever exame");
            contentPanel.add(TelaLogadaMedicoUI.telaPrescreverLaudo(), "Prescrever laudo");
            contentPanel.add(TelaLogadaMedicoUI.telaAgendarConsulta(), "Agendar consulta");
            contentPanel.add(CadastroUI.cadastroPaciente(), "Cadastrar paciente");
        } else if (user instanceof Paciente) {
            contentPanel.add(TelaLogadaPacienteUI.painelPaciente((Paciente) user), "Principal");
            contentPanel.add(TelaLogadaPacienteUI.telaVerificarConsulta(), "Verificar consultas");
            contentPanel.add(TelaLogadaPacienteUI.telaVerificarLaudo(), "Verificar laudos");
            contentPanel.add(TelaLogadaPacienteUI.telaVerificarExame(), "Verificar exames");
            contentPanel.add(TelaLogadaPacienteUI.telaAgendarConsulta(), "Agendar consulta");
        }
    }

    public static void mostrarTela(Usuario user) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                TelaLogadaUI tela = new TelaLogadaUI(user);
                tela.setVisible(true);
            }
        });
    }
}
