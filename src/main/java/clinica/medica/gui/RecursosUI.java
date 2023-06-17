package clinica.medica.gui;

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
}
