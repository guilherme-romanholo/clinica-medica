package clinica.medica.gui.receita;

import clinica.medica.database.ReceitasSQL;
import clinica.medica.database.UsuariosSQL;
import clinica.medica.documentos.Receita;
import clinica.medica.gui.recursos.RecursosUI;
import clinica.medica.gui.telas.CadastroUI;
import clinica.medica.gui.telas.TelaLogadaUI;
import clinica.medica.gui.recursos.ImpressaoUI;
import clinica.medica.usuarios.Medico;
import clinica.medica.usuarios.Paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class ReceitasMedicoUI {
    public static JPanel telaPrescreverReceita(Medico medicoLogado, TelaLogadaUI telaLogada) {
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setSize(800, 600);

        JPanel infoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g.create();

                GradientPaint gradient = new GradientPaint(0, 0, Color.decode("#5e8ab3"), getWidth(), getHeight(), Color.decode("#67dcff"));

                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());

                g2d.dispose();
            }
        };

        infoPanel.setBackground(Color.decode("#67dcff"));
        infoPanel.setLayout(new GridBagLayout());
        JLabel receitaLabel = new JLabel("Área das receitas");

        JPanel painelReceita = new JPanel();

        painelReceita.setLayout(new GridBagLayout());
        painelReceita.setSize(800, 600);

        JButton novaReceitaButton = new JButton("Prescrever nova receita");
        JButton verificarReceitaButton = new JButton("Verificar receitas");

        novaReceitaButton.addActionListener(telaLogada);
        verificarReceitaButton.addActionListener(telaLogada);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;
        infoPanel.add(receitaLabel);
        receitaLabel.setFont(new Font("Roboto", Font.BOLD, 20));
        receitaLabel.setForeground(Color.WHITE);


        constraints.gridy = 1;
        painelReceita.add(novaReceitaButton, constraints);

        constraints.gridy = 2;
        painelReceita.add(verificarReceitaButton, constraints);

        painelPrincipal.add(infoPanel, BorderLayout.NORTH);
        painelPrincipal.add(painelReceita, BorderLayout.CENTER);

        return painelPrincipal;
    }

    public static JPanel telaNovaReceita(Medico medicoLogado, String pacienteCpf, TelaLogadaUI telaLogada) {
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setSize(800, 600);
        JPanel infoPanel = RecursosUI.criaInfoPanel("Nova receita");
        JPanel painelReceita = new JPanel();

        painelReceita.setLayout(new GridBagLayout());
        painelReceita.setSize(800, 600);

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
        painelPrincipal.add(infoPanel, BorderLayout.NORTH);
        painelPrincipal.add(painelReceita, BorderLayout.CENTER);

        return painelPrincipal;
    }

    public static JPanel showReceitas(Medico medicoLogado, TelaLogadaUI telaLogada) {
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setSize(800, 600);
        JPanel infoPanel = RecursosUI.criaInfoPanel("Receitas");
        ArrayList<Receita> receitas = ReceitasSQL.selectAllReceitasFromMedico(medicoLogado.getCpf());
        int i = 0;
        JPanel painelReceita = new JPanel();

        painelReceita.setLayout(new GridBagLayout());
        painelReceita.setSize(800, 600);

        JButton imprimirReceitaButton = new JButton("Visualizar receita");
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(telaLogada);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        String[] listaReceita = new String[receitas.size()];

        for (Receita rc : receitas) {
            listaReceita[i] = "Receita - " + rc.getPaciente().getNome() + " - " + rc.getNomeDoRemedio() + " - " + rc.getId();
            i++;
        }

        JList<String> list = new JList<>(listaReceita);
        JScrollPane scrollPanel = new JScrollPane(list);

        scrollPanel.setPreferredSize(new Dimension(600, 400));
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
                ImpressaoUI.imprimirDocumento(receita);
            }
        });

        painelPrincipal.add(infoPanel, BorderLayout.NORTH);
        painelPrincipal.add(painelReceita, BorderLayout.CENTER);
        return painelPrincipal;
    }

    public static JPanel showPacientesReceita(Medico medicoLogado, TelaLogadaUI telaLogada) {
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setSize(800, 600);
        JPanel infoPanel = RecursosUI.criaInfoPanel("Pacientes");
        ArrayList<Paciente> pacientes = UsuariosSQL.selectAllPacientes();
        int i = 0;

        JPanel painelExame = new JPanel();

        painelExame.setLayout(new GridBagLayout());
        painelExame.setSize(800, 600);


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
                telaLogada.getContentPanel().add(ReceitasMedicoUI.telaNovaReceita(medicoLogado, pacienteCpf, telaLogada), "Prescrever receita para esse paciente");
                telaLogada.getCardLayout().show(telaLogada.getContentPanel(), "Prescrever receita para esse paciente");
            }
        });
        painelPrincipal.add(infoPanel, BorderLayout.NORTH);
        painelPrincipal.add(painelExame, BorderLayout.CENTER);
        return painelPrincipal;
    }
}
