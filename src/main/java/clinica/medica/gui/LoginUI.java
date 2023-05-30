package clinica.medica.gui;

import clinica.medica.database.UsuariosSQL;
import clinica.medica.usuarios.Medico;
import clinica.medica.usuarios.Paciente;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginUI {
    public static void chamarTela() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Não foi possível utilizar o recurso Look and Feel");
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                showLogin();
            }
        });
    }

    private static void showLogin() {
        JFrame frame = new JFrame("Login");

        frame.setLayout(new GridBagLayout());
        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //ImageIcon imagemLogo = new ImageIcon(LoginUI.class.getResource("/images/logo.jpg"));
        //JLabel logoLabel = new JLabel(imagemLogo);

        JLabel usernameLabel = new JLabel("Nome de Usuário");
        JLabel passwordLabel = new JLabel("Senha");

        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");
        JButton cadastroButton = new JButton("Cadastrar-se");
        
        //ativa quando eu clico com o mouse no botão
        cadastroButton.addMouseListener(new MouseAdapter(){
             @Override
            public void mouseClicked(MouseEvent e) {
                showCadastroMedico();
            }
        });
        
        loginButton.addMouseListener(new MouseAdapter(){
             @Override
            public void mouseClicked(MouseEvent e) {
                String nome = usernameField.getText();
                char[] senha = passwordField.getPassword();
                String password = new String(senha);
                Paciente pacienteLogado = UsuariosSQL.loginPaciente(nome, password);
                Medico medicoLogado = UsuariosSQL.loginMedico(nome, password);
                
                if(medicoLogado != null){
                    showTelaMedico(medicoLogado);
                    usernameField.setText("");
                    passwordField.setText("");
                }else if(pacienteLogado != null){
                    showTelaPaciente(pacienteLogado);
                    usernameField.setText("");
                    passwordField.setText("");
                }else{
                    JOptionPane.showMessageDialog(frame,"Não foi possível logar o médico/paciente, tente novamente!", "ERRO", JOptionPane.ERROR_MESSAGE);
                    usernameField.setText("");
                    passwordField.setText("");
                }
            }
        });
       
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        //constraints.gridy = 0;
        //frame.add(logoLabel, constraints);

        constraints.gridy = 1;
        frame.add(usernameLabel, constraints);

        constraints.gridy = 2;
        frame.add(usernameField, constraints);

        constraints.gridy = 3;
        frame.add(passwordLabel, constraints);

        constraints.gridy = 4;
        frame.add(passwordField, constraints);

        constraints.anchor = GridBagConstraints.CENTER;

        constraints.gridy = 5;
        frame.add(loginButton, constraints);

        constraints.gridy = 6;
        frame.add(cadastroButton, constraints);
    }
    
    private static void showCadastroMedico(){
        JFrame frame = new JFrame("Cadastro médico");

        frame.setLayout(new GridBagLayout());
        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //ImageIcon imagemLogo = new ImageIcon(LoginUI.class.getResource("/images/logo.jpg"));
        //JLabel logoLabel = new JLabel(imagemLogo);
        
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

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        //constraints.gridy = 0;
        //frame.add(logoLabel, constraints);

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
    }
    
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

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        //constraints.gridy = 0;
        //frame.add(logoLabel, constraints);

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
    }
    
    public static void showTelaMedico(Medico medicoLogado){
        JFrame frame = new JFrame("Área médica");

        frame.setLayout(new GridBagLayout());
        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JTextArea infoMedicoLabel = new JTextArea("============Bem Vindo, " + medicoLogado.getNome() + "============!\n\nÁrea de atuação: " + medicoLogado.getAreaAtuacao() + "\nCRM: " + medicoLogado.getCRM());
        infoMedicoLabel.setEditable(false);


        JButton cadastrarClienteButton = new JButton("Cadastrar novo paciente");
        JButton agendarConsultaButton = new JButton("Agendar nova consulta");
        JButton laudoButton = new JButton("Prescrever novo laudo");
        JButton exameButton = new JButton("Prescrever novo exame");
        
        cadastrarClienteButton.addMouseListener(new MouseAdapter(){
             @Override
            public void mouseClicked(MouseEvent e) {
                showCadastroPaciente();
            }
        });
        

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        constraints.gridy = 1;
        frame.add(infoMedicoLabel, constraints);

        constraints.gridy = 2;
        frame.add(cadastrarClienteButton, constraints);

        constraints.gridy = 3;
        frame.add(laudoButton, constraints);

        constraints.gridy = 4;
        frame.add(agendarConsultaButton, constraints);

        constraints.gridy = 5;
        frame.add(exameButton, constraints);
    }
    
    private static void showTelaPaciente(Paciente pacienteLogado){
        JFrame frame = new JFrame("Área do paciente");

        frame.setLayout(new GridBagLayout());
        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JTextArea infoMedicoLabel = new JTextArea("============Bem Vindo, " + pacienteLogado.getNome() + "!============");
        infoMedicoLabel.setEditable(false);


        JButton verificarConsultaButton = new JButton("Verifica consultas");
        JButton agendarConsultaButton = new JButton("Agendar nova consulta");
        JButton laudoButton = new JButton("Verificar laudos");
        JButton exameButton = new JButton("Verificar exames");
        
        

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        constraints.gridy = 1;
        frame.add(infoMedicoLabel, constraints);

        constraints.gridy = 2;
        frame.add(verificarConsultaButton, constraints);

        constraints.gridy = 3;
        frame.add(laudoButton, constraints);

        constraints.gridy = 4;
        frame.add(agendarConsultaButton, constraints);

        constraints.gridy = 5;
        frame.add(exameButton, constraints);
    }
}
