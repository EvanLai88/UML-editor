import java.awt.Graphics2D;

import javax.swing.JPanel;

public class AssociateLine extends Line {
    public AssociateLine(JPanel s, JPanel e) {
        super(s, e);
    }

    @Override
    public void drawLine(Graphics2D g2) {
        double sx = (double) getStartPortPosition().x;
        double sy = (double) getStartPortPosition().y;
        double ex = (double) getEndPortPosition().x;
        double ey = (double) getEndPortPosition().y;

        double H = 12, L = 8;
        double awrad = Math.atan(L / H);
        double arraow_len = Math.sqrt(L * L + H * H);
        
        double[] arrXY_1 = rotateVec(ex - sx, ey - sy, awrad, true, arraow_len);
        double[] arrXY_2 = rotateVec(ex - sx, ey - sy, -awrad, true, arraow_len);
        
        double x_3 = ex - arrXY_1[0];
        double y_3 = ey - arrXY_1[1];
        double x_4 = ex - arrXY_2[0];
        double y_4 = ey - arrXY_2[1];
        
        g2.drawLine((int) sx, (int) sy, (int) ex, (int) ey);
        g2.drawLine((int) ex, (int) ey, (int) x_3, (int) y_3);
        g2.drawLine((int) ex, (int) ey, (int) x_4, (int) y_4);
    }
}
