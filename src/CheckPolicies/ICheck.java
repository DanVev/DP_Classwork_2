package CheckPolicies;

/**
 * Created by Vasily Danilin on 03.11.2017.
 */
public interface ICheck {
    boolean check(double length, double t, double i);

    double getValue(double length, double i);
}
