import java.util.ArrayList;
import java.util.function.Predicate;
import java.lang.Math;
import java.lang.Thread;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;

public class Canvas extends JLayeredPane {
    private  String mode;
    private int oldX, oldY, currentX, currentY;
    private Oval oval;
    private ClassTable classtable;
    private Rectangle2D rectangle = null;
    private ArrayList<Object> panelList, selectedPanel;

    public Canvas() {
        super();
        setLayout(null);
        setBackground(Color.WHITE);
        Border blackline = BorderFactory.createLineBorder(Color.BLACK);
        setBorder(blackline);
        mode = "";

        panelList = new ArrayList<Object>();
        selectedPanel = new ArrayList<Object>();
        
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
                rectangle = new Rectangle2D.Double(oldX, oldY, 0, 0);
                unselectAll();
                // System.out.println("Pressed");
            }
            
            public void mouseClicked(MouseEvent e) {
                // System.out.println("Clicked");
                switch(mode) {
                    case "":
                        break;
                    case "select":
                        unselectAll();
                        break;

                    case "class":
                        createClassPanel(oldX, oldY);
                        break;

                    case "use Case":
                        createUsagePanel(oldX, oldY);
                        break;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int minX, minY, w, h;
                int objX, objY, width, height;
                currentX = e.getX();
                currentY = e.getY();
                minX = Math.min(oldX, currentX);
                minY = Math.min(oldY, currentY);
                w = Math.abs(oldX-currentX);
                h = Math.abs(oldY-currentY);
                switch(mode) {
                    case "":
                        break;
                    case "select":
                        rectangle = null;
                        revalidate();
                        repaint();
                        for(Object obj: panelList) {
                            objX = obj.getBounds().x;
                            objY = obj.getBounds().y;
                            width = obj.getBounds().width;
                            height = obj.getBounds().height;
        
                            if (objX >= minX && objY >= minY && objX+width <= minX+w && objY+height <= minY+h) {
                                if(obj.isSelected()){
                                    obj.setSelect(false);
                                }
                                else {
                                    obj.setSelect(true);
                                }
                            }
                        }
                        break;

                    case "class":
                        break;

                    case "use Case":
                        break;
                }
            }
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (mode == "select") {
                    int minX, minY, w, h;
                    currentX = e.getX();
                    currentY = e.getY();
                    minX = Math.min(oldX, currentX);
                    minY = Math.min(oldY, currentY);
                    w = Math.abs(oldX-currentX);
                    h = Math.abs(oldY-currentY);
                    rectangle.setRect(minX, minY, w, h);
                    revalidate();
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if(rectangle != null) {
            g2.setColor(Color.BLACK);
            g2.draw(rectangle);
        }
    }

    public void createClassPanel(int x, int y) {
        classtable = new ClassTable(this);
        newPanelInit(classtable, x, y);
    }

    public void createUsagePanel(int x, int y) {
        oval = new Oval(this);
        newPanelInit(oval, x, y);
    }

    public void newPanelInit(Object o, int x, int y) {
        unselectAll();
        o.setBounds(x,y,o.getWidth(),o.getHeight());
        o.setVisible(true);
        add(o);
        moveToFront(o);
        panelList.add(o);
        // System.out.println("class added");
        // System.out.println(getMode());
        revalidate();
        repaint();
    }

    public void setMode(String m) {
        mode = m;
        // System.out.println(mode.getName());
    }

    public String getMode() {
        return mode;
    }

    public ArrayList<Object> selectedPanel() {
        selectedPanel.clear();
        for (Object o: panelList) {
            if (o.isSelected()) {
                selectedPanel.add(o);
            }
        }
        return selectedPanel;
    }

    public void unselectAll(){
        for(Object obj: selectedPanel()){
            obj.setSelect(false);
        }
    }
}
