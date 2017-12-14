package Decorators;

import Contexts.IGContext;
import Drawable.IPoint;
import Drawable.Line;
import Drawable.Point;
import Drawable.PointCalculator;
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

    @SuppressWarnings("ConstantConditions")
    private void drawShell(IGContext context) {
        double old_x = 0;
        double old_y = 0;
        IPoint shellPoint1_old = null;
        IPoint shellPoint2_old = null;

        //shell painting
        final float step = 0.005F;
        final double angleThreshold = (45.0 / 180.0) * Math.PI;
        for (float i = 0; i <= 1; i += step) {
            if ((i + step > 1) && (i != 1))
                i = 1 - step;
            IPoint point = component.getPoint(i);
            double x = point.getX();
            double y = point.getY();
            if (i != 0) {
                double delta = ((x - old_x) / (y - old_y));
                double shellPoint1X = margin / Math.sqrt(1 + delta * delta);
                Point shiftPoint = new Point(((y - old_y) > 0 ? 1 : -1) * shellPoint1X,
                        (((y - old_y) > 0 ? 1 : -1) * shellPoint1X * (-delta)));
                IPoint shellPoint1 = PointCalculator.add(point, shiftPoint);
                IPoint shellPoint2 = PointCalculator.subtract(point, shiftPoint);
                IPoint shellVector1_old = new Point(shellPoint1_old.getX() - old_x, shellPoint1_old.getY() - old_y);
                IPoint shellVector2_old = new Point(shellPoint2_old.getX() - old_x, shellPoint2_old.getY() - old_y);

                IPoint shellVector1 = PointCalculator.subtract(shellPoint1, point);
                IPoint shellVector2 = PointCalculator.subtract(shellPoint2, point);

                //context.drawCircle(shellPoint1, 2);
                //System.out.println("length for sp1 = "+PointCalculator.getDistance(shellPoint1, point));

                if (!hasIntersection(i, shellPoint1, 0.5F, step)) {
                    double angle = getCosAngle(shellVector1_old, shellVector1);
                    if (angle <= angleThreshold) {
                        context.drawLine(new Line(shellPoint1_old, shellPoint1));
                        //context.drawCircle(shellPoint1,2);
                    } else {
                        IPoint middlePoint = getMiddlePoint(shellPoint1_old, step, i, shellPoint1);
                        context.drawLine(new Line(shellPoint1_old, middlePoint));
                        context.drawLine(new Line(middlePoint, shellPoint1));
                        //context.drawCircle(shellPoint1,2);
                    }
                    shellPoint1_old = shellPoint1;
                    //context.drawCircle(shellPoint1, 2);
                }
                if (!hasIntersection(i, shellPoint2, 0.5F, step)) {
                    if (getCosAngle(shellVector2_old, shellVector2) <= angleThreshold) {
                        context.drawLine(new Line(shellPoint2_old, shellPoint2));
                    } else {
                        IPoint middlePoint = getMiddlePoint(shellPoint2_old, step, i, shellPoint2);
                        context.drawLine(new Line(shellPoint2_old, middlePoint));
                        context.drawLine(new Line(middlePoint, shellPoint2));
                    }
                    shellPoint2_old = shellPoint2;
                }
                if (i == 1)
                    drawEnds(context, point, shellPoint1_old);
            } else {
                IPoint a = component.getPoint(step);
                double delta = ((a.getX() - x) / (a.getY() - y));
                double shellPoint1X = margin / Math.sqrt(1 + delta * delta);
                Point shiftPoint = new Point(((a.getY() - y) > 0 ? 1 : -1) * shellPoint1X,
                        (-delta) * (((a.getY() - y) > 0 ? 1 : -1) * shellPoint1X));
                shellPoint1_old = PointCalculator.add(point, shiftPoint);
                shellPoint2_old = PointCalculator.subtract(point, shiftPoint);


                IPoint shellVector1_old = PointCalculator.subtract(shellPoint1_old, point);
                IPoint directVector = new Point(x - a.getX(), y - a.getY());
                drawEnds(context, point, shellPoint2_old);
            }
            old_x = x;
            old_y = y;

        }
    }

    private IPoint getMiddlePoint(IPoint shellPoint1_old, float step, float i, IPoint shellPoint1) {
        IPoint middlePoint_outer = new Point((shellPoint1_old.getX() + shellPoint1.getX()) / 2, (shellPoint1_old.getY() + shellPoint1.getY()) / 2);
        IPoint middlePoint_inner = component.getPoint(i - step / 2);
        return new Point((middlePoint_outer.getX() - middlePoint_inner.getX()) * 1.4 + middlePoint_inner.getX(), (middlePoint_outer.getY() - middlePoint_inner.getY()) * 1.4 + middlePoint_inner.getY());
    }

    @SuppressWarnings("SameParameterValue")
    private boolean hasIntersection(float t, IPoint shellPoint, float environment, float precision) {
        for (float i = Math.max(0, t - environment); i <= Math.min(1, t + environment); i += precision) {
            IPoint point = component.getPoint(i);
            if (PointCalculator.getDistance(point, shellPoint) < (margin - 0.1))
                return true;
        }
        return false;
    }


    private double getCosAngle(IPoint vector1, IPoint vector2) {
        double scalar = PointCalculator.scalarProduct(vector1, vector2);
        double length1 = PointCalculator.getLength(vector1);
        double length2 = PointCalculator.getLength(vector2);
        return Math.acos(scalar / (length1 * length2 + 0.01));
    }


    private void drawEnds(IGContext context, IPoint center, IPoint shellPoint1) {
        double x_r_old = 0;
        double y_r_old = 0;
        final double step = 0.1;
        double tan = (shellPoint1.getY() - center.getY()) / (shellPoint1.getX() - center.getX());
        double startAngle = Math.atan(tan) + (((margin) * (shellPoint1.getX() - center.getX())) > 0 ? 0 : Math.PI);
        for (double angle = startAngle; angle <= startAngle + Math.PI; angle += step) {
            double x_r = margin * Math.cos(angle);
            double y_r = margin * Math.sin(angle);
            if (angle != startAngle)
                context.drawLine(new Line(new Point(center.getX() + x_r_old, center.getY() + y_r_old),
                        new Point(center.getX() + x_r, center.getY() + y_r)));
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
