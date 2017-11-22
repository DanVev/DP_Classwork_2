package Decorators;

import Contexts.IGContext;
import Drawable.IPoint;
import Drawable.Line;
import Drawable.Point;
import Visual.IVisualCurve;

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
        final float step = 0.01F;
        for (float i = 0; i <= 1; i += step) {
            if ((i + step > 1) && (i != 1))
                i = 1 - step;
            IPoint point = component.getPoint(i);
            double x = point.getX();
            double y = point.getY();
            if (i != 0) {
                double delta = (x - old_x) / (y - old_y);
                double shellPoint1X = margin / Math.sqrt(1 + delta * delta);
                IPoint shellPoint1 = new Point(x + shellPoint1X, y + shellPoint1X * (-delta));
                IPoint shellPoint2 = new Point(x - shellPoint1X, y + shellPoint1X * delta);
                context.drawLine(new Line(shellPoint1_old, shellPoint1));
                context.drawLine(new Line(shellPoint2_old, shellPoint2));
                shellPoint1_old = shellPoint1;
                shellPoint2_old = shellPoint2;
                if (i == 1)
                    drawEnds(context, point, shellPoint2_old);
            } else {
                IPoint a = component.getPoint(step);
                double delta = (a.getX() - x) / (a.getY() - y);
                double shellPoint1X = margin / Math.sqrt(1 + delta * delta);
                shellPoint1_old = new Point(x + shellPoint1X, y + shellPoint1X * (-delta));
                shellPoint2_old = new Point(x - shellPoint1X, y + shellPoint1X * delta);
                drawEnds(context, point, shellPoint1_old);
            }
            old_x = x;
            old_y = y;

        }
    }

    private void drawEnds(IGContext context, IPoint center, IPoint shellPoint1) {
        double x_r_old = 0;
        double y_r_old = 0;
        final double step = 0.1;
        double tan = (shellPoint1.getY() - center.getY()) / (shellPoint1.getX() - center.getX());
        double startAngle = Math.atan(tan) + (((margin) * (shellPoint1.getY() - center.getY())) > 0 ? 0 : Math.PI);
        for (double angle = startAngle; angle <= startAngle + Math.PI; angle += step) {
            double x_r = margin * Math.cos(angle);
            double y_r = margin * Math.sin(angle);
            if (angle != startAngle)
                context.drawLine(new Line(new Point(center.getX() + x_r_old, center.getY() + y_r_old), new Point(center.getX() + x_r, center.getY() + y_r)));
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
