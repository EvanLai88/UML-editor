import javax.swing.JFrame;
import javax.tools.Tool;

import java.awt.BorderLayout;

public class UMLeditor extends JFrame {
    private Canvas canvas;
    private MenuBar menubar;
    private ToolBar toolbar;

    public UMLeditor() {
        canvas = new Canvas();
        menubar = new MenuBar();
        toolbar = new ToolBar();

        setLayout(new BorderLayout());
        add(MenuBar.getInstance(), BorderLayout.NORTH);
        add(ToolBar.getInstance(), BorderLayout.WEST);
        add(Canvas.getInstance(), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        UMLeditor mainWindow = new UMLeditor();
        mainWindow.setTitle("UML editor");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(900, 500);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setResizable(false);
        mainWindow.setVisible(true);
    }
}
