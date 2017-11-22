package Contexts;


import Drawable.ICurve;
import Drawable.IPoint;

/**
 * Created by Vasily Danilin on 29.09.2017.
 */
public interface IGContext{
    void drawLine(ICurve c);
    void drawCircle(IPoint a, int radius);
    void drawStartPoint(IPoint a);
    void drawEndPoint(IPoint a, ICurve c);
}
