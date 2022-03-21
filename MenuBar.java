import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar {
    private static JMenuBar menubar;
    private static JMenu fileMenu, editMenu;
    private static JMenuItem changeName, group, ungroup;

    public MenuBar() {
        menubar = new JMenuBar();
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        changeName = new JMenuItem("change object name");
        group = new JMenuItem("Group");
        ungroup = new JMenuItem("UnGroup");

        editMenu.add(changeName);
        editMenu.add(group);
        editMenu.add(ungroup);

        menubar.add(fileMenu);
        menubar.add(editMenu);
    }

    public static JMenuBar getInstance() {
        return menubar;
    }
}
