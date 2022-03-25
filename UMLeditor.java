import javax.swing.JFrame;
import javax.tools.Tool;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

public class UMLeditor extends JFrame {
    private Canvas canvas;
    private MenuBar menubar;
    private ToolBar toolbar;

    public UMLeditor() {
        canvas = new Canvas();
        menubar = new MenuBar(canvas);
        toolbar = new ToolBar(canvas);
    
        setLayout(new BorderLayout());
        add(menubar, BorderLayout.NORTH);
        add(toolbar, BorderLayout.WEST);
        add(canvas, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        UMLeditor mainWindow = new UMLeditor();
        

        mainWindow.setTitle("UML editor");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(900, 675);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setResizable(false);
        mainWindow.setVisible(true);
    }

    public void changeMode() {

    }
}
