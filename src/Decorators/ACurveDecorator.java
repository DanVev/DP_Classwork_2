package Decorators;

import CheckPolicies.ICheck;
import Drawable.ICurve;
import Drawable.IPoint;

/**
 * Created by Vasily Danilin on 11.11.2017.
 */
public abstract class ACurveDecorator implements ICurve {
    protected ICurve component;

    public ACurveDecorator(ICurve component) {
        this.component = component;
    }

    @Override
    public IPoint getPoint(double t) {
        return component.getPoint(t);
    }
}
