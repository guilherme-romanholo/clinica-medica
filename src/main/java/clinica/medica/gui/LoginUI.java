package clinica.medica.gui;

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
                showCadastro();
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
    
    private static void showCadastro(){
        JFrame frame = new JFrame("Cadastro");

        frame.setLayout(new GridBagLayout());
        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //ImageIcon imagemLogo = new ImageIcon(LoginUI.class.getResource("/images/logo.jpg"));
        //JLabel logoLabel = new JLabel(imagemLogo);

        JLabel usernameLabel = new JLabel("Nome");
        JLabel cpfLabel = new JLabel("CPF");
        JLabel emailLabel = new JLabel("E-mail");
        JLabel passwordLabel = new JLabel("Senha");

        JTextField usernameField = new JTextField(20);
        JTextField cpfField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        JButton cadastroButton = new JButton("Cadastrar");
        
        cadastroButton.addMouseListener(new MouseAdapter(){
             @Override
            public void mouseClicked(MouseEvent e) {
                
                System.out.println("Aaaa");
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
        frame.add(passwordLabel, constraints);

        constraints.gridy = 8;
        frame.add(passwordField, constraints);

        constraints.anchor = GridBagConstraints.CENTER;

        constraints.gridy = 9;
        frame.add(cadastroButton, constraints);
    }
}
