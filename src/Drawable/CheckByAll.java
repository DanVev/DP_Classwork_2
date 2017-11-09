package Drawable;

/**
 * Created by Vasily Danilin on 03.11.2017.
 */
public class CheckByAll implements ICheck {
    @Override
    public boolean check(double length, double t,double i) {
        return i < 1;
    }

    @Override
    public double getValue(double length, double i) {
        return length;
    }
}
