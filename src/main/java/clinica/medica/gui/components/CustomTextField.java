package clinica.medica.gui.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CustomTextField extends JTextField {
    private String placeholder;
    private Border defaultBorder;
    private Border bottomBorder;

    public CustomTextField() {
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        defaultBorder = BorderFactory.createLineBorder(Color.BLACK);
        bottomBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                setBorder(bottomBorder);
            }

            @Override
            public void focusLost(FocusEvent e) {
                setBorder(defaultBorder);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (getText().isEmpty() && !hasFocus()) {
            FontMetrics fm = g.getFontMetrics();
            int textHeight = fm.getHeight();
            int textX = getInsets().left;
            int textY = (getHeight() - textHeight) / 2 + fm.getAscent();

            g.setColor(Color.GRAY);
            g.drawString(placeholder, textX, textY);
        }
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }
}
