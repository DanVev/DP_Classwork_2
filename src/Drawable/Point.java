package Drawable;

/**
 * Created by Vasily Danilin on 29.09.2017.
 */
public class Point implements IPoint {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setX(double value) {
        this.x = value;
    }

    @Override
    public void setY(double value) {
        this.y = value;
    }

    @Override
    public String toString() {
        return "(" +
                x +
                ", " + y +
                ')';
    }
}
