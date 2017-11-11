package Decorators;

import Drawable.ICurve;
import Drawable.IPoint;
import Drawable.Point;

/**
 * Created by Vasily Danilin on 11.11.2017.
 */
public class MoveDecorator extends ACurveDecorator{
    private IPoint dstPoint;
    private IPoint startPoint;

    public MoveDecorator(ICurve component, IPoint dstPoint) {
        super(component);
        this.dstPoint = dstPoint;
        this.startPoint = component.getPoint(0);
    }

    @Override
    public IPoint getPoint(double t) {
        IPoint point = super.getPoint(t);
        return new Point(point.getX()+ dstPoint.getX() - startPoint.getX(),point.getY()+ dstPoint.getY() - startPoint.getY());
    }
}
