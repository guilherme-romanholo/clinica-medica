package clinica.medica.gui.consulta;

import clinica.medica.consultas.Consulta;
import clinica.medica.consultas.Encaixe;
import clinica.medica.database.ConsultaSQL;
import clinica.medica.database.HorariosSQL;
import clinica.medica.database.UsuariosSQL;
import clinica.medica.gui.recursos.RecursosUI;
import clinica.medica.gui.telas.CadastroUI;
import clinica.medica.gui.telas.TelaLogadaUI;
import clinica.medica.usuarios.Medico;
import clinica.medica.usuarios.Paciente;
import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class ConsultasPacienteUI {
    public static JPanel showMedicosConsulta(Paciente pacienteLogado, TelaLogadaUI telaLogada) {
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setSize(800, 600);
        JPanel infoPanel = RecursosUI.criaInfoPanel("Medicos");
        ArrayList<Medico> medicos = UsuariosSQL.selectAllMedicos();
        int i = 0;

        JPanel painelExame = new JPanel();

        painelExame.setLayout(new GridBagLayout());
        painelExame.setSize(800, 600);


        JButton escolherMedicoButton = new JButton("Agendar consulta com o médico selecionado");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        String[] listaMedicos = new String[medicos.size()];

        for (Medico medico : medicos) {
            listaMedicos[i] = medico.getNome() + " - " + medico.getAreaAtuacao() + " - " + medico.getCpf();
            i++;
        }

        JList<String> list = new JList<>(listaMedicos);
        JScrollPane scrollPanel = new JScrollPane(list);

        scrollPanel.setPreferredSize(new Dimension(600, 400));
        constraints.gridy = 1;
        painelExame.add(scrollPanel, constraints);
        constraints.gridy = 2;
        painelExame.add(escolherMedicoButton, constraints);

        escolherMedicoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String exameSelecionado = list.getSelectedValue();
                String[] elementos = exameSelecionado.split("-");
                String cpfMedico = elementos[2].strip();
                String nomeMedico = elementos[0].strip();
                telaLogada.getContentPanel().add(telaAgendarConsulta(cpfMedico, nomeMedico, pacienteLogado, telaLogada), "Agendar consulta com o médico selecionado");
                telaLogada.getCardLayout().show(telaLogada.getContentPanel(), "Agendar consulta com o médico selecionado");
            }
        });
        painelPrincipal.add(infoPanel,BorderLayout.NORTH);
        painelPrincipal.add(painelExame,BorderLayout.CENTER);
        return painelPrincipal;
    }

    public static JPanel telaVerificarConsulta(Paciente pacienteLogado) {
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setSize(800, 600);
        JPanel infoPanel = RecursosUI.criaInfoPanel("Consultas");
        ArrayList<Consulta> consultas = ConsultaSQL.selectAllConsultasFromPaciente(pacienteLogado.getCpf());
        int i = 0;
        JPanel painelConsulta = new JPanel();

        painelConsulta.setLayout(new GridBagLayout());
        painelConsulta.setSize(800, 600);

        JButton vizualizarComentarioButton = new JButton("Visualizar comentários da consulta");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        String[] listaConsulta = new String[consultas.size()];

        for (Consulta rc: consultas) {
            if(rc.isRealizada()) {

                if (rc.getMotivoCancelamento().equals(""))
                    listaConsulta[i] = "Consulta já realizada - " + "Dr. " + rc.getMedico().getNome() + " - " + "Data: " + rc.getData().toString() + " - " + "Horário: " + rc.getHorario() + " horas" + " - " + rc.getId();
                else
                    listaConsulta[i] = "Consulta cancelada - " + "Dr. " + rc.getMedico().getNome() + " - " + "Data: " + rc.getData().toString() + " - " + "Horário: " + rc.getHorario() + " horas" + " - " + rc.getId();
            }else {
                if(rc.isEncaixe())
                    listaConsulta[i] = "Encaixe marcado - " + "Dr. " + rc.getMedico().getNome() + " - " + "Data: " + rc.getData().toString() + " - " + "Horário: " + rc.getHorario() + " horas" + " - " + rc.getId();
                else
                    listaConsulta[i] = "Consulta marcada - " + "Dr. " + rc.getMedico().getNome() + " - " + "Data: " + rc.getData().toString() + " - " + "Horário: " + rc.getHorario() + " horas" + " - " + rc.getId();
            }
            i++;
        }

        JList<String> list = new JList<>(listaConsulta);
        JScrollPane scrollPanel = new JScrollPane(list);

        scrollPanel.setPreferredSize(new Dimension(600, 400));
        constraints.gridy = 1;
        painelConsulta.add(scrollPanel, constraints);
        constraints.gridy = 2;
        painelConsulta.add(vizualizarComentarioButton, constraints);

        vizualizarComentarioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String consultaSelecionada = list.getSelectedValue();
                String[] elementos = consultaSelecionada.split("-");
                int id = Integer.parseInt(elementos[6].strip());
                Consulta consulta = new Consulta(id);
                if(consulta.getMotivoCancelamento().equals("") && !consulta.isEncaixe())
                    JOptionPane.showMessageDialog(painelConsulta, "Comentários: " + consulta.getDescricao(), "Comentarios da consulta", JOptionPane.INFORMATION_MESSAGE);
                else {
                    if(!consulta.isEncaixe()) {
                        JOptionPane.showMessageDialog(painelConsulta, "Comentários: " + consulta.getDescricao() + "\n\nMotivo do cancelamento: " + consulta.getMotivoCancelamento(), "Comentarios da consulta", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        Encaixe encaixe = new Encaixe(consulta.getId());
                        JOptionPane.showMessageDialog(painelConsulta, "Comentários: " + consulta.getDescricao() + "\n\nMotivo da emergência: " + encaixe.getMotivoEmergencia(), "Comentarios da consulta", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        painelPrincipal.add(infoPanel,BorderLayout.NORTH);
        painelPrincipal.add(painelConsulta,BorderLayout.CENTER);
        return painelPrincipal;
    }

    protected static JPanel telaNovaConsulta(Paciente pacienteLogado, String medicoSolicitado, String cpfMedico, Date data, TelaLogadaUI telaLogada) {
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setSize(800, 600);
        JPanel infoPanel = RecursosUI.criaInfoPanel("Nova consulta");
        JPanel painelConsulta = new JPanel();

        painelConsulta.setLayout(new GridBagLayout());
        //painelConsulta.setSize(100, 100);

        JLabel dataLabel = new JLabel("Data da consulta");
        JLabel horariolabel = new JLabel("Horário");
        JLabel medicoLabel = new JLabel("Medico");
        JLabel cpfPacienteLabel = new JLabel("CPF do paciente");
        JLabel comentarioLabel = new JLabel("Descrição");


        JFormattedTextField cpfField = CadastroUI.inicializaCpf();
        cpfField.setText(pacienteLogado.getCpf());
        cpfField.setEditable(false);
        cpfField.setEnabled(false);

        JTextField dataField = new JTextField(20);
        dataField.setText(data.toString());
        dataField.setEditable(false);
        dataField.setEnabled(false);

        String[] horariosDisponiveis = HorariosSQL.selectHorariosDisponiveis(cpfMedico, data);
        JComboBox<String> horariosCombo = new JComboBox<>(horariosDisponiveis);

        JTextField medicoField = new JTextField(20);
        medicoField.setText(medicoSolicitado);
        medicoField.setEditable(false);
        medicoField.setEnabled(false);

        JTextArea comentarioArea = new JTextArea(20, 40);
        comentarioArea.setSize(new Dimension(100, 100));
        comentarioArea.setEditable(true);
        comentarioArea.setLineWrap(true);
        comentarioArea.setWrapStyleWord(true);

        JButton cadastrarConsultaButton = new JButton("Agendar consulta");
        JButton voltarButton = new JButton("Voltar");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        //constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;


        constraints.gridy = 2;
        painelConsulta.add(dataLabel, constraints);

        constraints.gridy = 3;
        painelConsulta.add(dataField, constraints);

        constraints.gridy = 4;
        painelConsulta.add(horariolabel, constraints);

        constraints.gridy = 5;
        painelConsulta.add(horariosCombo, constraints);

        constraints.gridy = 6;
        painelConsulta.add(cpfPacienteLabel, constraints);

        constraints.gridy = 7;
        painelConsulta.add(cpfField, constraints);

        constraints.gridy = 8;
        painelConsulta.add(medicoLabel, constraints);

        constraints.gridy = 9;
        painelConsulta.add(medicoField, constraints);

        constraints.gridy = 10;
        painelConsulta.add(comentarioLabel, constraints);

        constraints.gridy = 11;
        painelConsulta.add(comentarioArea, constraints);

        constraints.gridy = 12;
        painelConsulta.add(cadastrarConsultaButton, constraints);

        constraints.gridy = 13;
        painelConsulta.add(voltarButton, constraints);

        cadastrarConsultaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String cpf = cpfField.getText();
                cpf = cpf.replaceAll("[.-]", "");
                String conteudo = comentarioArea.getText();
                String horario = (String) horariosCombo.getSelectedItem();
                if (!horario.equals("Não existem horários disponíveis nesse dia.") && ConsultaSQL.salvarConsulta(data, cpfMedico, pacienteLogado.getCpf(),conteudo, horario,painelConsulta)) {
                    JOptionPane.showMessageDialog(painelConsulta, "Agendamento de consulta realizado com sucesso!");
                    telaLogada.atualizaPainel(pacienteLogado);
                }else {
                    JOptionPane.showMessageDialog(painelConsulta, "Não é possível cadastrar consultas sem horários disponíveis !", "ERRO", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        voltarButton.addActionListener(telaLogada);
        painelPrincipal.add(infoPanel,BorderLayout.NORTH);
        painelPrincipal.add(painelConsulta,BorderLayout.CENTER);
        return painelPrincipal;
    }
    protected static JPanel telaAgendarConsulta(String cpfMedico, String nomeMedico, Paciente pacienteLogado, TelaLogadaUI telaLogada) {
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setSize(800, 600);
        JPanel infoPanel = RecursosUI.criaInfoPanel("Agendamento de consulta");

        GridBagConstraints constraints = new GridBagConstraints();

        JLabel nomeMedicoLabel = new JLabel("Escolha uma data para agendar a consulta com o Dr." + nomeMedico);
        JButton agendarConsultaButton = new JButton("Agendar a consulta");
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(telaLogada);


        JPanel calendarPanel = new JPanel();
        calendarPanel.setBackground(Color.WHITE);
        calendarPanel.setLayout(new GridBagLayout());

        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel calendarLabel = new JLabel("Calendário");
        constraints.gridx = 0;
        constraints.gridy = 0;
        calendarPanel.add(nomeMedicoLabel,constraints);

        constraints.gridy = 1;
        calendarPanel.add(calendarLabel, constraints);

        JCalendar calendar = new JCalendar();

        calendar.setSize(400, 400);
        calendar.getDayChooser().getDayPanel().setBackground(Color.WHITE);
        calendar.getDayChooser().setWeekOfYearVisible(false);
        calendar.setDecorationBackgroundColor(Color.WHITE);
        calendar.setMinSelectableDate(new Date(Calendar.getInstance().getTime().getTime()));
        constraints.gridy = 2;
        calendarPanel.add(calendar, constraints);
        constraints.gridy = 3;
        calendarPanel.add(agendarConsultaButton, constraints);


        // ================ Listeners ================

        class MeuPropertyListener implements PropertyChangeListener {
            protected Date data;
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                if ("calendar".equals(e.getPropertyName())) {
                    Calendar selectedCalendar = (Calendar) e.getNewValue();
                    this.data = new Date(selectedCalendar.getTimeInMillis());
                }
            }

            public class MeuMouseListener extends MouseAdapter {
                @Override
                public void mouseClicked(MouseEvent e) {
                    telaLogada.getContentPanel().add(telaNovaConsulta(pacienteLogado, nomeMedico, cpfMedico,  data, telaLogada), "Agendar a consulta");
                    telaLogada.getCardLayout().show(telaLogada.getContentPanel(), "Agendar a consulta");
                }
            }
        }

        MeuPropertyListener propertyListener = new MeuPropertyListener();
        calendar.addPropertyChangeListener(propertyListener);

        MeuPropertyListener.MeuMouseListener meuMouseListener = propertyListener.new MeuMouseListener();
        agendarConsultaButton.addMouseListener(meuMouseListener);

        painelPrincipal.add(infoPanel,BorderLayout.NORTH);
        painelPrincipal.add(calendarPanel,BorderLayout.CENTER);
        return painelPrincipal;
    }
}
