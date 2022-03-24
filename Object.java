import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Object extends JPanel {
    protected boolean select;
    protected JLabel label;
    protected JPanel ports;

    public Object(String name, int width, int height, int x, int y, int Lwid, int Lhei) {
        setBackground(Color.WHITE);
        setOpaque(true);
        setVisible(true);
        setLayout(null);
        label = new JLabel(name);
        setSize(width,height);
        label.setBounds(x, y, Lwid, Lhei);
        label.setVisible(true);
        add(label);
        select = false;

        Square p1 = new Square();
        Square p2 = new Square();
        Square p3 = new Square();
        Square p4 = new Square();

        initPort(p1);
        initPort(p2);
        initPort(p3);
        initPort(p4);
        p1.setBounds((getWidth()-p1.getWidth())/2, 0, p1.getWidth(), p1.getHeight());
        p2.setBounds((getWidth()-p2.getWidth())/2, getHeight()-p2.getHeight(), p2.getWidth(), p2.getHeight());
        p3.setBounds(0, (getHeight()-p3.getHeight())/2, p3.getWidth(), p3.getHeight());
        p4.setBounds(getWidth()-p4.getWidth(), (getHeight()-p4.getHeight())/2, p4.getWidth(), p4.getHeight());
        
        add(p1);
        add(p2);
        add(p3);
        add(p4);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                select = true;
                p1.setVisible(true);
                p2.setVisible(true);
                p3.setVisible(true);
                p4.setVisible(true);
            }
        });
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
