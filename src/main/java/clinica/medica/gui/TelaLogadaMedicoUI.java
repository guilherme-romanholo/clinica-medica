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
    protected static JPanel painelMedico(Medico medicoLogado){
        LoginUI.frame.setVisible(false);

        JPanel painelMedico = new JPanel();

        painelMedico.setLayout(new GridBagLayout());
        painelMedico.setVisible(true);
        painelMedico.setSize(800, 600);

        JTextArea infoMedicoLabel = new JTextArea("============Bem Vindo, " + medicoLogado.getNome() + "============!\n\nÁrea de atuação: " + medicoLogado.getAreaAtuacao() + "\nCRM: " + medicoLogado.getCRM());
        infoMedicoLabel.setEditable(false);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        constraints.gridy = 1;
        painelMedico.add(infoMedicoLabel, constraints);

        return painelMedico;
    }

    protected static JPanel telaPrescreverExame() {
        JPanel painelExame = new JPanel();

        painelExame.setLayout(new GridBagLayout());
        painelExame.setSize(800, 600);

        JTextArea tempLabel = new JTextArea("Área dos exames");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        constraints.gridy = 1;
        painelExame.add(tempLabel);

        return painelExame;
    }

    protected static JPanel telaPrescreverLaudo() {
        JPanel painelLaudo = new JPanel();

        painelLaudo.setLayout(new GridBagLayout());
        painelLaudo.setSize(800, 600);

        JTextArea tempLabel = new JTextArea("Área do Laudo");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        constraints.gridy = 1;
        painelLaudo.add(tempLabel);

        return painelLaudo;
    }

    protected static JPanel telaAgendarConsulta() {
        JPanel painelConsulta = new JPanel();

        painelConsulta.setLayout(new GridBagLayout());
        painelConsulta.setSize(800, 600);

        JTextArea tempLabel = new JTextArea("Área da Consulta");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        constraints.gridy = 1;
        painelConsulta.add(tempLabel);

        return painelConsulta;
    }
}
