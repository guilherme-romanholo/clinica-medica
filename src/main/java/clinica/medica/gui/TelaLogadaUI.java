package clinica.medica.gui;

import clinica.medica.usuarios.Medico;
import clinica.medica.usuarios.Paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaLogadaUI {

    /**
     * Método público para chamar a tela de médico logado.
     * @param medicoLogado Médico que está logado no momento.
     */
    public static void telaMedico(Medico medicoLogado) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Não foi possível utilizar o recurso Look and Feel");
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                showTelaMedico(medicoLogado);
            }
        });
    }

    /**
     * Método público para chamar a tela de login do paciente.
     * @param pacienteLogado Paciente logado no momento.
     */
    public static void telaPaciente(Paciente pacienteLogado) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Não foi possível utilizar o recurso Look and Feel");
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                showTelaPaciente(pacienteLogado);
            }
        });
    }

    /**
     * Método privado para a implementação da tela do médico.
     * @param medicoLogado Médico logado no momento.
     */
    private static void showTelaMedico(Medico medicoLogado){
        LoginUI.frame.setVisible(false);

        JFrame frame = new JFrame("Área médica");

        frame.setLocationRelativeTo(LoginUI.frame);
        frame.setLayout(new GridBagLayout());
        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JTextArea infoMedicoLabel = new JTextArea("============Bem Vindo, " + medicoLogado.getNome() + "============!\n\nÁrea de atuação: " + medicoLogado.getAreaAtuacao() + "\nCRM: " + medicoLogado.getCRM());
        infoMedicoLabel.setEditable(false);


        JButton cadastrarClienteButton = new JButton("Cadastrar novo paciente");
        JButton agendarConsultaButton = new JButton("Agendar nova consulta");
        JButton laudoButton = new JButton("Prescrever novo laudo");
        JButton exameButton = new JButton("Prescrever novo exame");
        JButton sairButton = new JButton("Sair");


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        constraints.gridy = 1;
        frame.add(infoMedicoLabel, constraints);

        constraints.gridy = 2;
        frame.add(cadastrarClienteButton, constraints);

        constraints.gridy = 3;
        frame.add(laudoButton, constraints);

        constraints.gridy = 4;
        frame.add(agendarConsultaButton, constraints);

        constraints.gridy = 5;
        frame.add(exameButton, constraints);

        constraints.gridy = 6;
        frame.add(sairButton, constraints);

        cadastrarClienteButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                CadastroUI.telaCadastroPaciente();
            }
        });

        sairButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                LoginUI.frame.setVisible(true);
            }
        });
    }

    /**
     * Método privado para a implementação da tela do paciente.
     * @param pacienteLogado Paciente logado no momento.
     */
    private static void showTelaPaciente(Paciente pacienteLogado){
        LoginUI.frame.setVisible(false);

        JFrame frame = new JFrame("Área do paciente");

        frame.setLocationRelativeTo(LoginUI.frame);
        frame.setLayout(new GridBagLayout());
        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JTextArea infoMedicoLabel = new JTextArea("============Bem Vindo, " + pacienteLogado.getNome() + "!============");
        infoMedicoLabel.setEditable(false);


        JButton verificarConsultaButton = new JButton("Verifica consultas");
        JButton agendarConsultaButton = new JButton("Agendar nova consulta");
        JButton laudoButton = new JButton("Verificar laudos");
        JButton exameButton = new JButton("Verificar exames");
        JButton sairButton = new JButton("Sair");


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        constraints.gridy = 1;
        frame.add(infoMedicoLabel, constraints);

        constraints.gridy = 2;
        frame.add(verificarConsultaButton, constraints);

        constraints.gridy = 3;
        frame.add(laudoButton, constraints);

        constraints.gridy = 4;
        frame.add(agendarConsultaButton, constraints);

        constraints.gridy = 5;
        frame.add(exameButton, constraints);

        constraints.gridy = 6;
        frame.add(sairButton, constraints);

        sairButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                LoginUI.frame.setVisible(true);
            }
        });
    }
}
