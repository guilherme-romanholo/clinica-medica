package clinica.medica.gui.exame;

import clinica.medica.database.ExamesSQL;
import clinica.medica.database.MedicosSQL;
import clinica.medica.documentos.Exame;
import clinica.medica.gui.recursos.RecursosUI;
import clinica.medica.gui.telas.CadastroUI;
import clinica.medica.gui.telas.TelaLogadaUI;
import clinica.medica.gui.recursos.ImpressaoUI;
import clinica.medica.usuarios.Medico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
/**
 * Classe que possui os métodos usados na criação da interface de exames do médico
 */
public class ExamesMedicoUI {
    /**
     * método que montará o painel da área de exames do médico
     * @param telaLogada tela principal que conterá o painel das consultas marcadas
     * @return retorna o painel das consultas marcadas para adicioná-lo na tela principal
     */
    public static JPanel telaAreaDosExames(TelaLogadaUI telaLogada) {
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

        JLabel examesLabel = new JLabel();
        examesLabel.setText("Área dos exames");

        JPanel painelExame = new JPanel(new GridBagLayout());

        JButton novoExameButton = new JButton("Prescrever novo exame");
        JButton verificarExameButton = new JButton("Verificar exames");

        novoExameButton.addActionListener(telaLogada);
        verificarExameButton.addActionListener(telaLogada);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;

        infoPanel.add(examesLabel, constraints);
        examesLabel.setFont(new Font("Roboto", Font.BOLD, 20));
        examesLabel.setForeground(Color.WHITE);

        painelPrincipal.add(infoPanel, BorderLayout.NORTH);

        constraints.gridy = 1;
        painelExame.add(novoExameButton, constraints);

        constraints.gridy = 2;
        painelExame.add(verificarExameButton, constraints);

        painelPrincipal.add(painelExame, BorderLayout.CENTER);


        return painelPrincipal;
    }

    /**
     * Método que montará o painel contendo os campos de preenchimento para cadastrar um novo exame
     * @param medicoLogado médico que irá fazer o exame
     * @param telaLogada tela principal que conterá o painel das consultas marcadas
     * @return retorna o painel das consultas marcadas para adicioná-lo na tela principal
     */
    public static JPanel telaNovoExame(Medico medicoLogado, TelaLogadaUI telaLogada) {
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setSize(800, 600);

        JPanel infoPanel = RecursosUI.criaInfoPanel("Novo exame");
        JPanel painelExame = new JPanel();

        painelExame.setLayout(new GridBagLayout());
        painelExame.setSize(800, 600);

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


        /**
         * criação de uma nova classe para tratar os eventos do calendário
         */
        cadastrarExameButton.addMouseListener(new MouseAdapter() {
            /**
             * Método que pega os dados fornecidos e salva o exame no banco de dados
             * @param e evento de click do mouse no botão Cadastrar exame
             */
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

        painelPrincipal.add(infoPanel, BorderLayout.NORTH);
        painelPrincipal.add(painelExame, BorderLayout.CENTER);
        return painelPrincipal;
    }

    /**
     * Método que montará o painel contendo os exames feitos pelo médico
     * @param medicoLogado médico logado no sistema
     * @param telaLogada tela principal que conterá o painel das consultas marcadas
     * @return retorna o painel das consultas marcadas para adicioná-lo na tela principal
     */
    public static JPanel showExamesFeitosPeloMedico(Medico medicoLogado, TelaLogadaUI telaLogada) {
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setSize(800, 600);

        JPanel infoPanel = RecursosUI.criaInfoPanel("Exames");
        ArrayList<Exame> exames = ExamesSQL.verificarExames(medicoLogado.getCpf());
        int i = 0;
        JPanel painelExame = new JPanel();

        painelExame.setLayout(new GridBagLayout());
        painelExame.setSize(800, 600);

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
        constraints.gridy = 1;
        painelExame.add(scrollPanel, constraints);
        constraints.gridy = 2;
        painelExame.add(imprimirExameButton, constraints);
        constraints.gridy = 3;
        painelExame.add(voltarButton, constraints);

        /**
         * criação de uma nova classe interna para tratar os eventos do botão Imprimir exame
         */
        imprimirExameButton.addMouseListener(new MouseAdapter() {
            /**
             * Método que pega os dados do exame e imprime este em uma nova tela
             * @param e evento de click do mouse no botão Imprimir exame
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                String exameSelecionado = list.getSelectedValue();
                String[] elementos = exameSelecionado.split("-");
                int id = Integer.parseInt(elementos[3].strip());
                Exame exame = new Exame(id);
                ImpressaoUI.imprimirDocumento(exame);
            }
        });

        painelPrincipal.add(infoPanel, BorderLayout.NORTH);
        painelPrincipal.add(painelExame, BorderLayout.CENTER);
        return painelPrincipal;
    }
}
