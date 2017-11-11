package Drawable;

import CheckPolicies.ICheck;

/**
 * Created by Vasily Danilin on 29.09.2017.
 */
public interface ICurve {
    IPoint getPoint(double t);
    double getLength(double t, ICheck checker);
}
