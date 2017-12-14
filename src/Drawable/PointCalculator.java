package Drawable;

/**
 * Created by Vasily Danilin on 09.12.2017.
 */
public class PointCalculator {
    public static double getDistance(IPoint point1, IPoint point2) {
        return Math.sqrt((point1.getX() - point2.getX()) * (point1.getX() - point2.getX()) + (point1.getY() - point2.getY()) * (point1.getY() - point2.getY()));
    }

    public static double scalarProduct(IPoint point1, IPoint point2) {
        return point1.getX() * point2.getX() + point1.getY() * point2.getY();
    }

    public static IPoint multiply(IPoint point, double scalar) {
        return new Point(point.getX() * scalar, point.getY() * scalar);
    }

    public static IPoint add(IPoint point1, IPoint point2) {
        return new Point(point1.getX() + point2.getX(), point1.getY() + point2.getY());
    }

    public static IPoint subtract(IPoint point1, IPoint point2) {
        return new Point(point1.getX() - point2.getX(), point1.getY() - point2.getY());
    }

    public static double getLength(IPoint point) {
        return Math.sqrt(point.getX() * point.getX() + point.getY() * point.getY());
    }
}
