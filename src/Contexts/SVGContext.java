package Contexts;

import Drawable.ICurve;
import Drawable.IPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vasily Danilin on 10.11.2017.
 */
public class SVGContext implements IGContext {
    private List<String> content = new ArrayList<>();

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
    public void drawLine(ICurve c) {
        int old_x = 0;
        int old_y = 0;
        for (double i = 0; i <= 1; i += 0.05) {
            if ((i + 0.05 > 1) && (i != 1))
                i = 1 - 0.05;
                IPoint point = c.getPoint(i);
                int x = (int) point.getX();
                int y = (int) point.getY();
                if (i != 0)
                    content.add(String.format("<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" stroke-width=\"1\" stroke=\"rgb(0,0,0)\"/>", old_x, old_y, x, y));
                old_x = x;
                old_y = y;

        }
    }

    @Override
    public void drawCircle(IPoint a, int radius) {
        content.add(String.format(" <circle cx=\"%d\" cy=\"%d\" r=\"%d\"/>", (int) a.getX(), (int) a.getY(), radius));
    }

    @Override
    public void drawStartPoint(IPoint a) {
        drawCircle(a, 2);
    }


    @Override
    public void drawEndPoint(IPoint a, ICurve c) {
        drawCircle(a, 2);
    }

    public void createSVGContent(ICurve c) {
        drawLine(c);
        drawStartPoint(c.getPoint(0));
        drawEndPoint(c.getPoint(1), c);
    }
}
