package Decorators;

import Drawable.ICurve;

/**
 * Created by Vasily Danilin on 17.11.2017.
 */
public class ShellDecorator extends ACurveDecorator{
    public ShellDecorator(ICurve component) {
        super(component);
    }

}
