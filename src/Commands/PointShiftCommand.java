package Commands;

import Drawable.IPoint;
import Drawable.Point;

/**
 * Created by Vasily Danilin on 15.12.2017.
 */
public class PointShiftCommand extends ACommand {
    private IPoint point;
    private IPoint coords;

    public PointShiftCommand(IPoint point, IPoint shift) {
        this.point = point;
        this.coords = new Point(shift.getX(), shift.getY());
    }

    @Override
    void doExecute() {
        point.setX(point.getX() + coords.getX());
        point.setY(point.getY() + coords.getY());
    }
}
