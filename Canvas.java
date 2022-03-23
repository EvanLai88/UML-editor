import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

public class Canvas extends JPanel {
    public Canvas() {
        super();
        setLayout(null);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
    }
}
