import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ClassTable extends JPanel {
    public ClassTable() {
        setSize(100,130);
        // setBackground(Color.WHITE);
        // setOpaque(true);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(5, 5, 90, 120);
        g.setColor(Color.BLACK);
        g.drawRect(5, 5, 90, 40);
        g.drawRect(5, 45, 90, 40);
        g.drawRect(5, 85, 90, 40);
    } 
}