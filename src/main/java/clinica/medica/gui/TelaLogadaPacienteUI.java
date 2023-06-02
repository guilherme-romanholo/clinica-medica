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
    protected static JPanel painelPaciente(Paciente pacienteLogado, ActionListener sairListener){
        LoginUI.frame.setVisible(false);

        JPanel painelPaciente = new JPanel();

        painelPaciente.setLayout(new GridBagLayout());
        painelPaciente.setVisible(true);
        painelPaciente.setSize(600, 400);


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
        painelPaciente.add(infoMedicoLabel, constraints);

        constraints.gridy = 2;
        painelPaciente.add(verificarConsultaButton, constraints);

        constraints.gridy = 3;
        painelPaciente.add(laudoButton, constraints);

        constraints.gridy = 4;
        painelPaciente.add(agendarConsultaButton, constraints);

        constraints.gridy = 5;
        painelPaciente.add(exameButton, constraints);

        constraints.gridy = 6;
        painelPaciente.add(sairButton, constraints);

        sairButton.addActionListener(sairListener);

        return painelPaciente;
    }
}
