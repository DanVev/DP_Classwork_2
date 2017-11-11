package Visual;

import CheckPolicies.CheckByAll;
import CheckPolicies.CheckByLength;
import CheckPolicies.ICheck;
import Contexts.IGContext;
import Drawable.*;

/**
 * Created by Vasily Danilin on 29.09.2017.
 */
public class VisualCurve implements ICurve, IDrawable {
    protected ICurve curve;
    private IPoint a;
    private IPoint b;

    public VisualCurve(ICurve curve) {
        this.curve = curve;
        a = curve.getPoint(0);
        b = curve.getPoint(1);
    }

    @Override
    public IPoint getPoint(double t) {
        return curve.getPoint(t);
    }

    @Override
    public void draw(IGContext context) {
        context.drawLine(curve, a, b);
        double l = curve.getLength(1, new CheckByAll());
        double central_t = curve.getLength(l / 2, new CheckByLength());
        System.out.println(central_t);
        context.drawCircle(curve.getPoint(central_t), 5);
        context.drawStartPoint(a);
        context.drawEndPoint(b);
    }

    @Override
    public void drawWithoutAnyPoints(IGContext context) {
        context.drawLine(curve, a, b);
    }

    @Override
    public double getLength(double t, ICheck checker) {
        return curve.getLength(t, checker);
    }
}
