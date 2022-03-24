import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

public class Canvas extends JPanel {
    private  String mode;
    private int oldX, oldY, currentX, currentY;
    private Oval oval;
    private ClassTable classtable;
    private ArrayList<JPanel> panelList, selectedPanel;

    public Canvas() {
        super();
        setLayout(null);
        setBackground(Color.WHITE);
        Border blackline = BorderFactory.createLineBorder(Color.BLACK);
        setBorder(blackline);
        mode = "";

        panelList = new ArrayList<>();
        
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
                // System.out.println("Pressed");
            }
            
            public void mouseClicked(MouseEvent e) {
                // System.out.println("Clicked");
                switch(mode) {
                    case "":
                        break;
                    case "select":
                        break;

                    case "class":
                        createClassPanel(oldX, oldY);
                        break;

                    case "use Case":
                        createUsagePanel(oldX, oldY);
                        break;
                }
            }
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();
                
                
            }
        });
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
        o.setBounds(x,y,o.getWidth(),o.getHeight());
        o.setVisible(true);
        add(o);
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
}
