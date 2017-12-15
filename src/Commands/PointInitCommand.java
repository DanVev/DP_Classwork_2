package Commands;

import Drawable.IPoint;

/**
 * Created by Vasily Danilin on 15.12.2017.
 */
public class PointInitCommand extends ACommand {
    private IPoint point;
    private double x;
    private double y;

    public PointInitCommand(IPoint point, double x, double y) {
        this.point = point;
        this.x = x;
        this.y = y;
    }

    public PointInitCommand(IPoint point, IPoint values) {
        this.point = point;
        this.x = values.getX();
        this.y = values.getY();
    }

    public PointInitCommand(IPoint point) {
        this.point = point;
        this.x = point.getX();
        this.y = point.getY();
    }

    @Override
    protected void doExecute() {
        point.setX(x);
        point.setY(y);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
