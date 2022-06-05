import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.util.jar.Attributes.Name;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ClassTable extends Object {
    public ClassTable() {
        super("Object Name", 100, 130);
        shape = "Rectangle";
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        g.fillRect(5, 5, getShapeWidth(), getShapeHeight());
        g.setColor(Color.BLACK);
        g.drawString(Name, 10, 30);
        g.drawRect(5, 5, getShapeWidth(), getShapeHeight()/3);
        g.drawRect(5, 45, getShapeWidth(), getShapeHeight()/3);
        g.drawRect(5, 85, getShapeWidth(), getShapeHeight()/3);
    }
}