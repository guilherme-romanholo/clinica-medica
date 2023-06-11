package clinica.medica.gui;

import clinica.medica.database.UsuariosSQL;
import clinica.medica.usuarios.Medico;
import clinica.medica.usuarios.Paciente;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LoginUI {
    protected static JFrame frame;

    /**
     * Método público para chamar a tela de login.
     */
    public static void chamarTela() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
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

    /**
     * Método privado para a implementação da tela de 'login'.
     */
    private static void showLogin() {
        JPanel painel = new JPanel();
        frame = new JFrame("Login");

        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(1300, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        painel.setLayout(new GridBagLayout());

        JLabel usernameLabel = new JLabel("Email");
        JLabel passwordLabel = new JLabel("Senha");

        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");
        JButton cadastroButton = new JButton("Cadastrar-se");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 200, 5, 5);
        constraints.gridx = 1;

        constraints.gridy = 1;
        painel.add(usernameLabel, constraints);

        constraints.gridy = 2;
        painel.add(usernameField, constraints);

        constraints.gridy = 3;
        painel.add(passwordLabel, constraints);

        constraints.gridy = 4;
        painel.add(passwordField, constraints);

        constraints.anchor = GridBagConstraints.CENTER;

        constraints.gridy = 5;
        painel.add(loginButton, constraints);

        constraints.gridy = 6;
        painel.add(cadastroButton, constraints);

        frame.add(painel, BorderLayout.WEST);

        try {
            BufferedImage logo = ImageIO.read(LoginUI.class.getResourceAsStream("/images/logo.png"));
            ImageIcon logoIcon = new ImageIcon(logo);
            JLabel logoLabel = new JLabel(logoIcon);
            frame.add(logoLabel, BorderLayout.EAST);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //ativa quando eu clico com o mouse no botão
        cadastroButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CadastroUI.telaCadastroMedico();
            }
        });

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String email = usernameField.getText();
                char[] senha = passwordField.getPassword();
                String password = new String(senha);
                Paciente pacienteLogado = UsuariosSQL.loginPaciente(email, password);
                Medico medicoLogado = UsuariosSQL.loginMedico(email, password);

                if (medicoLogado != null) {
                    TelaLogadaUI.mostrarTela(medicoLogado);
                    usernameField.setText("");
                    passwordField.setText("");
                } else if (pacienteLogado != null) {
                    TelaLogadaUI.mostrarTela(pacienteLogado);
                    usernameField.setText("");
                    passwordField.setText("");
                } else {
                    JOptionPane.showMessageDialog(painel, "Não foi possível logar o médico/paciente, tente novamente!", "ERRO", JOptionPane.ERROR_MESSAGE);
                    usernameField.setText("");
                    passwordField.setText("");
                }
            }
        });
    }
}
