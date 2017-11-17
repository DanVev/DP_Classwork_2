package Decorators;

import Contexts.IGContext;
import Drawable.IPoint;
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
        int old_x = 0;
        int old_y = 0;

        //shell painting
        for (float i = 0; i <= 1; i += 0.01) {
            IPoint point = component.getPoint(i);
            int x = (int) point.getX();
            int y = (int) point.getY();
            if (i != 0)

                old_x = x;
            old_y = y;
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
