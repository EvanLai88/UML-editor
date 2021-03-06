import java.awt.Color;
import java.awt.Graphics;

public class Oval extends Object {
    public Oval() {
        super("Usage", 100, 70);
        shape = "Oval";
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        g.fillOval(5, 5, getShapeWidth(), getShapeHeight());
        g.setColor(Color.BLACK);
        g.drawString(Name, 30, 35);
        g.drawOval(5, 5, getShapeWidth(), getShapeHeight());
    }
}