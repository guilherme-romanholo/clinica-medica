package clinica.medica.gui;

import clinica.medica.usuarios.Medico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaLogadaMedicoUI {


    /**
     * Método privado para a implementação da tela do médico.
     * @param medicoLogado Médico logado no momento.
     */
    protected static JPanel painelMedico(Medico medicoLogado, ActionListener sairListener){
        LoginUI.frame.setVisible(false);

        JPanel painelMedico = new JPanel();

        painelMedico.setLayout(new GridBagLayout());
        painelMedico.setVisible(true);
        painelMedico.setSize(600, 400);

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
        painelMedico.add(infoMedicoLabel, constraints);

        constraints.gridy = 2;
        painelMedico.add(cadastrarClienteButton, constraints);

        constraints.gridy = 3;
        painelMedico.add(laudoButton, constraints);

        constraints.gridy = 4;
        painelMedico.add(agendarConsultaButton, constraints);

        constraints.gridy = 5;
        painelMedico.add(exameButton, constraints);

        constraints.gridy = 6;
        painelMedico.add(sairButton, constraints);

        cadastrarClienteButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                CadastroUI.telaCadastroPaciente();
            }
        });

        sairButton.addActionListener(sairListener);

        return painelMedico;
    }
}
