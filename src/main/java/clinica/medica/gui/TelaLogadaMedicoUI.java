package clinica.medica.gui;

import clinica.medica.consultas.Consulta;
import clinica.medica.database.*;
import clinica.medica.documentos.Exame;

import static clinica.medica.gui.LoginUI.frame;

import clinica.medica.documentos.Imprimivel;
import clinica.medica.documentos.Laudo;
import clinica.medica.documentos.Receita;
import clinica.medica.usuarios.Medico;
import clinica.medica.usuarios.Paciente;
import clinica.medica.usuarios.Usuario;

import com.toedter.calendar.JCalendar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class TelaLogadaMedicoUI {

    protected static JPanel telaPrescreverExame(TelaLogadaUI telaLogada) {
        JPanel painelExame = new JPanel();

        painelExame.setLayout(new GridBagLayout());
        painelExame.setSize(800, 600);

        JTextArea tempLabel = new JTextArea("Área dos exames");

        JButton novoExameButton = new JButton("Prescrever novo exame");
        JButton verificarExameButton = new JButton("Verificar exames");

        novoExameButton.addActionListener(telaLogada);
        verificarExameButton.addActionListener(telaLogada);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        constraints.gridy = 1;
        painelExame.add(tempLabel, constraints);

        constraints.gridy = 2;
        painelExame.add(novoExameButton, constraints);

        constraints.gridy = 3;
        painelExame.add(verificarExameButton, constraints);


        return painelExame;
    }

    protected static JPanel telaPrescreverLaudo(TelaLogadaUI telaLogada) {
        JPanel painelLaudo = new JPanel();

        painelLaudo.setLayout(new GridBagLayout());
        painelLaudo.setSize(800, 600);

        JTextArea tempLabel = new JTextArea("Área do Laudo");

        JButton novoLaudoButton = new JButton("Prescrever novo laudo");
        JButton verificarLaudoButton = new JButton("Verificar laudos");

        novoLaudoButton.addActionListener(telaLogada);
        verificarLaudoButton.addActionListener(telaLogada);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        constraints.gridy = 1;
        painelLaudo.add(tempLabel, constraints);

        constraints.gridy = 2;
        painelLaudo.add(novoLaudoButton, constraints);

        constraints.gridy = 3;
        painelLaudo.add(verificarLaudoButton, constraints);

        return painelLaudo;
    }

    protected static JPanel telaPrescreverReceita(Medico medicoLogado, TelaLogadaUI telaLogada) {
        JPanel painelReceita = new JPanel();

        painelReceita.setLayout(new GridBagLayout());
        painelReceita.setSize(800, 600);

        JTextArea tempLabel = new JTextArea("Área das receitas");

        JButton novaReceitaButton = new JButton("Prescrever nova receita");
        JButton verificarReceitaButton = new JButton("Verificar receitas");

        novaReceitaButton.addActionListener(telaLogada);
        verificarReceitaButton.addActionListener(telaLogada);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        constraints.gridy = 1;
        painelReceita.add(tempLabel, constraints);

        constraints.gridy = 2;
        painelReceita.add(novaReceitaButton, constraints);

        constraints.gridy = 3;
        painelReceita.add(verificarReceitaButton, constraints);

        return painelReceita;
    }

    
    protected static JPanel telaAgendarConsulta(Medico medicoLogado, TelaLogadaUI telaLogada) {
        JPanel painelConsulta = new JPanel();

        painelConsulta.setLayout(new GridBagLayout());
        painelConsulta.setSize(800, 600);
        
        
        JTextArea tempLabel = new JTextArea("Área da Consulta");

        JButton novaConsultaButton = new JButton("Agendar novo encaixe");
        JButton verificarConsultaButton = new JButton("Verificar consultas");

        verificarConsultaButton.addActionListener(telaLogada);
        novaConsultaButton.addActionListener(telaLogada);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        constraints.gridy = 1;
        painelConsulta.add(tempLabel, constraints);

        constraints.gridy = 2;
        painelConsulta.add(novaConsultaButton, constraints);

        constraints.gridy = 3;
        painelConsulta.add(verificarConsultaButton, constraints);
        
        return painelConsulta;
    }

    protected static JPanel telaNovoEncaixe(Medico medicoLogado,Date data, TelaLogadaUI telaLogada) {
        JPanel painelEncaixe = new JPanel();

        painelEncaixe.setLayout(new GridBagLayout());
        //painelConsulta.setSize(100, 100);

        JTextArea tempLabel = new JTextArea("Nova Encaixe");
        tempLabel.setEditable(false);

        JLabel dataLabel = new JLabel("Data do encaixe");
        JLabel horariolabel = new JLabel("Horário");
        JLabel medicoLabel = new JLabel("Medico");
        JLabel cpfPacienteLabel = new JLabel("CPF do paciente");
        JLabel comentarioLabel = new JLabel("Descrição");
        JLabel motivoEmergencia = new JLabel("Motivo da emergência");


        JFormattedTextField cpfField = CadastroUI.inicializaCpf();

        JTextField dataField = new JTextField(20);
        dataField.setText(data.toString());
        dataField.setEditable(false);
        dataField.setEnabled(false);

        String[] horariosDisponiveis = {"8","9","10","11","12","13","14","15","16","17"};
        JComboBox<String> horariosCombo = new JComboBox<>(horariosDisponiveis);

        JTextField medicoField = new JTextField(20);
        medicoField.setText(medicoLogado.getNome());
        medicoField.setEditable(false);
        medicoField.setEnabled(false);

        JTextArea comentarioArea = new JTextArea(10, 20);
        comentarioArea.setSize(new Dimension(20, 20));
        comentarioArea.setEditable(true);
        comentarioArea.setLineWrap(true);
        comentarioArea.setWrapStyleWord(true);

        JTextArea motivoArea = new JTextArea(10, 20);
        comentarioArea.setSize(new Dimension(20, 20));
        comentarioArea.setEditable(true);
        comentarioArea.setLineWrap(true);
        comentarioArea.setWrapStyleWord(true);



        JButton cadastrarEncaixeButton = new JButton("Agendar encaixe");
        JButton voltarButton = new JButton("Voltar");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        //constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;


        constraints.gridy = 1;
        painelEncaixe.add(tempLabel, constraints);


        constraints.gridy = 2;
        painelEncaixe.add(dataLabel, constraints);

        constraints.gridy = 3;
        painelEncaixe.add(dataField, constraints);

        constraints.gridy = 4;
        painelEncaixe.add(horariolabel, constraints);

        constraints.gridy = 5;
        painelEncaixe.add(horariosCombo, constraints);

        constraints.gridy = 6;
        painelEncaixe.add(cpfPacienteLabel, constraints);

        constraints.gridy = 7;
        painelEncaixe.add(cpfField, constraints);

        constraints.gridy = 8;
        painelEncaixe.add(medicoLabel, constraints);

        constraints.gridy = 9;
        painelEncaixe.add(medicoField, constraints);

        constraints.gridy = 10;
        painelEncaixe.add(comentarioLabel, constraints);

        constraints.gridy = 11;
        painelEncaixe.add(comentarioArea, constraints);

        constraints.gridy = 12;
        painelEncaixe.add(motivoEmergencia, constraints);

        constraints.gridy = 13;
        painelEncaixe.add(motivoArea, constraints);

        constraints.gridy = 14;
        painelEncaixe.add(cadastrarEncaixeButton, constraints);

        constraints.gridy = 15;
        painelEncaixe.add(voltarButton, constraints);

        cadastrarEncaixeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String cpf = cpfField.getText();
                cpf = cpf.replaceAll("[.-]", "");
                String conteudo = comentarioArea.getText();
                String motivo = motivoArea.getText();
                String horario = (String) horariosCombo.getSelectedItem();
                if (ConsultaSQL.salvarEncaixe(data, medicoLogado.getCpf(), cpf,conteudo,motivo, horario)) {
                    JOptionPane.showMessageDialog(painelEncaixe, "Agendamento de encaixe realizado com sucesso!");
                    telaLogada.atualizaPainel(medicoLogado);
                }

            }
        });

        voltarButton.addActionListener(telaLogada);

        return painelEncaixe;
    }

    protected  static JPanel telaAgendarEncaixe(Medico medicoLogado, TelaLogadaUI telaLogada){
        JPanel painelConsulta = new JPanel();

        painelConsulta.setLayout(new GridLayout(3,2));
        painelConsulta.setSize(800, 600);
        painelConsulta.setBackground(Color.WHITE);

        GridBagConstraints constraints = new GridBagConstraints();

        JTextArea tempLabel = new JTextArea("Agendar encaixe");
        JLabel nomeMedicoLabel = new JLabel("Escolha uma data para agendar o encaixe");

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

        JButton agendarConsultaButton = new JButton("Agendar o encaixe");

        painelConsulta.add(agendarConsultaButton);

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
                    telaLogada.getContentPanel().add(telaNovoEncaixe(medicoLogado, data, telaLogada), "Agendar o encaixe");
                    telaLogada.getCardLayout().show(telaLogada.getContentPanel(), "Agendar o encaixe");
                }
            }
        }

        MeuPropertyListener propertyListener = new MeuPropertyListener();
        calendar.addPropertyChangeListener(propertyListener);

        MeuPropertyListener.MeuMouseListener meuMouseListener = propertyListener.new MeuMouseListener();
        agendarConsultaButton.addMouseListener(meuMouseListener);

        return painelConsulta;

    }

    protected static JPanel telaNovoExame(Medico medicoLogado, TelaLogadaUI telaLogada) {
        JPanel painelExame = new JPanel();

        painelExame.setLayout(new GridBagLayout());
        painelExame.setSize(800, 600);

        JTextArea tempLabel = new JTextArea("Novo exame");
        tempLabel.setEditable(false);

        JLabel tipoLabel = new JLabel("Tipo de exame");
        JLabel cpfPacienteLabel = new JLabel("CPF do paciente");
        JLabel comentarioLabel = new JLabel("Comentários");

        JFormattedTextField cpfField = CadastroUI.inicializaCpf();

        JTextField tipoField = new JTextField(20);
        JTextArea comentarioArea = new JTextArea(20, 40);
        comentarioArea.setEditable(true);
        comentarioArea.setLineWrap(true);
        comentarioArea.setWrapStyleWord(true);

        JButton cadastrarExameButton = new JButton("Cadastrar exame");
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(telaLogada);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        constraints.gridy = 1;
        painelExame.add(tempLabel, constraints);

        constraints.gridy = 2;
        painelExame.add(tipoLabel, constraints);

        constraints.gridy = 3;
        painelExame.add(tipoField, constraints);

        constraints.gridy = 4;
        painelExame.add(cpfPacienteLabel, constraints);

        constraints.gridy = 5;
        painelExame.add(cpfField, constraints);

        constraints.gridy = 6;
        painelExame.add(comentarioLabel, constraints);

        constraints.gridy = 7;
        painelExame.add(comentarioArea, constraints);

        constraints.gridy = 8;
        painelExame.add(cadastrarExameButton, constraints);

        constraints.gridy = 9;
        painelExame.add(voltarButton, constraints);


        cadastrarExameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String tipo = tipoField.getText();
                String cpf = cpfField.getText();
                cpf = cpf.replaceAll("[.-]", "");
                String comentario = comentarioArea.getText();
                if (MedicosSQL.cadastrarNovoExame(tipo, cpf, medicoLogado.getCpf(), new Date(Calendar.getInstance().getTime().getTime()), comentario, painelExame)) {
                    JOptionPane.showMessageDialog(painelExame, "Cadastro de exame realizado com sucesso!");
                    telaLogada.atualizaPainel(medicoLogado);
                }
            }
        });
        return painelExame;
    }

    protected static JPanel telaNovoLaudo(Medico medicoLogado, Exame exame, TelaLogadaUI telaLogada) {
        JPanel painelLaudo = new JPanel();

        painelLaudo.setLayout(new GridBagLayout());
        painelLaudo.setSize(800, 600);

        JTextArea tempLabel = new JTextArea("Novo Laudo");
        tempLabel.setEditable(false);

        JLabel clinicaLabel = new JLabel("Clínica UNESP");
        JLabel tipoLabel = new JLabel("Tipo de exame");
        JLabel cpfPacienteLabel = new JLabel("CPF do paciente");
        JLabel comentarioLabel = new JLabel("Conclusão");

        JFormattedTextField cpfField = CadastroUI.inicializaCpf();
        cpfField.setText(exame.getPaciente().getCpf());
        cpfField.setEditable(false);
        cpfField.setEnabled(false);

        JTextField tipoField = new JTextField(20);
        tipoField.setText(exame.getTipo());
        tipoField.setEditable(false);
        tipoField.setEnabled(false);


        JTextArea comentarioArea = new JTextArea(20, 40);
        comentarioArea.setEditable(true);
        comentarioArea.setLineWrap(true);
        comentarioArea.setWrapStyleWord(true);

        JButton cadastrarLaudoButton = new JButton("Cadastrar laudo");
        JButton voltarButton = new JButton("Voltar");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;


        constraints.gridy = 1;
        painelLaudo.add(tempLabel, constraints);

        constraints.gridy = 2;
        painelLaudo.add(clinicaLabel, constraints);

        constraints.gridy = 3;
        painelLaudo.add(tipoLabel, constraints);

        constraints.gridy = 4;
        painelLaudo.add(tipoField, constraints);

        constraints.gridy = 5;
        painelLaudo.add(cpfPacienteLabel, constraints);

        constraints.gridy = 6;
        painelLaudo.add(cpfField, constraints);

        constraints.gridy = 7;
        painelLaudo.add(comentarioLabel, constraints);

        constraints.gridy = 8;
        painelLaudo.add(comentarioArea, constraints);

        constraints.gridy = 9;
        painelLaudo.add(cadastrarLaudoButton, constraints);

        constraints.gridy = 10;
        painelLaudo.add(voltarButton, constraints);

        cadastrarLaudoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String tipo = tipoField.getText();
                String cpf = cpfField.getText();
                cpf = cpf.replaceAll("[.-]", "");
                String conteudo = comentarioArea.getText();
                if (MedicosSQL.cadastrarNovoLaudo(exame.getId(), cpf, medicoLogado.getCpf(), new Date(Calendar.getInstance().getTime().getTime()), conteudo, painelLaudo)) {
                    JOptionPane.showMessageDialog(painelLaudo, "Cadastro de Laudo realizado com sucesso!");
                    tipoField.setText("");
                    cpfField.setText("");
                    comentarioArea.setText("");
                    telaLogada.atualizaPainel(medicoLogado);
                }

            }
        });

        voltarButton.addActionListener(telaLogada);

        return painelLaudo;
    }

    protected static JPanel telaNovaReceita(Medico medicoLogado, String pacienteCpf, TelaLogadaUI telaLogada) {
        JPanel painelReceita = new JPanel();

        painelReceita.setLayout(new GridBagLayout());
        painelReceita.setSize(800, 600);

        JTextArea tempLabel = new JTextArea("Nova receita");
        tempLabel.setEditable(false);

        JLabel clinicaLabel = new JLabel("Clínica UNESP");
        JLabel remedioLabel = new JLabel("Nome do remedio");
        JLabel cpfPacienteLabel = new JLabel("CPF do paciente");
        JLabel detalhesLabel = new JLabel("Detalhes");

        JFormattedTextField cpfField = CadastroUI.inicializaCpf();
        cpfField.setText(pacienteCpf);
        cpfField.setEditable(false);
        cpfField.setEnabled(false);

        JTextField remedioField = new JTextField(20);
        remedioField.setEditable(true);
        remedioField.setEnabled(true);

        JTextArea detalhesArea = new JTextArea(20, 40);
        detalhesArea.setEditable(true);
        detalhesArea.setLineWrap(true);
        detalhesArea.setWrapStyleWord(true);

        JButton cadastrarReceitaButton = new JButton("Cadastrar receita");
        JButton voltarButton = new JButton("Voltar");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        constraints.gridy = 1;
        painelReceita.add(tempLabel, constraints);

        constraints.gridy = 2;
        painelReceita.add(clinicaLabel, constraints);

        constraints.gridy = 3;
        painelReceita.add(cpfPacienteLabel, constraints);

        constraints.gridy = 4;
        painelReceita.add(cpfField, constraints);

        constraints.gridy = 5;
        painelReceita.add(remedioLabel, constraints);

        constraints.gridy = 6;
        painelReceita.add(remedioField, constraints);

        constraints.gridy = 7;
        painelReceita.add(detalhesLabel, constraints);

        constraints.gridy = 8;
        painelReceita.add(detalhesArea, constraints);


        constraints.gridy = 9;
        painelReceita.add(cadastrarReceitaButton, constraints);

        constraints.gridy = 10;
        painelReceita.add(voltarButton, constraints);

        cadastrarReceitaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String remedio = remedioField.getText();
                String cpf = cpfField.getText();
                cpf = cpf.replaceAll("[.-]", "");
                String detalhes = detalhesArea.getText();
                Receita receita = new Receita(medicoLogado.getCpf(), cpf, remedio, new Date(Calendar.getInstance().getTime().getTime()), detalhes);

                if (ReceitasSQL.cadastrarReceita(receita, painelReceita)) {
                    JOptionPane.showMessageDialog(painelReceita, "Cadastro de Receita realizado com sucesso!");
                    remedioField.setText("");
                    cpfField.setText("");
                    detalhesArea.setText("");
                    telaLogada.atualizaPainel(medicoLogado);
                }
            }
        });

        voltarButton.addActionListener(telaLogada);

        return painelReceita;
    }

    protected static JPanel showExames(Medico medicoLogado, TelaLogadaUI telaLogada) {
        ArrayList<Exame> exames = ExamesSQL.verificarExames(medicoLogado.getCpf());
        int i = 0;
        JPanel painelExame = new JPanel();

        painelExame.setLayout(new GridBagLayout());
        painelExame.setSize(800, 600);

        JTextArea tempLabel = new JTextArea("Exames");
        JButton imprimirExameButton = new JButton("Visualizar exame");
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(telaLogada);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        String[] listaExame = new String[exames.size()];

        for (Exame ex : exames) {
            listaExame[i] = "Exame - " + ex.getPaciente().getNome() + " - " + ex.getTipo() + " - " + ex.getId();
            i++;
        }

        JList<String> list = new JList<>(listaExame);
        JScrollPane scrollPanel = new JScrollPane(list);

        scrollPanel.setPreferredSize(new Dimension(600, 400));
        constraints.gridy = 0;
        painelExame.add(tempLabel, constraints);
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
                TelaLogadaMedicoUI.imprimirDocumento(exame);
            }
        });

        return painelExame;
    }

    protected static JPanel showExamesLaudo(Medico medicoLogado, TelaLogadaUI telaLogada) {
        ArrayList<Exame> exames = ExamesSQL.verificarExames(medicoLogado.getCpf());
        int i = 0;

        JPanel painelExame = new JPanel();

        painelExame.setLayout(new GridBagLayout());
        painelExame.setSize(800, 600);

        JTextArea tempLabel = new JTextArea("Exames");

        JButton utilizarExameButton = new JButton("Prescrever laudo para esse exame");
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(telaLogada);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        String[] listaExame = new String[exames.size()];

        for (Exame ex : exames) {
            listaExame[i] = "Exame - " + ex.getPaciente().getNome() + " - " + ex.getTipo() + " - " + ex.getId();
            i++;
        }

        JList<String> list = new JList<>(listaExame);
        JScrollPane scrollPanel = new JScrollPane(list);

        scrollPanel.setPreferredSize(new Dimension(600, 400));
        constraints.gridy = 0;
        painelExame.add(tempLabel, constraints);
        constraints.gridy = 1;
        painelExame.add(scrollPanel, constraints);
        constraints.gridy = 2;
        painelExame.add(utilizarExameButton, constraints);
        constraints.gridy = 3;
        painelExame.add(voltarButton, constraints);

        utilizarExameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String exameSelecionado = list.getSelectedValue();
                String[] elementos = exameSelecionado.split("-");
                int id = Integer.parseInt(elementos[3].strip());
                Exame exame = new Exame(id);
                telaLogada.getContentPanel().add(telaNovoLaudo(medicoLogado, exame, telaLogada), "Prescrever laudo para esse exame");
                telaLogada.getCardLayout().show(telaLogada.getContentPanel(), "Prescrever laudo para esse exame");
            }
        });

        return painelExame;
    }

    protected static JPanel showConsultas(Medico medicoLogado, TelaLogadaUI telaLogada) {
        ArrayList<Consulta> consultas = ConsultaSQL.selectAllConsultasFromMedico(medicoLogado.getCpf());
        int i = 0;
        JPanel painelConsulta = new JPanel();

        painelConsulta.setLayout(new GridBagLayout());
        painelConsulta.setSize(800, 600);

        JTextArea tempLabel = new JTextArea("Consultas");
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
        for (Consulta rc: consultas) {
            if(!rc.isRealizada())
                listaConsulta[i] = "Consulta marcada - " + "Paciente: " + rc.getPaciente().getNome() + " - " + "Data: " + rc.getData().toString() + " - " + "Horário: " + rc.getHorario() + " horas" + " - " + rc.getId();
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
                if(consulta.getMotivoCancelamento().equals(""))
                    JOptionPane.showMessageDialog(painelConsulta, "Comentários do paciente: " + consulta.getDescricao(), "Comentarios da consulta", JOptionPane.INFORMATION_MESSAGE);
                else if(!consulta.getMotivoCancelamento().equals(""))
                    JOptionPane.showMessageDialog(painelConsulta, "Comentários do paciente: " + consulta.getDescricao() + "\n\n Motivo do cancelamento: " + consulta.getMotivoCancelamento(), "Comentarios da consulta", JOptionPane.INFORMATION_MESSAGE);
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

                int option = JOptionPane.showOptionDialog(painelConsulta,radioPanel, "Selecione uma opção", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

                if(option == JOptionPane.OK_OPTION){
                    if(feitaButton.isSelected()){
                        ConsultaSQL.atualizaConsulta(consulta.getId());
                        JOptionPane.showMessageDialog(painelConsulta, "Consulta finalizada com sucesso! ", "Consulta finalizada", JOptionPane.INFORMATION_MESSAGE);
                    }else if(canceladaButton.isSelected()){
                        JPanel fieldPanel = new JPanel();
                        fieldPanel.setLayout(new BorderLayout());
                        JLabel motivoLabel = new JLabel("Digite o motivo do cancelamento");
                        JTextArea cancelamento = new JTextArea(10,10);
                        cancelamento.setEditable(true);
                        cancelamento.setLineWrap(true);
                        fieldPanel.add(motivoLabel,BorderLayout.NORTH);
                        fieldPanel.add(cancelamento,BorderLayout.SOUTH);
                        int option2 = JOptionPane.showOptionDialog(painelConsulta,fieldPanel, "Cancelamento", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
                        if(option2 == JOptionPane.OK_OPTION){
                            String motivo = cancelamento.getText();
                            ConsultaSQL.atualizaConsulta(consulta.getId(),motivo);
                            JOptionPane.showMessageDialog(painelConsulta, "Consulta cancelada com sucesso!", "Consulta cancelada", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        });

        return painelConsulta;
    }


    protected static JPanel showLaudos(Medico medicoLogado, TelaLogadaUI telaLogada) {
        ArrayList<Laudo> laudos = LaudosSQL.verificarLaudos(medicoLogado.getCpf());
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

        scrollPanel.setPreferredSize(new Dimension(600, 400));
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
                TelaLogadaMedicoUI.imprimirDocumento(laudo);
            }
        });

        return painelLaudo;
    }

    protected static JPanel showPacientesReceita(Medico medicoLogado, TelaLogadaUI telaLogada) {
        ArrayList<Paciente> pacientes = UsuariosSQL.selectAllPacientes();
        int i = 0;

        JPanel painelExame = new JPanel();

        painelExame.setLayout(new GridBagLayout());
        painelExame.setSize(800, 600);

        JTextArea tempLabel = new JTextArea("Pacientes");

        JButton utilizarExameButton = new JButton("Prescrever receita para esse paciente");
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(telaLogada);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        String[] listaPacientes = new String[pacientes.size()];

        for (Paciente paciente : pacientes) {
            listaPacientes[i] = paciente.getNome() + " - " + paciente.getCpf();
            i++;
        }

        JList<String> list = new JList<>(listaPacientes);
        JScrollPane scrollPanel = new JScrollPane(list);

        scrollPanel.setPreferredSize(new Dimension(600, 400));
        constraints.gridy = 0;
        painelExame.add(tempLabel, constraints);
        constraints.gridy = 1;
        painelExame.add(scrollPanel, constraints);
        constraints.gridy = 2;
        painelExame.add(utilizarExameButton, constraints);
        constraints.gridy = 3;
        painelExame.add(voltarButton, constraints);

        utilizarExameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String exameSelecionado = list.getSelectedValue();
                String[] elementos = exameSelecionado.split("-");
                String pacienteCpf = elementos[1].strip();
                telaLogada.getContentPanel().add(telaNovaReceita(medicoLogado, pacienteCpf, telaLogada), "Prescrever receita para esse paciente");
                telaLogada.getCardLayout().show(telaLogada.getContentPanel(), "Prescrever receita para esse paciente");
            }
        });

        return painelExame;
    }

    protected static JPanel showReceitas(Medico medicoLogado, TelaLogadaUI telaLogada) {
        ArrayList<Receita> receitas = ReceitasSQL.selectAllReceitasFromMedico(medicoLogado.getCpf());
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
            listaReceita[i] = "Receita - " + rc.getPaciente().getNome() + " - " + rc.getNomeDoRemedio() + " - " + rc.getId();
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
                int id = Integer.parseInt(elementos[3].strip());

                Receita receita = new Receita(id);
                TelaLogadaMedicoUI.imprimirDocumento(receita);
            }
        });

        return painelReceita;
    }

    protected static <T extends Imprimivel> void imprimirDocumento(T documento) {
        JFrame documentFrame = new JFrame(documento.imprimeTipo());

        documentFrame.setLayout(new BorderLayout());
        documentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GridBagConstraints constraints = new GridBagConstraints();

        // ============ Painéis ============
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new GridBagLayout());
        logoPanel.setBackground(Color.WHITE);
        logoPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));

        JPanel infosTopoPanel = new JPanel();
        infosTopoPanel.setLayout(new GridBagLayout());
        infosTopoPanel.setBackground(Color.WHITE);

        JPanel topoPanel = new JPanel();
        topoPanel.setLayout(new BoxLayout(topoPanel, BoxLayout.X_AXIS));
        topoPanel.setBackground(Color.WHITE);

        JPanel infosCentralPanel = new JPanel(new GridBagLayout());
        infosCentralPanel.setLayout(new GridBagLayout());
        infosCentralPanel.setBackground(Color.WHITE);
        infosCentralPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
        centralPanel.setBackground(Color.WHITE);
        centralPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));

        JPanel finalPanel = new JPanel();
        finalPanel.setLayout(new GridBagLayout());
        finalPanel.setBackground(Color.WHITE);

        // ---------------------------------- TOPO ------------------------------------------

        // ============ Logo ============
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 2;

        try {
            BufferedImage logo = ImageIO.read(TelaLogadaMedicoUI.class.getResourceAsStream("/images/logoDocs.png"));
            ImageIcon logoIcon = new ImageIcon(logo);
            JLabel logoLabel = new JLabel(logoIcon);
            logoPanel.add(logoLabel, constraints);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ============ Tipo do Documento ============
        JLabel tipoDocumentoLabel = new JLabel(documento.imprimeTipo());

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.VERTICAL;
        infosTopoPanel.add(tipoDocumentoLabel, constraints);

        // ============ Endereço Clínica ============
        JLabel enderecoClinicaLabel = new JLabel("Rua Cristovão Colombo - (17) 3222-3174");

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        infosTopoPanel.add(enderecoClinicaLabel, constraints);

        topoPanel.add(logoPanel);
        topoPanel.add(infosTopoPanel);

        // ---------------------------------- MEIO ------------------------------------------
        constraints.insets = new Insets(0, 0, 0, 0);

        // ============ Info Documento ============
        JLabel docInfoLabel = new JLabel(documento.imprimeInfo());

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        infosCentralPanel.add(docInfoLabel, constraints);

        // ============ Infos Paciente ============
        JLabel nomePacienteLabel = new JLabel(documento.imprimeNomePaciente());
        JLabel idadeLabel = new JLabel(documento.imprimeIdade());
        JLabel sexoLabel = new JLabel(documento.imprimeSexo());
        JLabel dataLabel = new JLabel(documento.imprimeData());

        constraints.gridx = 0;
        constraints.gridy = 1;
        infosCentralPanel.add(nomePacienteLabel, constraints);
        constraints.gridy = 2;
        infosCentralPanel.add(idadeLabel, constraints);
        constraints.gridy = 3;
        infosCentralPanel.add(sexoLabel, constraints);
        constraints.gridy = 4;
        infosCentralPanel.add(dataLabel, constraints);

        centralPanel.add(infosCentralPanel);

        // ============ Comentário do Médico ============
        JLabel comentariosLabel = new JLabel("<html>" + documento.imprimeComentarios() + "</html>");
        comentariosLabel.setBackground(Color.WHITE);
        comentariosLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        centralPanel.add(comentariosLabel);

        // ---------------------------------- Final ------------------------------------------

        // ============ Infos do Médico ============
        JLabel medicoLabel = new JLabel(documento.imprimeNomeMedico());
        JLabel infoMedicoLabel = new JLabel(documento.imprimeCrmAtuacaoMedico());

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        finalPanel.add(medicoLabel, constraints);
        constraints.gridy = 1;
        finalPanel.add(infoMedicoLabel, constraints);

        // ============ Fontes ============
        Font fonteGrande = new Font(enderecoClinicaLabel.getFont().getFontName(), enderecoClinicaLabel.getFont().getStyle(), 30);
        Font fontePequena = new Font(enderecoClinicaLabel.getFont().getFontName(), enderecoClinicaLabel.getFont().getStyle(), 15);

        tipoDocumentoLabel.setFont(fonteGrande);
        enderecoClinicaLabel.setFont(fonteGrande);
        docInfoLabel.setFont(fontePequena);
        nomePacienteLabel.setFont(fontePequena);
        idadeLabel.setFont(fontePequena);
        sexoLabel.setFont(fontePequena);
        dataLabel.setFont(fontePequena);
        comentariosLabel.setFont(fontePequena);

        // ============ Ajuste Frame ============
        documentFrame.add(topoPanel, BorderLayout.NORTH);
        documentFrame.add(centralPanel, BorderLayout.CENTER);
        documentFrame.add(finalPanel, BorderLayout.SOUTH);
        documentFrame.setSize(1000, 800);
        //documentFrame.pack();
        documentFrame.setVisible(true);
    }
}