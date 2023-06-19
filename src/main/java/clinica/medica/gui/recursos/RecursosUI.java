package clinica.medica.gui.recursos;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class RecursosUI {
    public static JLabel criaImagemLabel(String imagePath, int width, int height){
        JLabel imageLabel = null;
        try {
            BufferedImage imageBufer = ImageIO.read(RecursosUI.class.getResourceAsStream(imagePath));
            ImageIcon imageIcon = new ImageIcon(imageBufer);
            Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
            ImageIcon imageIconResize = new ImageIcon(image);
            imageLabel = new JLabel(imageIconResize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageLabel;
    }

    public static JPanel criaInfoPanel(String label) {
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
        JLabel l = new JLabel();
        l.setText(label);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;
        infoPanel.add(l);
        l.setFont(new Font("Roboto", Font.BOLD, 20));
        l.setForeground(Color.WHITE);
        infoPanel.add(l);

        return infoPanel;
    }
}
