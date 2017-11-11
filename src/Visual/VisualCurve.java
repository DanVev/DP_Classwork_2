package Visual;

import Contexts.IGContext;
import Drawable.*;

/**
 * Created by Vasily Danilin on 29.09.2017.
 */
public abstract class VisualCurve implements ICurve, IDrawable {
    protected ACurve curve;

    public VisualCurve(ACurve curve) {
        this.curve = curve;
    }

    @Override
    public IPoint getPoint(double t) {
        return curve.getPoint(t);
    }

    @Override
    public void draw(IGContext context) {
        context.drawLine(curve, curve.getA(),curve.getB());
        double l = curve.getLength(1, new CheckByAll());
        double central_t = curve.getLength(l/2, new CheckByLength());
        context.drawCircle(curve.getPoint(central_t),5);
        context.drawStartPoint(curve.getA());
        context.drawEndPoint(curve.getB());
    }

    @Override
    public double getLength(double t, ICheck checker) {
        return curve.getLength(t, checker);
    }
}
