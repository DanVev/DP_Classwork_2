package Contexts;

import Drawable.*;
import Drawable.Point;

import java.awt.*;

/**
 * Created by Vasily Danilin on 20.10.2017.
 */
public class Graphics2DDottedContext extends Graphics2DContext implements IGContext {
    public Graphics2DDottedContext(Graphics g2d) {
        super(g2d);
    }

    @Override
    protected void initGraphicsParams() {
        super.initGraphicsParams();
        g2d.setPaint(Color.BLACK);
    }

    @Override
    public void drawLine(ICurve c, IPoint a, IPoint b) {
        initGraphicsParams();
        int old_x = 0;
        int old_y = 0;
        for (double i = 0; i <= 1; i+=0.05) {
            IPoint point = c.getPoint(i);
            int x = (int) point.getX();
            int y = (int) point.getY();
            if ((i+0.05>1)&&(i!=1))
                i=1-0.05;
            ICurve segment = new Line(new Point(old_x, old_y), point);
            if (i != 0) {
                IPoint startDot = new Point(segment.getPoint(0.33).getX(), segment.getPoint(0.33).getY());
                IPoint finishDot = new Point(segment.getPoint(0.66).getX(), segment.getPoint(0.66).getY());
                g2d.drawLine(old_x, old_y, (int) startDot.getX(), (int) startDot.getY());
                g2d.drawLine((int) finishDot.getX(), (int) finishDot.getY(), x, y);
            }
            old_x = x;
            old_y = y;
        }
    }

    @Override
    public void drawStartPoint(IPoint a) {
        initGraphicsParams();
        g2d.drawRect((int) a.getX() - 3, (int) a.getY() - 3, 6, 6);
    }

    @Override
    public void drawEndPoint(IPoint a) {
        initGraphicsParams();
        g2d.drawRect((int) a.getX() - 3, (int) a.getY() - 3, 6, 6);
    }
}
