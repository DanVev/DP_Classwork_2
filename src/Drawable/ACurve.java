package Drawable;

import CheckPolicies.ICheck;

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

    @Override
    public double getLength(double t, ICheck checker) {
        double length = 0.0;
        double old_x = a.getX();
        double old_y = a.getY();
        double i = 0;
        do {
            i += 0.01;
            IPoint point = this.getPoint(i);
            double x = point.getX();
            double y = point.getY();
            length += Math.sqrt((x - old_x) * (x - old_x) + (y - old_y) * (y - old_y));
            old_x = x;
            old_y = y;
        } while (checker.check(length, t, i));// while (i < 1) or while(length < t);
        return checker.getValue(length, i); //return length or i
    }
}
