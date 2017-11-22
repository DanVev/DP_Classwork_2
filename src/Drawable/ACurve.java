package Drawable;

import CheckPolicies.ICheck;
import CheckPolicies.LengthCalculator;

/**
 * Created by Vasily Danilin on 29.09.2017.
 */
public abstract class ACurve implements ICurve {
    protected IPoint a;
    protected IPoint b;

    public ACurve(IPoint a, IPoint b) {
        this.a = a;
        this.b = b;
    }

    public IPoint getA() {
        return a;
    }

    public IPoint getB() {
        return b;
    }

    @Override
    public String toString() {
        return "{" + a +
                ", " + b +
                '}';
    }

    public double getLength(double t, ICheck checker) {
        return LengthCalculator.getLength(this, t, checker);
    }
}
