package clinica.medica.gui;

import clinica.medica.usuarios.Medico;

import javax.swing.*;
import java.awt.*;

public class MainFrameUI {
    private JFrame mainFrame;
    private JPanel painelAtual;
    private CardLayout cardLayout;

    public void mostrarTela() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Não foi possível utilizar o recurso Look and Feel");
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }

    public void createMainFrame() {
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 400);
        mainFrame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        painelAtual = new JPanel(cardLayout);
    }
}
