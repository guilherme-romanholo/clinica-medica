package clinica.medica.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class RecursosUI {
    public static JLabel criaImagemLabel(String imagePath){
        JLabel imageLabel = null;
        try {
            BufferedImage imageBufer = ImageIO.read(RecursosUI.class.getResourceAsStream(imagePath));
            ImageIcon imageIcon = new ImageIcon(imageBufer);
            //Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
            imageLabel = new JLabel(imageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageLabel;
    }
}
