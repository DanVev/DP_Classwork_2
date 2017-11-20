package Composite;

import Drawable.ICurve;
import Drawable.IPoint;

/**
 * Created by Vasily Danilin on 12.11.2017.
 */
public class Chain implements ICurve {
    private ICurve first;
    private ICurve second;

    public Chain(ICurve curve1, ICurve curve2) {
        this.first = curve1;
        this.second = curve2;
    }

    public ICurve getFirst() {
        return first;
    }

    public void setFirst(ICurve first) {
        this.first = first;
    }

    public ICurve getSecond() {
        return second;
    }

    public void setSecond(ICurve second) {
        this.second = second;
    }

    @Override
    public IPoint getPoint(double t) {
        if (t <= 0.5)
            return first.getPoint(2 * t);
        else
            return second.getPoint(2 * (t - 0.5));
    }
}
