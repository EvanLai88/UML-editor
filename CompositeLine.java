import java.awt.Graphics2D;

import javax.swing.JPanel;

public class CompositeLine extends Line {
    public CompositeLine(JPanel s, JPanel e) {
        super(s, e);
    }

    @Override
    public void drawLine(Graphics2D g2) {
        double sx = (double) getStartPortPosition().x;
        double sy = (double) getStartPortPosition().y;
        double ex = (double) getEndPortPosition().x;
        double ey = (double) getEndPortPosition().y;

        double H = 10, L = 6;
        double awrad = Math.atan(L / H);
        double arraow_len = Math.sqrt(L * L + H * H);
        
        double[] arrXY_1 = rotateVec(ex - sx, ey - sy, awrad, true, arraow_len);
        double[] arrXY_2 = rotateVec(ex - sx, ey - sy, -awrad, true, arraow_len);

        double x_3 = ex - arrXY_1[0];
        double y_3 = ey - arrXY_1[1];
        double x_4 = ex - arrXY_2[0];
        double y_4 = ey - arrXY_2[1];

        double x_5 = x_3 + (x_4 - x_3) / 2;
        double y_5 = y_3 + (y_4 - y_3) / 2;
        double x_6 = ex - (ex - x_5) * 2;
        double y_6 = ey - (ey - y_5) * 2;

        g2.drawLine((int) sx, (int) sy, (int) x_6, (int) y_6);
        g2.drawLine((int) ex, (int) ey, (int) x_3, (int) y_3);
        g2.drawLine((int) ex, (int) ey, (int) x_4, (int) y_4);
        g2.drawLine((int) x_6, (int) y_6, (int) x_3, (int) y_3);
        g2.drawLine((int) x_6, (int) y_6, (int) x_4, (int) y_4);
    }
}
