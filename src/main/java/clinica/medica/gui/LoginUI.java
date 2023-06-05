package clinica.medica.gui;

import clinica.medica.database.UsuariosSQL;
import clinica.medica.usuarios.Medico;
import clinica.medica.usuarios.Paciente;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginUI {
    protected static JFrame frame;

    /**
     * Método público para chamar a tela de login.
     */
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

    /**
     * Método privado para a implementação da tela de 'login'.
     */
    private static void showLogin() {
        frame = new JFrame("Login");

        frame.setLayout(new GridBagLayout());
        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel usernameLabel = new JLabel("Email");
        JLabel passwordLabel = new JLabel("Senha");

        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");
        JButton cadastroButton = new JButton("Cadastrar-se");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

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

        //ativa quando eu clico com o mouse no botão
        cadastroButton.addMouseListener(new MouseAdapter(){
             @Override
            public void mouseClicked(MouseEvent e) {
                CadastroUI.telaCadastroMedico();
            }
        });
        
        loginButton.addMouseListener(new MouseAdapter(){
             @Override
            public void mouseClicked(MouseEvent e) {
                String email = usernameField.getText();
                char[] senha = passwordField.getPassword();
                String password = new String(senha);
                Paciente pacienteLogado = UsuariosSQL.loginPaciente(email, password);
                Medico medicoLogado = UsuariosSQL.loginMedico(email, password);
                
                if(medicoLogado != null){
                    TelaLogadaUI.mostrarTela(medicoLogado);
                    usernameField.setText("");
                    passwordField.setText("");
                }else if(pacienteLogado != null){
                    TelaLogadaUI.mostrarTela(pacienteLogado);
                    usernameField.setText("");
                    passwordField.setText("");
                }else{
                    JOptionPane.showMessageDialog(frame,"Não foi possível logar o médico/paciente, tente novamente!", "ERRO", JOptionPane.ERROR_MESSAGE);
                    usernameField.setText("");
                    passwordField.setText("");
                }
            }
        });
    }
}
