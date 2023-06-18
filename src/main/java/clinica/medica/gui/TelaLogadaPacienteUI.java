package clinica.medica.gui;

import clinica.medica.consultas.Consulta;
import clinica.medica.database.*;
import clinica.medica.documentos.Exame;
import clinica.medica.documentos.Laudo;
import clinica.medica.documentos.Receita;
import clinica.medica.usuarios.Medico;
import clinica.medica.usuarios.Paciente;
import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class TelaLogadaPacienteUI {

    protected static JPanel showMedicosConsulta(Paciente pacienteLogado, TelaLogadaUI telaLogada) {
        ArrayList<Medico> medicos = UsuariosSQL.selectAllMedicos();
        int i = 0;

        JPanel painelExame = new JPanel();

        painelExame.setLayout(new GridBagLayout());
        painelExame.setSize(800, 600);

        JTextArea tempLabel = new JTextArea("Medicos");

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
        constraints.gridy = 0;
        painelExame.add(tempLabel, constraints);
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

        return painelExame;
    }

    protected static JPanel telaVerificarConsulta(Paciente pacienteLogado) {
        ArrayList<Consulta> consultas = ConsultaSQL.selectAllConsultasFromPaciente(pacienteLogado.getCpf());
        int i = 0;
        JPanel painelConsulta = new JPanel();

        painelConsulta.setLayout(new GridBagLayout());
        painelConsulta.setSize(800, 600);

        JTextArea tempLabel = new JTextArea("Consultas");
        JButton vizualizarComentarioButton = new JButton("Visualizar comentários da consulta");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        String[] listaConsulta = new String[consultas.size()];

        for (Consulta rc: consultas) {
            if(rc.isRealizada())
                listaConsulta[i] = "Consulta já realizada - " + "Dr. " + rc.getMedico().getNome() + " - " + "Data: " + rc.getData().toString() + " - " + "Horário: " + rc.getHorario() + " horas" + " - " + rc.getId();
            else
                listaConsulta[i] = "Consulta marcada - " + "Dr. " + rc.getMedico().getNome() + " - " + "Data: " + rc.getData().toString() + " - " + "Horário: " + rc.getHorario() + " horas" + " - " + rc.getId();
            i++;
        }

        JList<String> list = new JList<>(listaConsulta);
        JScrollPane scrollPanel = new JScrollPane(list);

        scrollPanel.setPreferredSize(new Dimension(600, 400));
        constraints.gridy = 0;
        painelConsulta.add(tempLabel, constraints);
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
                JOptionPane.showMessageDialog(painelConsulta, "Comentários: " + consulta.getDescricao(), "Comentarios da consulta", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        return painelConsulta;
    }

    protected static JPanel telaNovaConsulta(Paciente pacienteLogado, String medicoSolicitado, String cpfMedico, Date data, TelaLogadaUI telaLogada) {
        JPanel painelConsulta = new JPanel();

        painelConsulta.setLayout(new GridBagLayout());
        //painelConsulta.setSize(100, 100);

        JTextArea tempLabel = new JTextArea("Nova consulta");
        tempLabel.setEditable(false);

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


        constraints.gridy = 1;
        painelConsulta.add(tempLabel, constraints);


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
                if (ConsultaSQL.salvarConsulta(data, cpfMedico, pacienteLogado.getCpf(),conteudo, horario)) {
                    JOptionPane.showMessageDialog(painelConsulta, "Agendamento de consulta realizado com sucesso!");
                    telaLogada.atualizaPainel(pacienteLogado);
                }

            }
        });

        voltarButton.addActionListener(telaLogada);

        return painelConsulta;
    }
    protected static JPanel telaAgendarConsulta(String cpfMedico, String nomeMedico, Paciente pacienteLogado, TelaLogadaUI telaLogada) {
        JPanel painelConsulta = new JPanel();

        painelConsulta.setLayout(new GridLayout(3,2));
        painelConsulta.setSize(800, 600);
        painelConsulta.setBackground(Color.WHITE);

        GridBagConstraints constraints = new GridBagConstraints();

        JTextArea tempLabel = new JTextArea("Agendar consulta");
        JLabel nomeMedicoLabel = new JLabel("Escolha uma data e horário para agendar a consulta com o Dr." + nomeMedico);

        painelConsulta.add(tempLabel);
        painelConsulta.add(nomeMedicoLabel);

        JPanel calendarPanel = new JPanel();
        calendarPanel.setBackground(Color.WHITE);
        calendarPanel.setLayout(new GridBagLayout());

        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel calendarLabel = new JLabel("Calendário");
        constraints.gridx = 0;
        constraints.gridy = 0;
        calendarPanel.add(calendarLabel, constraints);

        JCalendar calendar = new JCalendar();

        calendar.setSize(400, 400);
        calendar.getDayChooser().getDayPanel().setBackground(Color.WHITE);
        calendar.getDayChooser().setWeekOfYearVisible(false);
        calendar.setDecorationBackgroundColor(Color.WHITE);
        calendar.setMinSelectableDate(new Date(Calendar.getInstance().getTime().getTime()));
        constraints.gridy = 1;
        calendarPanel.add(calendar, constraints);

        painelConsulta.add(calendarPanel);

        JPanel consultasHorarioPanel = new JPanel();
        //consultasHorarioPanel.setBackground(Color.WHITE);
        consultasHorarioPanel.setLayout(new GridBagLayout());

        JLabel consultasHoararioLabel = new JLabel("Horários disponíveis");
        JButton agendarConsultaButton = new JButton("Agendar a consulta");
        constraints.gridy = 0;
        consultasHorarioPanel.add(consultasHoararioLabel, constraints);

        constraints.gridy = 2;
        consultasHorarioPanel.add(agendarConsultaButton, constraints);

        painelConsulta.add(consultasHorarioPanel);

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

        return painelConsulta;
    }

    protected static JPanel telaVerificarLaudo(Paciente pacienteLogado, TelaLogadaUI telaLogada) {
        ArrayList<Laudo> laudos = PacientesSQL.verificarLaudos(pacienteLogado.getCpf());
        int i = 0;
        JPanel painelLaudo = new JPanel();

        painelLaudo.setLayout(new GridBagLayout());
        painelLaudo.setSize(800, 600);

        JTextArea tempLabel = new JTextArea("Laudos");

        JButton imprimirLaudoButton = new JButton("Visualizar laudo");
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(telaLogada);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        String[] listaLaudo = new String[laudos.size()];

        for (Laudo ex : laudos) {
            listaLaudo[i] = "Laudo - " + ex.getPaciente().getNome() + " - " + ex.getExame().getTipo() + " - " + ex.getExame().getId();
            i++;
        }


        JList<String> list = new JList<>(listaLaudo);
        JScrollPane scrollPanel = new JScrollPane(list);
        scrollPanel.setPreferredSize(new Dimension(800, 600));

        constraints.gridy = 0;
        painelLaudo.add(tempLabel, constraints);
        constraints.gridy = 1;
        painelLaudo.add(scrollPanel, constraints);
        constraints.gridy = 2;
        painelLaudo.add(imprimirLaudoButton, constraints);
        constraints.gridy = 3;
        painelLaudo.add(voltarButton, constraints);

        imprimirLaudoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String laudoSelecionado = list.getSelectedValue();
                String[] elementos = laudoSelecionado.split("-");
                int id = Integer.parseInt(elementos[3].strip());
                Laudo laudo = new Laudo(id);
             //   TelaLogadaMedicoUI.imprimirLaudo(medicoLogado, laudo);
            }
        });

        return painelLaudo;
    }

    protected static JPanel telaVerificarExame(Paciente pacienteLoagado, TelaLogadaUI telaLogada) {
        int i = 0;
        ArrayList<Exame> exames = PacientesSQL.verificarExamesPaciente(pacienteLoagado.getCpf());
        JPanel painelExame = new JPanel();

        painelExame.setLayout(new GridBagLayout());
        painelExame.setSize(800, 600);

        JTextArea tempLabel = new JTextArea("Exames");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        constraints.gridy = 1;
        painelExame.add(tempLabel);

        JButton imprimirExameButton = new JButton("Visualizar exame");
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(telaLogada);

        String[] listaExame = new String[exames.size()];

        for (Exame ex : exames) {
            listaExame[i] = "Exame - " + ex.getPaciente().getNome() + " - " + ex.getTipo() + " - " + ex.getId();
            i++;
        }

        JList<String> list = new JList<>(listaExame);
        JScrollPane scrollPanel = new JScrollPane(list);

        scrollPanel.setPreferredSize(new Dimension(800, 600));

        constraints.gridy = 0;
        painelExame.add(tempLabel);
        constraints.gridy = 1;
        painelExame.add(scrollPanel, constraints);
        constraints.gridy = 2;
        painelExame.add(imprimirExameButton, constraints);
        constraints.gridy = 3;
        painelExame.add(voltarButton, constraints);

        imprimirExameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String exameSelecionado = list.getSelectedValue();
                String[] elementos = exameSelecionado.split("-");
                int id = Integer.parseInt(elementos[3].strip());
                Exame exame = new Exame(id);
                //TelaLogadaMedicoUI.imprimirExame(exame);
            }
        });

        return painelExame;
    }

    protected static JPanel telaVerificarReceitas(Paciente pacienteLogado, TelaLogadaUI telaLogada) {
        ArrayList<Receita> receitas = ReceitasSQL.selectAllReceitasFromPaciente(pacienteLogado.getCpf());
        int i = 0;
        JPanel painelReceita = new JPanel();

        painelReceita.setLayout(new GridBagLayout());
        painelReceita.setSize(800, 600);

        JTextArea tempLabel = new JTextArea("Receitas");
        JButton imprimirReceitaButton = new JButton("Visualizar receita");
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(telaLogada);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        String[] listaReceita = new String[receitas.size()];

        for (Receita rc: receitas) {
            listaReceita[i] = "Receita - " + rc.getPaciente().getNome() + " - " + rc.getNomeDoRemedio() + " - " + rc.getMedico().getNome() + " - " + rc.getId();
            i++;
        }

        JList<String> list = new JList<>(listaReceita);
        JScrollPane scrollPanel = new JScrollPane(list);

        scrollPanel.setPreferredSize(new Dimension(600, 400));
        constraints.gridy = 0;
        painelReceita.add(tempLabel, constraints);
        constraints.gridy = 1;
        painelReceita.add(scrollPanel, constraints);
        constraints.gridy = 2;
        painelReceita.add(imprimirReceitaButton, constraints);
        constraints.gridy = 3;
        painelReceita.add(voltarButton, constraints);

        imprimirReceitaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String receitaSelecionada = list.getSelectedValue();
                String[] elementos = receitaSelecionada.split("-");
                int id = Integer.parseInt(elementos[4].strip());

                Receita receita = new Receita(id);
                TelaLogadaMedicoUI.imprimirDocumento(receita);
            }
        });

        return painelReceita;
    }
}
