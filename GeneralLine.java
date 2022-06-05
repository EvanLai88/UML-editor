import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import javax.swing.JPanel;

public class GeneralLine extends Line {
    public GeneralLine(JPanel s, JPanel e) {
        super(s, e);
    }
    
    @Override
    public void drawLine(Graphics2D g2) {
        double sx = (double) getStartPortPosition().x;
        double sy = (double) getStartPortPosition().y;
        double ex = (double) getEndPortPosition().x;
        double ey = (double) getEndPortPosition().y;

        double H=12, L=8;
        double awrad = Math.atan(L / H);
        double arraow_len = Math.sqrt(L * L + H * H);
        
        double[] arrXY_1 = rotateVec(ex - sx, ey - sy, awrad, true, arraow_len);
        double[] arrXY_2 = rotateVec(ex - sx, ey - sy, -awrad, true, arraow_len);
        
        double x_3 = ex - arrXY_1[0];
        double y_3 = ey - arrXY_1[1];
        double x_4 = ex - arrXY_2[0];
        double y_4 = ey - arrXY_2[1];
        
        GeneralPath triangle = new GeneralPath();
        triangle.moveTo(ex, ey);
        triangle.lineTo(x_3, y_3);
        triangle.lineTo(x_4, y_4);
        triangle.closePath();
        g2.draw(triangle);
        g2.drawLine((int) sx, (int) sy, (int) (x_3 + (x_4 - x_3) / 2), (int) (y_3 + (y_4 - y_3) / 2));
    }
}
