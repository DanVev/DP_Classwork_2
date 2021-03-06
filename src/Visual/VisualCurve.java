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

    public VisualCurve(ICurve curve) {
        this.curve = curve;
    }

    @Override
    public IPoint getPoint(double t) {
        return curve.getPoint(t);
    }

    @Override
    public void draw(IGContext context) {
        context.drawLine(curve);
        double l = LengthCalculator.getLength(curve, 1, new CheckByAll());
        double central_t = LengthCalculator.getLength(curve, l / 2, new CheckByLength());
        context.drawCircle(curve.getPoint(central_t), 5);
        context.drawStartPoint(curve.getPoint(0));
        context.drawEndPoint(curve.getPoint(1), curve);
    }

    @Override
    public void drawWithoutAnyPoints(IGContext context) {
        context.drawLine(curve);
    }

}
