import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Oval extends JPanel {
    private JLabel label;

    public Oval() {
        super();
        setSize(100,70);
        setBackground(Color.WHITE);
        setOpaque(true);
        setVisible(true);
        setLayout(new GridBagLayout());
        label = new JLabel("Usage");
        add(label);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.drawOval(5, 5, 90, 60);
    }
}