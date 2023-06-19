package clinica.medica.gui.consulta;

import clinica.medica.consultas.Consulta;
import clinica.medica.consultas.Encaixe;
import clinica.medica.database.ConsultaSQL;
import clinica.medica.gui.recursos.RecursosUI;
import clinica.medica.gui.telas.TelaLogadaUI;
import clinica.medica.usuarios.Medico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class ConsultasMedicoUI {
    public static JPanel telaAgendarConsulta(TelaLogadaUI telaLogada) {
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setSize(800, 600);
        JPanel infoPanel = RecursosUI.criaInfoPanel("Área das consultas");
        JPanel painelConsulta = new JPanel();

        painelConsulta.setLayout(new GridBagLayout());
        painelConsulta.setSize(800, 600);


        JButton novaConsultaButton = new JButton("Agendar novo encaixe");
        JButton verificarConsultaButton = new JButton("Verificar consultas");

        verificarConsultaButton.addActionListener(telaLogada);
        novaConsultaButton.addActionListener(telaLogada);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        constraints.gridy = 2;
        painelConsulta.add(novaConsultaButton, constraints);

        constraints.gridy = 3;
        painelConsulta.add(verificarConsultaButton, constraints);

        painelPrincipal.add(infoPanel, BorderLayout.NORTH);
        painelPrincipal.add(painelConsulta, BorderLayout.CENTER);

        return painelPrincipal;
    }
    public static JPanel showConsultas(Medico medicoLogado, TelaLogadaUI telaLogada) {
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setSize(800, 600);
        JPanel infoPanel = RecursosUI.criaInfoPanel("Consultas");
        ArrayList<Consulta> consultas = ConsultaSQL.selectAllConsultasFromMedico(medicoLogado.getCpf());
        int i = 0;
        JPanel painelConsulta = new JPanel();

        painelConsulta.setLayout(new GridBagLayout());
        painelConsulta.setSize(800, 600);

        JButton vizualizarComentarioButton = new JButton("Visualizar comentários da consulta");
        JButton editarConsultaButton = new JButton("Editar consulta");
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(telaLogada);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        String[] listaConsulta = new String[consultas.size()];
        System.out.println(consultas.size());
        for (Consulta rc : consultas) {
            if (!rc.isRealizada()) {
                if (rc.isEncaixe()) {
                    Encaixe enc = new Encaixe(rc.getId());
                    listaConsulta[i] = "Encaixe marcado - " + "Paciente: " + rc.getPaciente().getNome() + " - " + "Data: " + rc.getData().toString() + " - " + "Horário: " + rc.getHorario() + " horas" + " - " + rc.getId();
                }else{
                    listaConsulta[i] = "Consulta marcada - " + "Paciente: " + rc.getPaciente().getNome() + " - " + "Data: " + rc.getData().toString() + " - " + "Horário: " + rc.getHorario() + " horas" + " - " + rc.getId();
                }
                i++;
            }
        }

        JList<String> list = new JList<>(listaConsulta);
        JScrollPane scrollPanel = new JScrollPane(list);

        scrollPanel.setPreferredSize(new Dimension(600, 400));
        constraints.gridy = 1;
        painelConsulta.add(scrollPanel, constraints);
        constraints.gridy = 2;
        painelConsulta.add(vizualizarComentarioButton, constraints);
        constraints.gridy = 3;
        painelConsulta.add(editarConsultaButton, constraints);
        constraints.gridy = 4;
        painelConsulta.add(voltarButton, constraints);

        vizualizarComentarioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String consultaSelecionada = list.getSelectedValue();
                String[] elementos = consultaSelecionada.split("-");
                int id = Integer.parseInt(elementos[6].strip());
                Consulta consulta = new Consulta(id);
                if (consulta.getMotivoCancelamento().equals("") && !consulta.isEncaixe())
                    JOptionPane.showMessageDialog(painelConsulta, "Comentários do paciente: " + consulta.getDescricao(), "Comentarios da consulta", JOptionPane.INFORMATION_MESSAGE);
                else if (!consulta.getMotivoCancelamento().equals(""))
                    JOptionPane.showMessageDialog(painelConsulta, "Comentários do paciente: " + consulta.getDescricao() + "\n\nMotivo do cancelamento: " + consulta.getMotivoCancelamento(), "Comentarios da consulta", JOptionPane.INFORMATION_MESSAGE);
                else if(consulta.isEncaixe()){
                    Encaixe encaixe = new Encaixe(consulta.getId());
                    JOptionPane.showMessageDialog(painelConsulta, "Comentários do paciente: " + consulta.getDescricao() + "\n\nMotivo da emergência: " + encaixe.getMotivoEmergencia(), "Comentarios da consulta", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        editarConsultaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String consultaSelecionada = list.getSelectedValue();
                String[] elementos = consultaSelecionada.split("-");
                int id = Integer.parseInt(elementos[6].strip());
                Consulta consulta = new Consulta(id);

                JRadioButton feitaButton = new JRadioButton("Marcar consulta como realizada");
                JRadioButton canceladaButton = new JRadioButton("Cancelar consulta");
                ButtonGroup radioGroup = new ButtonGroup();
                radioGroup.add(feitaButton);
                radioGroup.add(canceladaButton);

                JPanel radioPanel = new JPanel();
                radioPanel.setLayout(new FlowLayout());
                radioPanel.add(feitaButton);
                radioPanel.add(canceladaButton);

                ;

                int option = JOptionPane.showOptionDialog(painelConsulta, radioPanel, "Selecione uma opção", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

                if (option == JOptionPane.OK_OPTION) {
                    if (feitaButton.isSelected()) {
                        ConsultaSQL.atualizaConsulta(consulta.getId());
                        JOptionPane.showMessageDialog(painelConsulta, "Consulta finalizada com sucesso! ", "Consulta finalizada", JOptionPane.INFORMATION_MESSAGE);
                        telaLogada.atualizaPainel(medicoLogado);
                    } else if (canceladaButton.isSelected()) {
                        JPanel fieldPanel = new JPanel();
                        fieldPanel.setLayout(new BorderLayout());
                        JLabel motivoLabel = new JLabel("Digite o motivo do cancelamento");
                        JTextArea cancelamento = new JTextArea(10, 10);
                        cancelamento.setEditable(true);
                        cancelamento.setLineWrap(true);
                        fieldPanel.add(motivoLabel, BorderLayout.NORTH);
                        fieldPanel.add(cancelamento, BorderLayout.SOUTH);
                        int option2 = JOptionPane.showOptionDialog(painelConsulta, fieldPanel, "Cancelamento", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
                        if (option2 == JOptionPane.OK_OPTION) {
                            String motivo = cancelamento.getText();
                            ConsultaSQL.atualizaConsulta(consulta.getId(), motivo);
                            JOptionPane.showMessageDialog(painelConsulta, "Consulta cancelada com sucesso!", "Consulta cancelada", JOptionPane.INFORMATION_MESSAGE);
                            telaLogada.atualizaPainel(medicoLogado);
                        }
                    }
                }
            }
        });
        painelPrincipal.add(infoPanel, BorderLayout.NORTH);
        painelPrincipal.add(painelConsulta, BorderLayout.CENTER);
        return painelPrincipal;
    }
}
