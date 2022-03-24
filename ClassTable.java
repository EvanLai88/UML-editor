import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.util.jar.Attributes.Name;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ClassTable extends Object {
    public ClassTable(Canvas canvas) {
        super(canvas, "Object Name", 100, 130);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        g.fillRect(5, 5, 90, 120);
        g.setColor(Color.BLACK);
        g.drawString(Name, 10, 30);
        g.drawRect(5, 5, 90, 40);
        g.drawRect(5, 45, 90, 40);
        g.drawRect(5, 85, 90, 40);
    }
}