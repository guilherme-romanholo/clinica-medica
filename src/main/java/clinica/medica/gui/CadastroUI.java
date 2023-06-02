package clinica.medica.gui;

import clinica.medica.database.UsuariosSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadastroUI {

    /**
     * Método de público para chamar a tela de cadastro do médico.
     */
    public static void telaCadastroMedico() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Não foi possível utilizar o recurso Look and Feel");
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                showCadastroMedico();
            }
        });
    }

    /**
     * Método público para chamar a tela de cadastro do paciente.
     */
    public static void telaCadastroPaciente() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Não foi possível utilizar o recurso Look and Feel");
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                showCadastroPaciente();
            }
        });
    }

    /**
     * Método privado para implementação da tela de cadastro do médico.
     */
    private static void showCadastroMedico(){
        LoginUI.frame.setVisible(false);

        JFrame frame = new JFrame("Cadastro médico");
        frame.setLocationRelativeTo(LoginUI.frame);

        frame.setLayout(new GridBagLayout());
        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel usernameLabel = new JLabel("Nome");
        JLabel cpfLabel = new JLabel("CPF");
        JLabel emailLabel = new JLabel("E-mail");
        JLabel areaLabel = new JLabel("Área de atuação");
        JLabel crmLabel = new JLabel("CRM");
        JLabel passwordLabel = new JLabel("Senha");

        JTextField usernameField = new JTextField(20);
        JTextField cpfField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JTextField areaField = new JTextField(20);
        JTextField crmField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        JButton cadastroButton = new JButton("Cadastrar");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        constraints.gridy = 1;
        frame.add(usernameLabel, constraints);

        constraints.gridy = 2;
        frame.add(usernameField, constraints);

        constraints.gridy = 3;
        frame.add(emailLabel, constraints);

        constraints.gridy = 4;
        frame.add(emailField, constraints);

        constraints.gridy = 5;
        frame.add(cpfLabel, constraints);

        constraints.gridy = 6;
        frame.add(cpfField, constraints);

        constraints.gridy = 7;
        frame.add(areaLabel, constraints);

        constraints.gridy = 8;
        frame.add(areaField, constraints);

        constraints.gridy = 9;
        frame.add(crmLabel, constraints);

        constraints.gridy = 10;
        frame.add(crmField, constraints);

        constraints.gridy = 11;
        frame.add(passwordLabel, constraints);

        constraints.gridy = 12;
        frame.add(passwordField, constraints);

        constraints.anchor = GridBagConstraints.CENTER;

        constraints.gridy = 13;
        frame.add(cadastroButton, constraints);

        //Quando clica no botão pra cadastrar, pega os dados e chama a função de cadastrar o médico
        cadastroButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                String nome = usernameField.getText();
                String cpf = cpfField.getText();
                String email = emailField.getText();
                String area = areaField.getText();
                String crm = crmField.getText();
                char[] senha = passwordField.getPassword();
                String password = new String(senha);

                if(UsuariosSQL.cadastroMedico(nome, cpf, email, password, area, crm)){
                    JOptionPane.showMessageDialog(frame, "Cadastro realizado com sucesso!");
                    frame.dispose();
                    LoginUI.frame.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(frame,"Não foi possível cadastrar o médico, tente novamente!", "ERRO", JOptionPane.ERROR_MESSAGE);
                    usernameField.setText("");
                    cpfField.setText("");
                    emailField.setText("");
                    areaField.setText("");
                    crmField.setText("");
                    passwordField.setText("");
                }

            }
        });
    }

    /**
     * Método privado para a implementação da tela de cadastro do paciente.
     */
    private static void showCadastroPaciente(){
        String[] sexo = {"Masculino","Feminino","Nenhum"};
        JFrame frame = new JFrame("Cadastro de paciente");

        frame.setLayout(new GridBagLayout());
        frame.setVisible(true);
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JLabel usernameLabel = new JLabel("Nome");
        JLabel cpfLabel = new JLabel("CPF");
        JLabel emailLabel = new JLabel("E-mail");
        JLabel enderecoLabel = new JLabel("Endereco");
        JLabel sexoLabel = new JLabel("Sexo");
        JLabel idadeLabel = new JLabel("Idade");
        JLabel alturaLabel = new JLabel("Altura");
        JLabel pesoLabel = new JLabel("Peso");
        JLabel passwordLabel = new JLabel("Senha");

        JTextField usernameField = new JTextField(20);
        JTextField cpfField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JTextField enderecoField = new JTextField(20);
        JComboBox sexoCombo = new JComboBox(sexo);
        sexoCombo.setSelectedIndex(2);
        JTextField idadeField = new JTextField(20);
        JTextField alturaField = new JTextField(20);
        JTextField pesoField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        JButton cadastroButton = new JButton("Cadastrar paciente");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        constraints.gridy = 1;
        frame.add(usernameLabel, constraints);

        constraints.gridy = 2;
        frame.add(usernameField, constraints);

        constraints.gridy = 3;
        frame.add(emailLabel, constraints);

        constraints.gridy = 4;
        frame.add(emailField, constraints);

        constraints.gridy = 5;
        frame.add(cpfLabel, constraints);

        constraints.gridy = 6;
        frame.add(cpfField, constraints);

        constraints.gridy = 7;
        frame.add(enderecoLabel, constraints);

        constraints.gridy = 8;
        frame.add(enderecoField, constraints);

        constraints.gridy = 9;
        frame.add(sexoLabel, constraints);

        constraints.gridy = 10;
        frame.add(sexoCombo, constraints);

        constraints.gridy = 11;
        frame.add(idadeLabel, constraints);

        constraints.gridy = 12;
        frame.add(idadeField, constraints);

        constraints.gridy = 13;
        frame.add(alturaLabel, constraints);

        constraints.gridy = 14;
        frame.add(alturaField, constraints);

        constraints.gridy = 15;
        frame.add(pesoLabel, constraints);

        constraints.gridy = 16;
        frame.add(pesoField, constraints);

        constraints.gridy = 17;
        frame.add(passwordLabel, constraints);

        constraints.gridy = 18;
        frame.add(passwordField, constraints);

        constraints.anchor = GridBagConstraints.CENTER;

        constraints.gridy = 19;
        frame.add(cadastroButton, constraints);

        //Quando clica no botão pra cadastrar, pega os dados e chama a função de cadastrar o paciente
        cadastroButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                int idade = 0;
                double altura =0, peso = 0;
                String nome = usernameField.getText();
                String cpf = cpfField.getText();
                String email = emailField.getText();
                String endereco = enderecoField.getText();
                String sexo = (String)sexoCombo.getSelectedItem();
                try{
                    idade = Integer.parseInt(idadeField.getText());
                    altura = Double.parseDouble(alturaField.getText());
                    peso = Double.parseDouble(pesoField.getText());
                }catch(NumberFormatException r){

                }
                char[] senha = passwordField.getPassword();
                String password = new String(senha);

                if(UsuariosSQL.cadastroPaciente(nome, cpf, email, password, endereco, sexo, idade, altura, peso)){
                    JOptionPane.showMessageDialog(frame, "Cadastro do cliente realizado com sucesso!");
                    frame.dispose();
                }else{
                    JOptionPane.showMessageDialog(frame,"Não foi possível cadastrar o médico, tente novamente!", "ERRO", JOptionPane.ERROR_MESSAGE);
                    usernameField.setText("");
                    cpfField.setText("");
                    emailField.setText("");
                    enderecoField.setText("");
                    sexoCombo.setSelectedIndex(2);
                    idadeField.setText("");
                    alturaField.setText("");
                    pesoField.setText("");
                    passwordField.setText("");
                }

            }
        });

    }
}
