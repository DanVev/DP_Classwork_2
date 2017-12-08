package Decorators;

import Drawable.IPoint;
import Visual.IActiveComponent;

/**
 * Created by Vasily Danilin on 08.12.2017.
 */
public class ActivePointDecorator implements IPoint, IActiveComponent {
    private IPoint component;

    public ActivePointDecorator(IPoint component) {
        this.component = component;
    }

    @Override
    public double getX() {
        return component.getX();
    }

    @Override
    public double getY() {
        return component.getY();
    }

    @Override
    public void setX(double value) {
        component.setX(value);
    }

    @Override
    public void setY(double value) {
        component.setY(value);
    }

}
