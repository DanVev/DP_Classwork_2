package Visual;

import Drawable.ACurve;
import Drawable.Bezier;
import Drawable.IPoint;
import com.sun.prism.paint.Color;

import java.awt.*;

/**
 * Created by Vasily Danilin on 20.10.2017.
 */
public class VisualBezier extends VisualCurve {
    public VisualBezier(Bezier curve) {
        super(curve);
    }
}
