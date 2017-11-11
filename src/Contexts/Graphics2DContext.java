package Contexts;

import Drawable.*;

import java.awt.*;

/**
 * Created by Vasily Danilin on 20.10.2017.
 */
public class Graphics2DContext implements IGContext {
    protected Graphics2D g2d;

    public Graphics2DContext(Graphics g2d) {
        this.g2d = (Graphics2D)g2d;
    }

    public Graphics2DContext() {
    }

    @Override
    public void drawLine(ICurve c, IPoint a, IPoint b) {
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
        g2d.setPaint(Color.GREEN);
        g2d.setStroke(new BasicStroke(2));
    }

    @Override
    public void drawCircle(IPoint a, int radius) {
        g2d.drawOval((int)a.getX()-radius,(int)a.getY()-radius,2*radius,2*radius);
    }

    public void setG2d(Graphics2D g2d) {
        this.g2d = g2d;
    }

    @Override
    public void drawStartPoint(IPoint a) {
        initGraphicsParams();
        drawCircle(a, 2*3);
    }

    @Override
    public void drawEndPoint(IPoint a) {
        initGraphicsParams();
        g2d.drawLine((int)a.getX()-6,(int)a.getY(),(int)a.getX(),(int)a.getY());
        g2d.drawLine((int)a.getX(),(int)a.getY()-6,(int)a.getX(),(int)a.getY());
    }
}
