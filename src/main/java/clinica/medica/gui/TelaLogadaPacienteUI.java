package clinica.medica.gui;

import clinica.medica.usuarios.Paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaLogadaPacienteUI {

    /**
     * Método privado para a implementação da tela do paciente.
     * @param pacienteLogado Paciente logado no momento.
     */
    protected static JPanel painelPaciente(Paciente pacienteLogado){
        LoginUI.frame.setVisible(false);

        JPanel painelPaciente = new JPanel();

        painelPaciente.setLayout(new GridBagLayout());
        painelPaciente.setVisible(true);
        painelPaciente.setSize(600, 400);

        JTextArea infoMedicoLabel = new JTextArea("============Bem Vindo, " + pacienteLogado.getNome() + "!============");
        infoMedicoLabel.setEditable(false);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        constraints.gridy = 1;
        painelPaciente.add(infoMedicoLabel, constraints);

        return painelPaciente;
    }

    protected static JPanel telaVerificarConsulta() {
        JPanel painelConsulta = new JPanel();

        painelConsulta.setLayout(new GridBagLayout());
        painelConsulta.setSize(800, 600);

        JTextArea tempLabel = new JTextArea("Verificar a consulta");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        constraints.gridy = 1;
        painelConsulta.add(tempLabel);

        return painelConsulta;
    }

    protected static JPanel telaAgendarConsulta() {
        JPanel painelConsulta = new JPanel();

        painelConsulta.setLayout(new GridBagLayout());
        painelConsulta.setSize(800, 600);

        JTextArea tempLabel = new JTextArea("Agendar a consulta");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        constraints.gridy = 1;
        painelConsulta.add(tempLabel);

        return painelConsulta;
    }

    protected static JPanel telaVerificarLaudo() {
        JPanel painelLaudo = new JPanel();

        painelLaudo.setLayout(new GridBagLayout());
        painelLaudo.setSize(800, 600);

        JTextArea tempLabel = new JTextArea("Verificar o laudo");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        constraints.gridy = 1;
        painelLaudo.add(tempLabel);

        return painelLaudo;
    }

    protected static JPanel telaVerificarExame() {
        JPanel painelExame = new JPanel();

        painelExame.setLayout(new GridBagLayout());
        painelExame.setSize(800, 600);

        JTextArea tempLabel = new JTextArea("Verificar o exame");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        constraints.gridy = 1;
        painelExame.add(tempLabel);

        return painelExame;
    }
}
