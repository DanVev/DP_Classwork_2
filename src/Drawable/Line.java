package Drawable;

/**
 * Created by Vasily Danilin on 29.09.2017.
 */
public class Line extends ACurve {
    public Line(IPoint a, IPoint b) {
        super(a, b);
    }

    @Override
    public IPoint getPoint(double t) {
        double new_x = (1-t)*this.a.getX()+ t*this.b.getX();
        double new_y = (1-t)*this.a.getY()+ t*this.b.getY();
        return new Point(new_x,new_y);
    }
}
