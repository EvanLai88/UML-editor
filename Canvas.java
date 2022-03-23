import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Canvas extends JPanel {
    private static Canvas instance = null;
    private int oldX, oldY, currentX, currentY;
    private String mode;
    private Oval oval;
    private ClassTable classtable;
    private ArrayList<JPanel> panelList;

    public Canvas() {
        super();
        setLayout(null);
        Border blackline = BorderFactory.createLineBorder(Color.BLACK);
        setBorder(blackline);
        mode = "";

        panelList = new ArrayList<>();
        
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
                System.out.println("Pressed");
            }
            
            public void mouseClicked(MouseEvent e) {
                System.out.println("Clicked");
                switch(mode) {
                    case "":
                        break;
                    case "select":
                        break;

                    case "class":
                        classtable = new ClassTable();
                        classtable.setBounds(oldX, oldY, classtable.getWidth(), classtable.getHeight());
                        add(classtable);
                        panelList.add(classtable);
                        System.out.println("class added");
                        revalidate();
                        repaint();
                        break;

                    case "use Case":
                        oval = new Oval();
                        oval.setBounds(oldX, oldY, oval.getWidth(), oval.getHeight());
                        add(oval);
                        panelList.add(oval);
                        System.out.println("useCase added");
                        revalidate();
                        repaint();
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

    public static Canvas getInstance() {
        if (instance == null) {
            synchronized(Canvas.class) {
                if (instance == null) {
                    instance = new Canvas();
                }
            }
        }
        return instance;
    }

    public void setMode(String m) {
        mode = m;
        // System.out.println(mode.getName());
    }
}
