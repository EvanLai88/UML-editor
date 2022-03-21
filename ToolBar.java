import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

public class ToolBar {
    private static JToolBar toolbar;
    private static JButton selectBtn, assoBtn, genBtn, comBtn, classBtn, useCaseBtn;

    public ToolBar() {
        ImageIcon selIcon = new ImageIcon("img/select.png");
        ImageIcon assIcon = new ImageIcon("img/associate.png");
        ImageIcon genIcon = new ImageIcon("img/general.png");
        ImageIcon comIcon = new ImageIcon("img/composite.png");
        ImageIcon claIcon = new ImageIcon("img/class.png");
        ImageIcon useIcon = new ImageIcon("img/usecase.png");
        
        toolbar = new JToolBar(null, JToolBar.VERTICAL);
        selectBtn = new JButton(selIcon);
        assoBtn = new JButton(assIcon);
        genBtn = new JButton(genIcon);
        comBtn = new JButton(comIcon);
        classBtn = new JButton(claIcon);
        useCaseBtn = new JButton(useIcon);

        toolbar.setFloatable(false);
        toolbar.add(selectBtn);
        toolbar.add(assoBtn);
        toolbar.add(genBtn);
        toolbar.add(comBtn);
        toolbar.add(classBtn);
        toolbar.add(useCaseBtn);

        Border blackline = BorderFactory.createLineBorder(Color.black);
        toolbar.setBorder(blackline);

    }

    public static JToolBar getInstance() {
        return toolbar;
    }
}
