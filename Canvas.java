import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

public class Canvas {
    private static JPanel canvas;

    public Canvas() {
        canvas = new JPanel();
        Border blackline = BorderFactory.createLineBorder(Color.black);
        canvas.setBorder(blackline);
    }

    public static JPanel getInstance() {
        return canvas;
    }
}
