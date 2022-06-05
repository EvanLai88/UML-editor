import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.text.NumberFormat.Style;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Object extends JPanel {
    protected Canvas canvas;
    protected String shape;
    protected Object origin;
    protected boolean select, isComposite;
    protected String Name;
    protected JPanel p1, p2, p3, p4;
    protected JLabel label;
    protected JPanel ports;
    protected int oldX, oldY, currentX, currentY;
    protected int borderSize = 5;
    protected ArrayList<Object> objectList;
    protected final ArrayList<String> lines = new ArrayList<String>(Arrays.asList("associate", "general", "composite"));

    protected Object outer() {
        return Object.this;
    }

    public Object(String name, int width, int height) {
        Name = name;
        canvas = Canvas.getInstance();
        isComposite = false;
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

        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();

        initPort(p1);
        initPort(p2);
        initPort(p3);
        initPort(p4);

        /**
         *  ------p1-------
         * |               |
         * |               |
         * p3              p4
         * |               |
         * |               |
         *  ------p2-------
         */
        p1.setLocation((getWidth()-p1.getWidth())/2, 0);
        p2.setLocation((getWidth()-p2.getWidth())/2, getHeight()-p2.getHeight());
        p3.setLocation(                           0, (getHeight()-p3.getHeight())/2);
        p4.setLocation(    getWidth()-p4.getWidth(), (getHeight()-p4.getHeight())/2);
        
        add(p1);
        add(p2);
        add(p3);
        add(p4);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(! SwingUtilities.isLeftMouseButton(e)) {
                    return;
                }
                oldX = e.getX();
                oldY = e.getY();
                // System.out.println(oldX);
                // System.out.println(oldY);
                if (lines.contains(canvas.getMode())) {
                    if(isComposite) {
                        return;
                    }
                    findPort(oldX, oldY, canvas.setStart);
                    canvas.setLineEnd(null);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // System.out.println(e.getX());
                // System.out.println(e.getY());
                if(! SwingUtilities.isLeftMouseButton(e)) {
                    return;
                }
                oldX = getX()+e.getX();
                oldY = getY()+e.getY();
                canvas.unselectAll();

                if (canvas.getMode() == "select") {
                    setSelect(true);
                    canvas.updateSelectedPanel();
                }
                // else if (canvas.getMode() == "associate") {
                    
                // }
                // else if (canvas.getMode() == "general") {
                    
                // }
                // else if (canvas.getMode() == "composite") {
                    
                // }
                else if (canvas.getMode() == "class") {
                    // System.out.println("Object:class");
                    canvas.createClassPanel(oldX, oldY);
                }
                else if (canvas.getMode() == "use Case") {
                    // System.out.println("Object:useCase");
                    canvas.createUsagePanel(oldX, oldY);
                }
                canvas.revalidate();
                canvas.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                // System.out.println(getClass().getName()+"mouseEntered");
                // System.out.println(lines.contains(canvas.getMode()));
                if (lines.contains(canvas.getMode())) {
                    if(isComposite) {
                        return;
                    }
                    // findPort(x, y, canvas.setEnd);
                    canvas.setLineEnd(outer());;
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // System.out.println(getClass().getName()+"mouseExited");
                // System.out.println(lines.contains(canvas.getMode()));
                if (lines.contains(canvas.getMode())) {
                    if(isComposite) {
                        return;
                    }
                    canvas.setLineEnd(null);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(! SwingUtilities.isLeftMouseButton(e)) {
                    return;
                }
                int x = SwingUtilities.convertPoint(outer(), e.getX(), e.getY(), canvas.getLineEnd()).x;
                int y = SwingUtilities.convertPoint(outer(), e.getX(), e.getY(), canvas.getLineEnd()).y;
                // System.out.println(x);
                // System.out.println(y);
                if (lines.contains(canvas.getMode())) {
                    // System.out.println(isComposite);
                    if(isComposite) {
                        return;
                    }
                    if(canvas.getLineStart() == null || canvas.getLineEnd() == null) {
                        canvas.setLineStart(null);
                        canvas.setLineEnd(null);
                        return;
                    }
                    
                    ((Object)canvas.getLineEnd()).findPort(x, y, canvas.setEnd);
                    if(canvas.getLineStart() == null || canvas.getLineEnd() == null) {
                        canvas.setLineStart(null);
                        canvas.setLineEnd(null);
                        return;
                    }

                    System.out.println(canvas.getLineStart() == canvas.getLineEnd());
                    System.out.println(canvas.getLineStart());
                    System.out.println(canvas.getLineEnd());
                    if(canvas.getLineStart() == canvas.getLineEnd()) {
                        canvas.setLineStart(null);
                        canvas.setLineEnd(null);
                        return;
                    }

                    switch (canvas.getMode()) {
                        case "associate":
                            canvas.addLine(new AssociateLine(canvas.getLineStart(), canvas.getLineEnd()));
                            break;
                        case "composite":
                            canvas.addLine(new CompositeLine(canvas.getLineStart(), canvas.getLineEnd()));
                            break;
                        case "general":
                            canvas.addLine(new GeneralLine(canvas.getLineStart(), canvas.getLineEnd()));
                            break;
                    }
                    // for(Line l: canvas.getLineList()) {
                    //     System.out.println(l.getStartPortPosition());
                    //     System.out.println(l.getEndPortPosition());
                    // }
                    canvas.revalidate();
                    canvas.repaint();
                    // System.out.println("canvas.getLineList():");
                    // System.out.println(canvas.getLineList().);
                    canvas.setLineStart(null);
                    canvas.setLineEnd(null);
                }
            }
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(! SwingUtilities.isLeftMouseButton(e)) {
                    return;
                }
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
                canvas.revalidate();
                canvas.repaint();
            }
        });
    }

    public int getShapeWidth() {
        return getWidth()-(borderSize*2);
    }

    public int getShapeHeight() {
        return getHeight()-(borderSize*2);
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

    private void initPort(JPanel s) {
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

    public void unselectAll() {
        setSelect(false);
    }

    /**
     * (0, 0)-----------------------(width, 0)
     * |                            |
     * |                            |
     * |                            |
     * |                            |
     * |                            |
     * (0, height)------------------(width, height)
     */
    public void findPort(int x, int y, Canvas.Callback callback) {
        if (this.shape == "Oval") {
            if( Math.pow((getShapeHeight()/2)*(x-(getWidth()/2)), 2) + Math.pow((getShapeWidth()/2)*(y-(getHeight()/2)), 2) > Math.pow((getShapeWidth()/2)*(getShapeHeight()/2),2)) {
                canvas.setLineEnd(null);
                return;
            }
        }
        if(x*getHeight()-y*getWidth() > 0){
            if(x*getHeight()+y*getWidth() > getWidth()*getHeight()){
                callback.setEndPoint(p4);
            }
            else {
                callback.setEndPoint(p1);
            }
        }
        else {
            if(x*getHeight()+y*getWidth() > getWidth()*getHeight()){
                callback.setEndPoint(p2);
            }
            else {
                callback.setEndPoint(p3);
            }
        }
    }
}