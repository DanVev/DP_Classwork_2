package Decorators;

import Contexts.IGContext;
import Drawable.IPoint;
import Drawable.Line;
import Drawable.Point;
import Visual.IVisualCurve;
import Visual.VisualCurve;

/**
 * Created by Vasily Danilin on 17.11.2017.
 */
public class ShellDecorator implements IVisualCurve {
    private IVisualCurve component;
    private double margin;

    public ShellDecorator(IVisualCurve component, double margin) {
        this.component = component;
        this.margin = margin;
    }

    @Override
    public void draw(IGContext context) {
        component.draw(context);
        drawShell(context);
    }

    private void drawShell(IGContext context) {
        double old_x = 0;
        double old_y = 0;
        IPoint shellPoint1_old = null;
        IPoint shellPoint2_old = null;

        //shell painting
        for (float i = 0; i <= 1; i += 0.01F) {
            if ((i + 0.01 > 1) && (i != 1))
                i = 0.99F;
            IPoint point = component.getPoint(i);
            double x = point.getX();
            double y = point.getY();
            if (i != 0) {
                double delta = (x - old_x) / (y - old_y);
                double shellPoint1X = margin / Math.sqrt(1 + delta * delta);
                IPoint shellPoint1 = new Point(x + shellPoint1X, y + shellPoint1X * (-delta));
                IPoint shellPoint2 = new Point(x - shellPoint1X, y + shellPoint1X * delta);
                context.drawLine(new Line(shellPoint1_old, shellPoint1), shellPoint1_old, shellPoint1);
                context.drawLine(new Line(shellPoint2_old, shellPoint2), shellPoint1_old, shellPoint1);
                shellPoint1_old = shellPoint1;
                shellPoint2_old = shellPoint2;
                if (i == 1)
                    drawEnds(context,point,new Point(old_x,old_y));
            } else {
                IPoint a = component.getPoint(0.01);
                double delta = (a.getX() - x) / (a.getY() - y);
                double shellPoint1X = margin / Math.sqrt(1 + delta * delta);
                shellPoint1_old = new Point(x + shellPoint1X, y + shellPoint1X * (-delta));
                shellPoint2_old = new Point(x - shellPoint1X, y + shellPoint1X * delta);
                drawEnds(context, point,a);
            }
            old_x = x;
            old_y = y;

        }
    }

    private void drawEnds(IGContext context, IPoint center, IPoint direction) {
        boolean isFirst = true;
        double x_r_old = 0;
        double y_r_old = 0;
        for (double x_r = -margin; x_r >= margin; x_r += 0.01) {
            double y_r1 = Math.sqrt(margin * margin - x_r * x_r);
            double y_r2 = -y_r1;
            double y_r = (x_r * direction.getX() + y_r1 * direction.getY() < 0) ? y_r1 : y_r2;
            if (x_r != -margin)
                context.drawLine(new Line(new Point(x_r_old, y_r_old), new Point(x_r, y_r)));
            x_r_old = x_r;
            y_r_old = y_r;
        }
    }

    @Override
    public IPoint getPoint(double t) {
        return component.getPoint(t);
    }

    @Override
    public void drawWithoutAnyPoints(IGContext context) {
        component.drawWithoutAnyPoints(context);
        drawShell(context);
    }
}
