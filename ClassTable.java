import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

public class ClassTable extends JPanel {
    private JLabel label;
    public ClassTable() {
        super();
        setSize(100,130);
        setBackground(Color.WHITE);
        setOpaque(true);
        setVisible(true);
        
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        
        p1.setSize(100, 40);
        p2.setSize(100, 40);
        p3.setSize(100, 40);

        p1.setBackground(Color.WHITE);
        p1.setOpaque(true);
        p1.setVisible(true);

        p2.setBackground(Color.WHITE);
        p2.setOpaque(true);
        p2.setVisible(true);

        p3.setBackground(Color.WHITE);
        p3.setOpaque(true);
        p3.setVisible(true);

        p1.setLayout(new GridBagLayout());
        label = new JLabel("Object Name");
        p1.add(label);

        setLayout(null);
        p1.setBounds(5, 5, 90, 40);
        p2.setBounds(5, 45, 90, 40);
        p3.setBounds(5, 85, 90, 40);
        add(p1);
        add(p2);
        add(p3);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.drawRect(5, 5, 90, 40);
        g.drawRect(5, 45, 90, 40);
        g.drawRect(5, 85, 90, 40);
    } 
}