package Contexts;

import Drawable.*;
import Drawable.Point;

import java.awt.*;

/**
 * Created by Vasily Danilin on 20.10.2017.
 */
public class Graphics2DContext implements IGContext {
    Graphics2D g2d;

    public Graphics2DContext(Graphics g2d) {
        this.g2d = (Graphics2D) g2d;
    }

    @Override
    public void drawLine(ICurve c) {
        initGraphicsParams();
        int old_x = 0;
        int old_y = 0;
        for (float i = 0; i <= 1; i += 0.01) {
            IPoint point = c.getPoint(i);
            int x = (int) point.getX();
            int y = (int) point.getY();
            if (i != 0)
                g2d.drawLine(old_x, old_y, x, y);
            old_x = x;
            old_y = y;
        }
    }

    protected void initGraphicsParams() {
        g2d.setPaint(new Color(60, 60, 214));
        g2d.setStroke(new BasicStroke(2));
    }

    @Override
    public void drawCircle(IPoint a, int radius) {
        g2d.drawOval((int) a.getX() - radius, (int) a.getY() - radius, 2 * radius, 2 * radius);
    }

    @Override
    public void drawStartPoint(IPoint a) {
        initGraphicsParams();
        drawCircle(a, 2 * 3);
    }

    @Override
    public void drawEndPoint(IPoint a, ICurve c) {
        initGraphicsParams();
        IPoint point = c.getPoint(0.98);
        double delta = (a.getX() - point.getX()) / (a.getY() - point.getY());
        double arrowPoint_x = (8 / 2) / Math.sqrt(1 + delta * delta);
        IPoint arrowPoint1 = new Point(a.getX() + arrowPoint_x, a.getY() + arrowPoint_x * (-delta));
        IPoint arrowPoint2 = new Point(a.getX() - arrowPoint_x, a.getY() + arrowPoint_x * delta);
        g2d.fillPolygon(new int[]{(int) (2*a.getX()-point.getX()),(int) arrowPoint1.getX(), (int) arrowPoint2.getX()}, new int[]{(int) (2*a.getY()-point.getY()),(int) arrowPoint1.getY(), (int) arrowPoint2.getY()},3);


    }


}
