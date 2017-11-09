package Visual;


import Drawable.ICurve;
import Drawable.IPoint;

import java.awt.*;

/**
 * Created by Vasily Danilin on 29.09.2017.
 */
public interface IGContext{
    void drawLine(ICurve c, IPoint a, IPoint b);
    void drawCircle(IPoint a, int radius);
    void drawStartPoint(IPoint a);
    void drawEndPoint(IPoint a);
}
