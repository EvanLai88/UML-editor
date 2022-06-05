import java.awt.Point;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Line {
    protected JPanel start, end;

    public Line(JPanel s, JPanel e) {
        start = s;
        end = e;
    }

    public Point getStartPortPosition() {
        return SwingUtilities.convertPoint(start, start.getWidth()/2, start.getHeight()/2, Canvas.getInstance());
    }
    
    public Point getEndPortPosition() {
        return SwingUtilities.convertPoint(end, end.getWidth()/2, end.getHeight()/2, Canvas.getInstance());
    }

    public void drawLine(Graphics2D g2) {}

    public double[] rotateVec(double e, double f, double ang,
            boolean isChLen, double newLen) {
        double mathstr[] = new double[2];
        double vx = e * Math.cos(ang) - f * Math.sin(ang);
        double vy = e * Math.sin(ang) + f * Math.cos(ang);
        if (isChLen) {
            double d = Math.sqrt(vx * vx + vy * vy);
            vx = vx / d * newLen;
            vy = vy / d * newLen;
            mathstr[0] = vx;
            mathstr[1] = vy;
        }
        return mathstr;
    }
}