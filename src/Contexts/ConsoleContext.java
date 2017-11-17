package Contexts;

import Drawable.ICurve;
import Drawable.IPoint;

/**
 * Created by Vasily Danilin on 20.10.2017.
 */
public class ConsoleContext implements IGContext{
    @Override
    public void drawLine(ICurve c) {
        System.out.println("Drawing line:"+c.toString());
    }

    @Override
    public void drawCircle(IPoint a, int radius) {
        System.out.println("Drawing point: "+ a.toString());
    }

    @Override
    public void drawStartPoint(IPoint a) {

    }

    @Override
    public void drawEndPoint(IPoint a) {

    }
}
