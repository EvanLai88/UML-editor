import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
    private Canvas canvas;
    public JMenu fileMenu, editMenu;
    public JMenuItem changeName, group, ungroup;

    public MenuBar(Canvas c) {
        canvas = c;
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        changeName = new JMenuItem("change object name");
        group = new JMenuItem("Group");
        ungroup = new JMenuItem("UnGroup");

        editMenu.add(changeName);
        editMenu.add(group);
        editMenu.add(ungroup);

        add(fileMenu);
        add(editMenu);
    }
}
