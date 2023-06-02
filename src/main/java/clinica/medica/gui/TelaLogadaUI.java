package clinica.medica.gui;

import clinica.medica.usuarios.Medico;
import clinica.medica.usuarios.Paciente;
import clinica.medica.usuarios.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogadaUI {
    private JFrame mainFrame;
    private JPanel paineis;
    private CardLayout cardLayout;

    /**
     * Construtor da tela principal
     */
    public TelaLogadaUI(Usuario user) {
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 400);
        mainFrame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        paineis = new JPanel(cardLayout);

        if (user instanceof Medico)
            incializaPainelMedico((Medico) user);
        else if (user instanceof Paciente)
            inicializaPainelPaciente((Paciente) user);

        mainFrame.getContentPane().add(paineis, BorderLayout.CENTER);
        cardLayout.show(paineis, "principal");

        mainFrame.setJMenuBar(menuBar);

        mainFrame.setVisible(true);
    }

    public static void mostrarTela(Usuario user) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Não foi possível utilizar o recurso Look and Feel");
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaLogadaUI(user);
            }
        });
    }

    public void incializaPainelMedico(Medico medico) {
        JPanel painelPrincipalMedico = TelaLogadaMedicoUI.painelMedico(medico, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainFrame.dispose();
                LoginUI.frame.setVisible(true);
            }
        });
        paineis.add(painelPrincipalMedico, "principal");
        // Adicionar os outros paineis ...
    }

    public void inicializaPainelPaciente(Paciente paciente) {
        JPanel painelPrincipalMedico = TelaLogadaPacienteUI.painelPaciente(paciente, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainFrame.dispose();
                LoginUI.frame.setVisible(true);
            }
        });
        paineis.add(painelPrincipalMedico, "principal");
        // Adicionar os outros paineis ...
    }
}
