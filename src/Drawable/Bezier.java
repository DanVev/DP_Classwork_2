package Drawable;

/**
 * Created by Vasily Danilin on 29.09.2017.
 */
public class  Bezier extends ACurve {
    private IPoint c;
    private IPoint d;
    public Bezier(IPoint a, IPoint c, IPoint d, IPoint b) {
        super(a, b);
        this.c = c;
        this.d = d;
    }

    @Override
    public IPoint getPoint(double t) {
        double new_x = (1-t)*(1-t)*(1-t)*a.getX() + 3*t*(1-t)*(1-t)*c.getX() + 3*t*t*(1-t)*d.getX() + t*t*t*b.getX();
        double new_y = (1-t)*(1-t)*(1-t)*a.getY() + 3*t*(1-t)*(1-t)*c.getY() + 3*t*t*(1-t)*d.getY() + t*t*t*b.getY();
        return new Point(new_x,new_y);
    }

    public IPoint getC() {
        return c;
    }

    public IPoint getD() {
        return d;
    }
}
