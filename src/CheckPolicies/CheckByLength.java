package CheckPolicies;

/**
 * Created by Vasily Danilin on 03.11.2017.
 */
public class CheckByLength implements ICheck{
    @Override
    public boolean check(double length, double t, double i) {
        return length < t;
    }

    @Override
    public double getValue(double length, double i) {
        return i;
    }
}
