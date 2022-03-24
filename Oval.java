import java.awt.Color;
import java.awt.Graphics;

public class Oval extends Object {
    public Oval() {
        super("Usage", 100, 70, 30, 14, 80, 40);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.drawOval(5, 5, 90, 60);
    }


    
}