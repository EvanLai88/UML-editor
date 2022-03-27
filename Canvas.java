import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;
import java.lang.Math;
import java.lang.Thread;

import java.awt.Color;
import java.awt.Component;
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
    private ArrayList<Line> lineList;
    private ArrayList<String> lines = new ArrayList<String>(Arrays.asList("associate", "general", "composite"));
    private JPanel lineStart, lineEnd;
    public Callback setStart = new Callback() {

        @Override
        public void setEndPoint(JPanel jp) {
            setLineStart(jp);
        }
    };
    public Callback setEnd = new Callback() {

        @Override
        public void setEndPoint(JPanel jp) {
            setLineEnd(jp);
        }
    };

    public interface Callback {
        void setEndPoint(JPanel jp);
    }

    public Canvas() {
        super();
        setLayout(null);
        setBackground(Color.WHITE);
        Border blackline = BorderFactory.createLineBorder(Color.BLACK);
        setBorder(blackline);
        mode = "";

        panelList = new ArrayList<Object>();
        selectedPanel = new ArrayList<Object>();
        lineList = new ArrayList<Line>();
        
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
                Boolean containsPanel = false;
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
                            objX = obj.getX();
                            objY = obj.getY();
                            width = obj.getWidth();
                            height = obj.getHeight();
        
                            if (objX >= minX && objY >= minY && objX+width <= minX+w && objY+height <= minY+h) {
                                containsPanel = true;
                            }
                        }
                        if ( ! containsPanel ) {
                            unselectAll();
                            updateSelectedPanel();
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
                    int objX, objY, width, height;
                    currentX = e.getX();
                    currentY = e.getY();
                    minX = Math.min(oldX, currentX);
                    minY = Math.min(oldY, currentY);
                    w = Math.abs(oldX-currentX);
                    h = Math.abs(oldY-currentY);
                    rectangle.setRect(minX, minY, w, h);
                    revalidate();
                    repaint();

                    for(Object obj: panelList) {
                        objX = obj.getX();
                        objY = obj.getY();
                        width = obj.getWidth();
                        height = obj.getHeight();
    
                        if (objX >= minX && objY >= minY && objX+width <= minX+w && objY+height <= minY+h) {
                            if( selectedPanel.contains(obj)){
                                obj.setSelect(false);
                            }
                            else {
                                obj.setSelect(true);
                            }
                        }
                        else {
                            if( selectedPanel.contains(obj)){
                                obj.setSelect(true);
                            }
                            else {
                                obj.setSelect(false);
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        if(mode == "select") {
            if(rectangle != null) {
                g2.setColor(Color.BLACK);
                g2.draw(rectangle);
            }
        }
        else if(lines.contains(mode)) {
            for(Line line: lineList) {
                line.drawLine(g2);
            }
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        for(Line line: lineList) {
            line.drawLine(g2);
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
    
    public void addPanel(Object obj) {
        add(obj);
        moveToFront(obj);
        panelList.add(obj);
    }

    public void removePanel(Object obj) {
        remove(obj);
        panelList.remove(obj);
        obj.setSelect(false);
    }

    public void newPanelInit(Object o, int x, int y) {
        unselectAll();
        o.setLocation(x,y);
        o.setVisible(true);
        addPanel(o);
        revalidate();
        repaint();
    }

    public ArrayList<Line> getLineList() {
        return lineList;
    }

    public void addLine(Line line) {
        lineList.add(line);
    }

    public void removeLine(Line line) {
        lineList.remove(line);
    }

    public void setMode(String m) {
        mode = m;
    }

    public String getMode() {
        return mode;
    }

    public void setLineStart(JPanel jp) {
        lineStart = jp;
    }

    public JPanel getLineStart() {
        return lineStart;
    }
    
    public void setLineEnd(JPanel jp) {
        lineEnd = jp;
    }

    public JPanel getLineEnd() {
        return lineEnd;
    }

    public ArrayList<Object> updateSelectedPanel() {
        selectedPanel.clear();
        for (Object o: panelList) {
            if (o.isSelected()) {
                selectedPanel.add(o);
            }
        }
        return selectedPanel;
    }

    public void unselectAll(){
        for(Object obj: panelList){
            obj.setSelect(false);
            selectedPanel.remove(obj);
            if(obj instanceof Composite) {
                ((Composite)obj).unselectAll();
            }
        }
    }
}
