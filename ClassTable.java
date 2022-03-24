import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ClassTable extends JPanel {
    private boolean select;
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
        
        panelInit(p1);
        panelInit(p2);
        panelInit(p3);
        p1.setBounds(5, 5, 90, 40);
        p2.setBounds(5, 45, 90, 40);
        p3.setBounds(5, 85, 90, 40);
        
        p1.setLayout(new GridBagLayout());
        label = new JLabel("Object Name");
        p1.add(label);

        setLayout(null);
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

    private void panelInit(JPanel jp) {
        jp.setSize(100, 40);
        jp.setBackground(Color.WHITE);
        jp.setOpaque(true);
        jp.setVisible(true);
    }



    public boolean isSelected() {
        return select;
    }


    public class Square extends JPanel {
        public Square() {
            super();
            setSize(10,10);
            setBackground(Color.BLACK);
            setOpaque(true);
            setVisible(true);
        }
    }

    private void initPort(Square s) {
        s.setSize(10,10);
        s.setBackground(Color.BLACK);
        s.setOpaque(true);
        s.setVisible(false);
    }
}