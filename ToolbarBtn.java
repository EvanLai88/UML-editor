import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ToolbarBtn extends JButton {
    public ToolbarBtn(String s, ImageIcon imageIcon) {
        setName(s);
        setIcon(imageIcon);
        setBackground(Color.LIGHT_GRAY);
        setOpaque(true);
        setBorderPainted(false);
        setFocusPainted(false);
    }    
}
