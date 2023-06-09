package clinica.medica.gui;

import clinica.medica.database.MedicosSQL;
import clinica.medica.documentos.Exame;

import static clinica.medica.gui.LoginUI.frame;

import clinica.medica.documentos.Laudo;
import clinica.medica.usuarios.Medico;

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

public class TelaLogadaMedicoUI {


    /**
     * Método privado para a implementação da tela do médico.
     *
     * @param medicoLogado Médico logado no momento.
     */
    protected static JPanel painelMedico(Medico medicoLogado) {
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
                if (MedicosSQL.cadastrarNovoExame(tipo, cpf, medicoLogado.getCRM(), new Date(Calendar.getInstance().getTime().getTime()), comentario)) {
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

    protected static void telaNovoLaudo(Medico medicoLogado,Exame exame) {
        JFrame laudoFrame = new JFrame("Cadastro de novo laudo");
        laudoFrame.setLayout(new GridBagLayout());
        laudoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        laudoFrame.setVisible(true);
        laudoFrame.setSize(1200, 800);


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

        constraints.gridy = 11;
        laudoFrame.add(painelExame, constraints);


        cadastrarLaudoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String tipo = tipoField.getText();
                String cpf = cpfField.getText();
                cpf = cpf.replaceAll("[.-]", "");
                String conteudo = comentarioArea.getText();
                if (MedicosSQL.cadastrarNovoLaudo(exame.getId(), cpf, medicoLogado.getCRM(), new Date(Calendar.getInstance().getTime().getTime()), conteudo)) {
                    JOptionPane.showMessageDialog(painelExame, "Cadastro de Laudo realizado com sucesso!");
                    tipoField.setText("");
                    cpfField.setText("");
                    comentarioArea.setText("");
                } else {
                    JOptionPane.showMessageDialog(painelExame, "Não foi possível cadastrar o exame, tente novamente!", "ERRO", JOptionPane.ERROR_MESSAGE);
                    tipoField.setText("");
                    cpfField.setText("");
                    comentarioArea.setText("");
                }
            }
        });

    }

    protected static JPanel showExames(Medico medicoLogado, TelaLogadaUI telaLogada) {
        ArrayList<Exame> exames = MedicosSQL.verificarExames(medicoLogado.getCRM());
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

        scrollPanel.setPreferredSize(new Dimension(800, 600));
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
                TelaLogadaMedicoUI.imprimirExame(medicoLogado, exame);
            }
        });

        return painelExame;
    }

    protected static JPanel showExamesLaudo(Medico medicoLogado, TelaLogadaUI telaLogada) {
        ArrayList<Exame> exames = MedicosSQL.verificarExames(medicoLogado.getCRM());
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

        scrollPanel.setPreferredSize(new Dimension(800, 600));
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
                telaNovoLaudo(medicoLogado,exame);
            }
        });

        return painelExame;
    }

    protected static JPanel showLaudos(Medico medicoLogado, TelaLogadaUI telaLogada) {
        ArrayList<Laudo> laudos = MedicosSQL.verificarLaudos(medicoLogado.getCRM());
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
                TelaLogadaMedicoUI.imprimirLaudo(medicoLogado, laudo);
            }
        });

        return painelLaudo;
    }
    protected static void imprimirExame(Medico medicoLogado, Exame exame) {
        JFrame exameFrame = new JFrame("Exame - " + exame.getPaciente().getNome() + " - " + exame.getTipo());

        exameFrame.setLayout(new GridBagLayout());
        exameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        exameFrame.setSize(1200, 800);
        exameFrame.setVisible(true);


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 1;

        JPanel topoPanel = new JPanel();
        JPanel meioPanel = new JPanel();
        JPanel ultimoPanel = new JPanel();

        topoPanel.setLayout(new GridBagLayout());
        meioPanel.setLayout(new GridBagLayout());
        ultimoPanel.setLayout(new GridBagLayout());
        
       /* try {
            BufferedImage logo = ImageIO.read(LoginUI.class.getResourceAsStream("/images/logo.png"));
            ImageIcon logoIcon = new ImageIcon(logo);
            JLabel logoLabel = new JLabel(logoIcon);
            topoPanel.add(logoLabel, BorderLayout.WEST);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        JLabel linhaLabel = new JLabel("___________________________________________________________________");
        JLabel enderecoClinicaLabel = new JLabel("Rua Cristovão Colombo - (17) 3222-3174");
        Font novaFonte = new Font(enderecoClinicaLabel.getFont().getFontName(), enderecoClinicaLabel.getFont().getStyle(), 30);
        enderecoClinicaLabel.setFont(novaFonte);

        constraints.gridy = 1;
        topoPanel.add(enderecoClinicaLabel, constraints);

        linhaLabel.setFont(novaFonte);
        constraints.gridy = 2;
        topoPanel.add(linhaLabel, constraints);

        constraints.gridy = 3;
        exameFrame.add(topoPanel, constraints);

        JLabel tipoExameLabel = new JLabel(exame.getTipo() + " - Id: " + exame.getId());

        constraints.gridy = 1;
        meioPanel.add(tipoExameLabel, constraints);

        JLabel nomePacienteLabel = new JLabel("Nome: " + exame.getPaciente().getNome());
        JLabel idadeLabel = new JLabel("Idade: " + exame.getPaciente().getIdade());
        JLabel sexoLabel = new JLabel("Sexo: " + exame.getPaciente().getSexo());
        JLabel dataLabel = new JLabel("Data do exame: " + exame.getData());

        constraints.gridy = 2;
        meioPanel.add(nomePacienteLabel, constraints);

        constraints.gridy = 3;
        meioPanel.add(idadeLabel, constraints);

        constraints.gridy = 4;
        meioPanel.add(sexoLabel,constraints);

        constraints.gridy = 5;
        meioPanel.add(dataLabel, constraints);

        constraints.gridy = 6;
        meioPanel.add(linhaLabel, constraints);

        constraints.gridy = 7;
        exameFrame.add(meioPanel, constraints);

        JLabel comentarioLabel = new JLabel("Comentário: " + exame.getComentario());
        JLabel medicoLabel = new JLabel("Dr. " + medicoLogado.getNome());
        JLabel infoMedicoLabel = new JLabel("CRM " + medicoLogado.getCRM() + " - " + medicoLogado.getAreaAtuacao());

        constraints.gridy = 8;
        ultimoPanel.add(comentarioLabel, constraints);

        constraints.gridy = 9;
        ultimoPanel.add(medicoLabel, constraints);

        constraints.gridy = 10;
        ultimoPanel.add(infoMedicoLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 11;
        exameFrame.add(ultimoPanel, constraints);



    }

    protected static void imprimirLaudo(Medico medicoLogado, Laudo laudo) {
        JFrame exameFrame = new JFrame("Exame - " + laudo.getPaciente().getNome() + " - " + laudo.getExame().getTipo());

        exameFrame.setLayout(new GridBagLayout());
        exameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        exameFrame.setSize(1200, 800);
        exameFrame.setVisible(true);


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 1;

        JPanel topoPanel = new JPanel();
        JPanel meioPanel = new JPanel();
        JPanel ultimoPanel = new JPanel();

        topoPanel.setLayout(new GridBagLayout());
        meioPanel.setLayout(new GridBagLayout());
        ultimoPanel.setLayout(new GridBagLayout());

       /* try {
            BufferedImage logo = ImageIO.read(LoginUI.class.getResourceAsStream("/images/logo.png"));
            ImageIcon logoIcon = new ImageIcon(logo);
            JLabel logoLabel = new JLabel(logoIcon);
            topoPanel.add(logoLabel, BorderLayout.WEST);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        JLabel linhaLabel = new JLabel("___________________________________________________________________");
        JLabel enderecoClinicaLabel = new JLabel("Rua Cristovão Colombo - (17) 3222-3174");
        Font novaFonte = new Font(enderecoClinicaLabel.getFont().getFontName(), enderecoClinicaLabel.getFont().getStyle(), 30);
        enderecoClinicaLabel.setFont(novaFonte);

        constraints.gridy = 1;
        topoPanel.add(enderecoClinicaLabel, constraints);

        linhaLabel.setFont(novaFonte);
        constraints.gridy = 2;
        topoPanel.add(linhaLabel, constraints);

        constraints.gridy = 3;
        exameFrame.add(topoPanel, constraints);

        JLabel tipoExameLabel = new JLabel(laudo.getExame().getTipo() + " - Id: " + laudo.getExame().getId());

        constraints.gridy = 1;
        meioPanel.add(tipoExameLabel, constraints);

        JLabel nomePacienteLabel = new JLabel("Nome: " + laudo.getExame().getPaciente().getNome());
        JLabel idadeLabel = new JLabel("Idade: " + laudo.getExame().getPaciente().getIdade());
        JLabel sexoLabel = new JLabel("Sexo: " + laudo.getExame().getPaciente().getSexo());
        JLabel dataLabel = new JLabel("Data do laudo: " + laudo.getData());

        constraints.gridy = 2;
        meioPanel.add(nomePacienteLabel, constraints);

        constraints.gridy = 3;
        meioPanel.add(idadeLabel, constraints);

        constraints.gridy = 4;
        meioPanel.add(sexoLabel,constraints);

        constraints.gridy = 5;
        meioPanel.add(dataLabel, constraints);

        constraints.gridy = 6;
        meioPanel.add(linhaLabel, constraints);

        constraints.gridy = 7;
        exameFrame.add(meioPanel, constraints);

        JLabel comentarioLabel = new JLabel("Conclusões: " + laudo.getConteudo());
        JLabel medicoLabel = new JLabel("Dr. " + medicoLogado.getNome());
        JLabel infoMedicoLabel = new JLabel("CRM " + medicoLogado.getCRM() + " - " + medicoLogado.getAreaAtuacao());

        constraints.gridy = 8;
        ultimoPanel.add(comentarioLabel, constraints);

        constraints.gridy = 9;
        ultimoPanel.add(medicoLabel, constraints);

        constraints.gridy = 10;
        ultimoPanel.add(infoMedicoLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 11;
        exameFrame.add(ultimoPanel, constraints);



    }
}
