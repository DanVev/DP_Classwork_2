package Visual;

import CheckPolicies.CheckByAll;
import CheckPolicies.CheckByLength;
import CheckPolicies.LengthCalculator;
import Contexts.IGContext;
import Drawable.*;

/**
 * Created by Vasily Danilin on 29.09.2017.
 */
public class VisualCurve implements IVisualCurve {
    private ICurve curve;
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
        context.drawLine(curve);
        LengthCalculator calculator = new LengthCalculator();
        double l = calculator.getLength(curve, 1, new CheckByAll());
        double central_t = calculator.getLength(curve, l / 2, new CheckByLength());
        context.drawCircle(curve.getPoint(central_t), 5);
        context.drawStartPoint(a);
        context.drawEndPoint(b, curve);
    }

    @Override
    public void drawWithoutAnyPoints(IGContext context) {
        context.drawLine(curve);
    }

}
