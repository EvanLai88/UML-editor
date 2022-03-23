import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Oval extends JPanel {
    public Oval() {
        setSize(100,70);
        setBackground(Color.WHITE);
        setOpaque(true);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(5, 5, 90, 60);
        g.setColor(Color.BLACK);
        g.drawOval(5, 5, 90, 60);
    }
}