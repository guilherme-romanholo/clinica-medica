package clinica.medica.gui;

import clinica.medica.database.MedicosSQL;
import clinica.medica.database.ReceitasSQL;
import clinica.medica.database.UsuariosSQL;
import clinica.medica.documentos.Exame;

import static clinica.medica.gui.LoginUI.frame;

import clinica.medica.documentos.Laudo;
import clinica.medica.documentos.Receita;
import clinica.medica.usuarios.Medico;
import clinica.medica.usuarios.Paciente;
import clinica.medica.usuarios.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
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

    protected static JPanel telaAgendarConsulta() {
        JPanel painelConsulta = new JPanel();

        painelConsulta.setLayout(new GridBagLayout());
        painelConsulta.setSize(800, 600);

        JTextArea tempLabel = new JTextArea("Área da Consulta");

        JButton novaConsultaButton = new JButton("Agendar nova consulta");
        JButton verificarConsultaButton = new JButton("Verificar consultas");

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
                if (MedicosSQL.cadastrarNovoExame(tipo, cpf, medicoLogado.getCpf(), new Date(Calendar.getInstance().getTime().getTime()), comentario)) {
                    JOptionPane.showMessageDialog(painelExame, "Cadastro de exame realizado com sucesso!");
                    telaLogada.atualizaPainel(medicoLogado);
                } else {
                    JOptionPane.showMessageDialog(painelExame, "Não foi possível cadastrar o exame, tente novamente!", "ERRO", JOptionPane.ERROR_MESSAGE);
                    tipoField.setText("");
                    cpfField.setText("");
                    comentarioArea.setText("");
                }
            }
        });
        return painelExame;
    }

    protected static JPanel telaNovoLaudo(Medico medicoLogado, Exame exame, TelaLogadaUI telaLogada) {
        JPanel painelExame = new JPanel();

        painelExame.setLayout(new GridBagLayout());
        painelExame.setSize(800, 600);

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
        painelExame.add(tempLabel, constraints);

        constraints.gridy = 2;
        painelExame.add(clinicaLabel, constraints);

        constraints.gridy = 3;
        painelExame.add(tipoLabel, constraints);

        constraints.gridy = 4;
        painelExame.add(tipoField, constraints);

        constraints.gridy = 5;
        painelExame.add(cpfPacienteLabel, constraints);

        constraints.gridy = 6;
        painelExame.add(cpfField, constraints);

        constraints.gridy = 7;
        painelExame.add(comentarioLabel, constraints);

        constraints.gridy = 8;
        painelExame.add(comentarioArea, constraints);

        constraints.gridy = 9;
        painelExame.add(cadastrarLaudoButton, constraints);

        constraints.gridy = 10;
        painelExame.add(voltarButton, constraints);

        cadastrarLaudoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String tipo = tipoField.getText();
                String cpf = cpfField.getText();
                cpf = cpf.replaceAll("[.-]", "");
                String conteudo = comentarioArea.getText();
                if (MedicosSQL.cadastrarNovoLaudo(exame.getId(), cpf, medicoLogado.getCpf(), new Date(Calendar.getInstance().getTime().getTime()), conteudo)) {
                    JOptionPane.showMessageDialog(painelExame, "Cadastro de Laudo realizado com sucesso!");
                    tipoField.setText("");
                    cpfField.setText("");
                    comentarioArea.setText("");
                    telaLogada.atualizaPainel(medicoLogado);
                } else {
                    JOptionPane.showMessageDialog(painelExame, "Não foi possível cadastrar o laudo, tente novamente!", "ERRO", JOptionPane.ERROR_MESSAGE);
                    tipoField.setText("");
                    cpfField.setText("");
                    comentarioArea.setText("");
                }
            }
        });

        voltarButton.addActionListener(telaLogada);

        return painelExame;
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

                if (ReceitasSQL.cadastrarReceita(receita)) {
                    JOptionPane.showMessageDialog(painelReceita, "Cadastro de Receita realizado com sucesso!");
                    remedioField.setText("");
                    cpfField.setText("");
                    detalhesArea.setText("");
                    telaLogada.atualizaPainel(medicoLogado);
                } else {
                    JOptionPane.showMessageDialog(painelReceita, "Não foi possível cadastrar o laudo, tente novamente!", "ERRO", JOptionPane.ERROR_MESSAGE);
                    remedioField.setText("");
                    cpfField.setText("");
                    detalhesArea.setText("");
                }
            }
        });

        voltarButton.addActionListener(telaLogada);

        return painelReceita;
    }

    protected static JPanel showExames(Medico medicoLogado, TelaLogadaUI telaLogada) {
        ArrayList<Exame> exames = MedicosSQL.verificarExames(medicoLogado.getCpf());
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
                TelaLogadaMedicoUI.imprimirExame(exame);
            }
        });

        return painelExame;
    }

    protected static JPanel showExamesLaudo(Medico medicoLogado, TelaLogadaUI telaLogada) {
        ArrayList<Exame> exames = MedicosSQL.verificarExames(medicoLogado.getCpf());
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


    protected static JPanel showLaudos(Medico medicoLogado, TelaLogadaUI telaLogada) {
        ArrayList<Laudo> laudos = MedicosSQL.verificarLaudos(medicoLogado.getCpf());
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
                TelaLogadaMedicoUI.imprimirLaudo(laudo);
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
        ArrayList<Receita> receitas = ReceitasSQL.selectAllReceitas();
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
                TelaLogadaMedicoUI.imprimirReceita(receita);
            }
        });

        return painelReceita;
    }

    protected static void imprimirExame(Exame exame) {
        JFrame exameFrame = new JFrame("Exame - " + exame.getPaciente().getNome() + " - " + exame.getTipo());

        exameFrame.setLayout(new BorderLayout());
        exameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        exameFrame.setSize(1200, 800);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        // ============ Painéis ============
        JPanel topoPanel = new JPanel();
        topoPanel.setLayout(new GridBagLayout());
        topoPanel.setBackground(Color.WHITE);

        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new GridBagLayout());
        centralPanel.setBackground(Color.WHITE);
        centralPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));

        JPanel finalPanel = new JPanel();
        finalPanel.setLayout(new GridBagLayout());
        finalPanel.setBackground(Color.WHITE);

        // ---------------------------------- TOPO ------------------------------------------

        // ============ Logo ============
        constraints.gridx = 0;
        constraints.gridy = 0;

        try {
            BufferedImage logo = ImageIO.read(TelaLogadaMedicoUI.class.getResourceAsStream("/images/logoDocs.png"));
            ImageIcon logoIcon = new ImageIcon(logo);
            JLabel logoLabel = new JLabel(logoIcon);
            topoPanel.add(logoLabel, constraints);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ============ Endereço Clínica ============
        JLabel enderecoClinicaLabel = new JLabel("Rua Cristovão Colombo - (17) 3222-3174");

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        topoPanel.add(enderecoClinicaLabel, constraints);

        // ---------------------------------- MEIO ------------------------------------------

        // ============ Tipo Exame ============
        JLabel tipoExameLabel = new JLabel(exame.getTipo() + " - Id: " + exame.getId());

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        centralPanel.add(tipoExameLabel, constraints);

        // ============ Infos Paciente ============
        JLabel nomePacienteLabel = new JLabel("Nome: " + exame.getPaciente().getNome());
        JLabel idadeLabel = new JLabel("Idade: " + exame.getPaciente().getIdade());
        JLabel sexoLabel = new JLabel("Sexo: " + exame.getPaciente().getSexo());
        JLabel dataLabel = new JLabel("Data do exame: " + exame.getData());

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        centralPanel.add(nomePacienteLabel, constraints);
        constraints.gridy = 2;
        centralPanel.add(idadeLabel, constraints);
        constraints.gridy = 3;
        centralPanel.add(sexoLabel, constraints);
        constraints.gridy = 4;
        centralPanel.add(dataLabel, constraints);

        // ============ Comentário do Médico ============
        JLabel comentarioLabel = new JLabel("<html>Comentário: " + exame.getComentario() + "</html>");
        comentarioLabel.setSize(200, 100);

        constraints.gridy = 5;
        centralPanel.add(comentarioLabel, constraints);

        // ---------------------------------- Final ------------------------------------------

        // ============ Infos do Médico ============
        JLabel medicoLabel = new JLabel("Dr. " + exame.getMedicoSolicitante().getNome());
        JLabel infoMedicoLabel = new JLabel("CRM " + exame.getMedicoSolicitante().getCRM() + " - " + exame.getMedicoSolicitante().getAreaAtuacao());

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        finalPanel.add(medicoLabel, constraints);
        constraints.gridy = 1;
        finalPanel.add(infoMedicoLabel, constraints);

        // ============ Fontes ============
        Font fonteGrande = new Font(enderecoClinicaLabel.getFont().getFontName(), enderecoClinicaLabel.getFont().getStyle(), 30);
        Font fontePequena = new Font(enderecoClinicaLabel.getFont().getFontName(), enderecoClinicaLabel.getFont().getStyle(), 15);

        enderecoClinicaLabel.setFont(fonteGrande);
        tipoExameLabel.setFont(fontePequena);
        nomePacienteLabel.setFont(fontePequena);
        idadeLabel.setFont(fontePequena);
        sexoLabel.setFont(fontePequena);
        dataLabel.setFont(fontePequena);
        comentarioLabel.setFont(fontePequena);

        // ============ Ajuste Frame ============
        exameFrame.add(topoPanel, BorderLayout.NORTH);
        exameFrame.add(centralPanel, BorderLayout.CENTER);
        exameFrame.add(finalPanel, BorderLayout.SOUTH);
        exameFrame.setVisible(true);
    }
    protected static void imprimirLaudo(Laudo laudo) {
        JFrame laudoFrame = new JFrame("Exame - " + laudo.getPaciente().getNome() + " - " + laudo.getExame().getTipo());

        laudoFrame.setLayout(new BorderLayout());
        laudoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        laudoFrame.setSize(1200, 800);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        // ============ Painéis ============
        JPanel topoPanel = new JPanel();
        topoPanel.setLayout(new GridBagLayout());
        topoPanel.setBackground(Color.WHITE);

        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new GridBagLayout());
        centralPanel.setBackground(Color.WHITE);
        centralPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));

        JPanel finalPanel = new JPanel();
        finalPanel.setLayout(new GridBagLayout());
        finalPanel.setBackground(Color.WHITE);

        // ---------------------------------- TOPO ------------------------------------------

        // ============ Logo ============
        constraints.gridx = 0;
        constraints.gridy = 0;

        try {
            BufferedImage logo = ImageIO.read(TelaLogadaMedicoUI.class.getResourceAsStream("/images/logoDocs.png"));
            ImageIcon logoIcon = new ImageIcon(logo);
            JLabel logoLabel = new JLabel(logoIcon);
            topoPanel.add(logoLabel, constraints);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ============ Endereço Clínica ============
        JLabel enderecoClinicaLabel = new JLabel("Rua Cristovão Colombo - (17) 3222-3174");

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        topoPanel.add(enderecoClinicaLabel, constraints);

        // ---------------------------------- MEIO ------------------------------------------

        // ============ Tipo Exame ============
        JLabel tipoExameLabel = new JLabel(laudo.getExame().getTipo() + " - Id: " + laudo.getExame().getId());

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        centralPanel.add(tipoExameLabel, constraints);

        // ============ Infos Paciente ============
        JLabel nomePacienteLabel = new JLabel("Nome: " + laudo.getExame().getPaciente().getNome());
        JLabel idadeLabel = new JLabel("Idade: " + laudo.getExame().getPaciente().getIdade());
        JLabel sexoLabel = new JLabel("Sexo: " + laudo.getExame().getPaciente().getSexo());
        JLabel dataLabel = new JLabel("Data do exame: " + laudo.getExame().getData());

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        centralPanel.add(nomePacienteLabel, constraints);
        constraints.gridy = 2;
        centralPanel.add(idadeLabel, constraints);
        constraints.gridy = 3;
        centralPanel.add(sexoLabel, constraints);
        constraints.gridy = 4;
        centralPanel.add(dataLabel, constraints);

        // ============ Comentário do Médico ============
        JLabel comentarioLabel = new JLabel("<html>Conclusões: " + laudo.getConteudo() + "</html>");
        comentarioLabel.setSize(200, 100);

        constraints.gridy = 5;
        centralPanel.add(comentarioLabel, constraints);

        // ---------------------------------- Final ------------------------------------------

        // ============ Infos do Médico ============
        JLabel medicoLabel = new JLabel("Dr. " + laudo.getMedicoSolicitante().getNome());
        JLabel infoMedicoLabel = new JLabel("CRM " + laudo.getMedicoSolicitante().getCRM() + " - " + laudo.getMedicoSolicitante().getAreaAtuacao());

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        finalPanel.add(medicoLabel, constraints);
        constraints.gridy = 1;
        finalPanel.add(infoMedicoLabel, constraints);

        // ============ Fontes ============
        Font fonteGrande = new Font(enderecoClinicaLabel.getFont().getFontName(), enderecoClinicaLabel.getFont().getStyle(), 30);
        Font fontePequena = new Font(enderecoClinicaLabel.getFont().getFontName(), enderecoClinicaLabel.getFont().getStyle(), 15);

        enderecoClinicaLabel.setFont(fonteGrande);
        tipoExameLabel.setFont(fontePequena);
        nomePacienteLabel.setFont(fontePequena);
        idadeLabel.setFont(fontePequena);
        sexoLabel.setFont(fontePequena);
        dataLabel.setFont(fontePequena);
        comentarioLabel.setFont(fontePequena);

        // ============ Ajuste Frame ============
        laudoFrame.add(topoPanel, BorderLayout.NORTH);
        laudoFrame.add(centralPanel, BorderLayout.CENTER);
        laudoFrame.add(finalPanel, BorderLayout.SOUTH);
        laudoFrame.setVisible(true);
    }

    protected static void imprimirReceita(Receita receita) {
        JFrame receitaFrame = new JFrame("Receita de " + receita.getPaciente().getNome() + ", id - " + receita.getId());

        receitaFrame.setLayout(new BorderLayout());
        receitaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        receitaFrame.setSize(1200, 800);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        // ============ Painéis ============
        JPanel topoPanel = new JPanel();
        topoPanel.setLayout(new GridBagLayout());
        topoPanel.setBackground(Color.WHITE);

        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new GridBagLayout());
        centralPanel.setBackground(Color.WHITE);
        centralPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));

        JPanel finalPanel = new JPanel();
        finalPanel.setLayout(new GridBagLayout());
        finalPanel.setBackground(Color.WHITE);

        // ---------------------------------- TOPO ------------------------------------------

        // ============ Logo ============
        constraints.gridx = 0;
        constraints.gridy = 0;

        try {
            BufferedImage logo = ImageIO.read(TelaLogadaMedicoUI.class.getResourceAsStream("/images/logoDocs.png"));
            ImageIcon logoIcon = new ImageIcon(logo);
            JLabel logoLabel = new JLabel(logoIcon);
            topoPanel.add(logoLabel, constraints);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ============ Endereço Clínica ============
        JLabel enderecoClinicaLabel = new JLabel("Rua Cristovão Colombo - (17) 3222-3174");

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        topoPanel.add(enderecoClinicaLabel, constraints);

        // ---------------------------------- MEIO ------------------------------------------

        // ============ Tipo Exame ============
        JLabel remedioLabel = new JLabel("Remedio - " + receita.getNomeDoRemedio());

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        centralPanel.add(remedioLabel, constraints);

        // ============ Infos Paciente ============
        JLabel nomePacienteLabel = new JLabel("Nome: " + receita.getPaciente().getNome());
        JLabel idadeLabel = new JLabel("Idade: " + receita.getPaciente().getIdade());
        JLabel sexoLabel = new JLabel("Sexo: " + receita.getPaciente().getSexo());
        JLabel dataLabel = new JLabel("Data de prescrição: " + receita.getDataReceita());

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        centralPanel.add(nomePacienteLabel, constraints);
        constraints.gridy = 2;
        centralPanel.add(idadeLabel, constraints);
        constraints.gridy = 3;
        centralPanel.add(sexoLabel, constraints);
        constraints.gridy = 4;
        centralPanel.add(dataLabel, constraints);

        // ============ Comentário do Médico ============
        JLabel detalhesLabel = new JLabel("<html>Conclusões: " + receita.getDetalhes() + "</html>");
        detalhesLabel.setSize(200, 100);

        constraints.gridy = 5;
        centralPanel.add(detalhesLabel, constraints);

        // ---------------------------------- Final ------------------------------------------

        // ============ Infos do Médico ============
        JLabel medicoLabel = new JLabel("Dr. " + receita.getMedico().getNome());
        JLabel infoMedicoLabel = new JLabel("CRM " + receita.getMedico().getCRM() + " - " + receita.getMedico().getAreaAtuacao());

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        finalPanel.add(medicoLabel, constraints);
        constraints.gridy = 1;
        finalPanel.add(infoMedicoLabel, constraints);

        // ============ Fontes ============
        Font fonteGrande = new Font(enderecoClinicaLabel.getFont().getFontName(), enderecoClinicaLabel.getFont().getStyle(), 30);
        Font fontePequena = new Font(enderecoClinicaLabel.getFont().getFontName(), enderecoClinicaLabel.getFont().getStyle(), 15);

        enderecoClinicaLabel.setFont(fonteGrande);
        remedioLabel.setFont(fontePequena);
        nomePacienteLabel.setFont(fontePequena);
        idadeLabel.setFont(fontePequena);
        sexoLabel.setFont(fontePequena);
        dataLabel.setFont(fontePequena);
        detalhesLabel.setFont(fontePequena);

        // ============ Ajuste Frame ============
        receitaFrame.add(topoPanel, BorderLayout.NORTH);
        receitaFrame.add(centralPanel, BorderLayout.CENTER);
        receitaFrame.add(finalPanel, BorderLayout.SOUTH);
        receitaFrame.setVisible(true);
    }
}
