import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.security.ProtectionDomain;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Object extends JPanel {
    protected Canvas canvas;
    protected Object origin;
    protected boolean select;
    protected String Name;
    protected Square p1, p2, p3, p4;
    protected JLabel label;
    protected JPanel ports;
    protected int oldX, oldY, currentX, currentY;
    protected ArrayList<Object> objectList;

    public Object(Canvas instance, String name, int width, int height) {
        Name = name;
        canvas = instance;
        setBackground(Color.white);
        setOpaque(false);
        setVisible(true);
        setLayout(null);
        setSize(width,height);
        setOrigin(null);
        // label = new JLabel(name);
        // label.setBounds(x, y, Lwid, Lhei);
        // label.setVisible(true);
        // add(label);
        select = false;

        p1 = new Square();
        p2 = new Square();
        p3 = new Square();
        p4 = new Square();

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
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // System.out.println(e.getX());
                // System.out.println(e.getY());
                oldX = getX()+e.getX();
                oldY = getY()+e.getY();
                canvas.unselectAll();

                if (canvas.getMode() == "select") {
                    setSelect(true);
                    canvas.updateSelectedPanel();
                }
                else if (canvas.getMode() == "associate") {

                }
                else if (canvas.getMode() == "general") {
                    
                }
                else if (canvas.getMode() == "composite") {
                    
                }
                else if (canvas.getMode() == "class") {
                    // System.out.println("Object:class");
                    canvas.createClassPanel(oldX, oldY);
                }
                else if (canvas.getMode() == "use Case") {
                    // System.out.println("Object:useCase");
                    canvas.createUsagePanel(oldX, oldY);
                }
            }
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();
                // canvas.unselectAll();

                if (canvas.getMode() == "select") {
                    // getTopLevel().setSelect(true);
                    getTopLevel().setLocation(getTopLevel().getX() + (currentX-oldX), getTopLevel().getY() + (currentY-oldY));
                    getTopLevel().revalidate();
                    getTopLevel().repaint();
                }
                else if (canvas.getMode() == "associate") {

                }
                else if (canvas.getMode() == "composite") {

                }
                else if (canvas.getMode() == "general") {

                }
                else if (canvas.getMode() == "class") {

                }
                else if (canvas.getMode() == "use Case") {

                }
            }
        });
    }

    public void setSelect(boolean isSet) {
        select = isSet;
        p1.setVisible(isSet);
        p2.setVisible(isSet);
        p3.setVisible(isSet);
        p4.setVisible(isSet);
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

    public Object getOrigin() {
        return origin;
    }

    public void setOrigin(Object obj) {
        origin = obj;
    }
    public Object getTopLevel() {
        Object top = this;
        while (top.getOrigin() != null) {
            top = top.getOrigin();
        }
        return top;
    }
}