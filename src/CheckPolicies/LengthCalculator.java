package CheckPolicies;

import Drawable.ICurve;
import Drawable.IPoint;

/**
 * Created by Vasily Danilin on 17.11.2017.
 */
public class LengthCalculator {
    public double getLength(ICurve c, double t, ICheck checker) {
        double length = 0.0;
        IPoint a = c.getPoint(0);
        double old_x = a.getX();
        double old_y = a.getY();
        double i = 0;
        do {
            i += 0.01;
            IPoint point = c.getPoint(i);
            double x = point.getX();
            double y = point.getY();
            length += Math.sqrt((x - old_x) * (x - old_x) + (y - old_y) * (y - old_y));
            old_x = x;
            old_y = y;
        } while (checker.check(length, t, i));// while (i < 1) or while(length < t);
        return checker.getValue(length, i); //return length or i}
    }
}
