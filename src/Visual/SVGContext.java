package Visual;

import Drawable.ICurve;
import Drawable.IPoint;
import org.omg.CORBA.INV_POLICY;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vasily Danilin on 10.11.2017.
 */
public class SVGContext implements IGContext {
    List<String> content = new ArrayList<>();

    public String getXML() {
        final String[] inner = {""};
        content.forEach((i) -> inner[0] += (i + "\n"));
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<svg version = \"1.1\"\n" +
                "     baseProfile=\"full\"\n" +
                "     xmlns = \"http://www.w3.org/2000/svg\" \n" +
                "     xmlns:xlink = \"http://www.w3.org/1999/xlink\"\n" +
                "     xmlns:ev = \"http://www.w3.org/2001/xml-events\"\n" +
                "     height = \"400px\"  width = \"400px\">\n" +
                inner[0] + "</svg>";
    }

    @Override
    public void drawLine(ICurve c, IPoint a, IPoint b) {
        content.add(String.format("<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" stroke-width=\"1\" stroke=\"rgb(0,0,0)\"/>", (int) a.getX(), (int) a.getY(), (int) b.getX(), (int) b.getY()));
    }

    @Override
    public void drawCircle(IPoint a, int radius) {
        content.add(String.format(" <circle cx=\"%d\" cy=\"%d\" r=\"%d\"/>",(int)a.getX(),(int)a.getY(),radius));
    }

    @Override
    public void drawStartPoint(IPoint a) {
        drawCircle(a,2);
    }

    @Override
    public void drawEndPoint(IPoint a) {
        drawCircle(a,2);
    }
    public void createSVGContent(ICurve c, IPoint a, IPoint b)
    {
        drawLine(c,a,b);
        drawStartPoint(a);
        drawEndPoint(b);
    }
}
