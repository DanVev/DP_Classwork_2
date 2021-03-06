import Commands.CommandManager;
import Commands.ICommand;
import Commands.PointInitCommand;
import Commands.PointShiftCommand;
import Composite.Chain;
import Contexts.Graphics2DContext;
import Contexts.Graphics2DDottedContext;
import Contexts.IGContext;
import Contexts.SVGContext;
import Decorators.ActivePointDecorator;
import Decorators.ShellDecorator;
import Drawable.ICurve;
import Drawable.IPoint;
import Drawable.Line;
import Drawable.Point;
import Visual.IActiveComponent;
import Visual.IVisualCurve;
import Visual.VisualCurve;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vasily Danilin on 03.11.2017.
 */
public class PlotForm {
    private JButton button1;
    private JPanel jPanelGeneral;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JButton svgButton1;
    private JButton undoButton1;
    private Canvas canvas1 = new Canvas(false);
    private Canvas canvas2 = new Canvas(true);

    private PlotForm() {
        $$$setupUI$$$();
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                svgButton1.setEnabled(true);
                jPanelGeneral.repaint();
            }
        });
        svgButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                saveSVG("./svg1.svg", canvas1.main);
            }
        });
        undoButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CommandManager.getInstance().undo();
                jPanelGeneral.repaint();
            }
        });
    }

    private void saveSVG(String pathname, ICurve curve) {
        SVGContext svgContext = new SVGContext();
        svgContext.createSVGContent(curve);
        File file = new File(pathname);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(svgContext.getXML());
            fileWriter.close();
        } catch (IOException e1) {
            e1.printStackTrace();
            System.out.println("problem with file");
        }
        System.out.println(svgContext.getXML());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("PlotForm");
        final PlotForm plotForm = new PlotForm();
        frame.setContentPane(plotForm.jPanelGeneral);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    private void createUIComponents() {
        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jPanel1.add(canvas1);
        jPanel2.add(canvas2);

        canvas1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                canvas1.nearest = findNearest(e, canvas1);
                canvas1.oldPoint = new Point(e.getX(), e.getY());
            }
        });

        canvas2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                canvas2.nearest = findNearest(e, canvas2);
                canvas2.oldPoint = new Point(e.getX(), e.getY());
            }
        });
        canvas1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                moveNearest(e, canvas1);

            }
        });
        canvas2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                moveNearest(e, canvas2);

            }
        });
    }

    private void moveNearest(MouseEvent e, Canvas panel) {
        IPoint point = panel.nearest;
        ICommand shiftCommand = new PointShiftCommand(point, new Point(e.getX() - panel.oldPoint.getX(), e.getY() - panel.oldPoint.getY()));
        shiftCommand.execute();
        panel.repaint();
    }

    private IPoint findNearest(MouseEvent e, Canvas panel) {
        boolean isFirst = true;
        double minLength = 0.0;
        IPoint nearest = null;
        for (IPoint point : panel.getActiveComponentList()) {
            double length = (e.getX() - point.getX()) * (e.getX() - point.getX()) + (e.getY() - point.getY()) * (e.getY() - point.getY());
            if ((length < minLength) || (isFirst)) {
                minLength = length;
                nearest = point;
            }
            isFirst = false;
        }
        return nearest;
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        jPanelGeneral = new JPanel();
        jPanelGeneral.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));
        button1 = new JButton();
        button1.setText("Paint plots");
        jPanelGeneral.add(button1, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jPanelGeneral.add(jPanel2, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(500, 500), new Dimension(500, 500), null, 0, false));
        jPanelGeneral.add(jPanel1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(500, 500), new Dimension(500, 500), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Standard Paint");
        jPanelGeneral.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Dotted Paint");
        jPanelGeneral.add(label2, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        svgButton1 = new JButton();
        svgButton1.setEnabled(false);
        svgButton1.setText("Save as SVG file");
        jPanelGeneral.add(svgButton1, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        undoButton1 = new JButton();
        undoButton1.setText("Undo");
        jPanelGeneral.add(undoButton1, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return jPanelGeneral;
    }
}

class Canvas extends JPanel {
    private boolean isDotted;
    private boolean isFirst = true;
    ICurve main;
    private List<IActiveComponent> activeComponentList = new ArrayList<>();
    IPoint nearest;
    IPoint oldPoint;
    IActiveComponent aPoint = new ActivePointDecorator(new Point(15, 130));
    IActiveComponent bPoint = new ActivePointDecorator(new Point(300, 70));
    IActiveComponent cPoint = new ActivePointDecorator(new Point(250, 300));
    IActiveComponent dPoint = new ActivePointDecorator(new Point(450, 400));

    Line a = new Line(aPoint, bPoint);
    Line b = new Line(bPoint, cPoint);
    Line c = new Line(cPoint, dPoint);
    IVisualCurve curve = new ShellDecorator(new VisualCurve(new Chain(a, new Chain(b, c))), 8);

    Canvas(boolean isDotted) {
        super();
        this.isDotted = isDotted;
        setPreferredSize(new Dimension(500, 500));
        activeComponentList.add(aPoint);
        activeComponentList.add(bPoint);
        activeComponentList.add(cPoint);
        activeComponentList.add(dPoint);
        ICommand aCommand = new PointInitCommand(aPoint);
        ICommand bCommand = new PointInitCommand(bPoint);
        ICommand cCommand = new PointInitCommand(cPoint);
        ICommand dCommand = new PointInitCommand(dPoint);
        aCommand.execute();
        bCommand.execute();
        cCommand.execute();
        dCommand.execute();

        System.out.println("do");

        this.main = curve;

    }

    public List<IActiveComponent> getActiveComponentList() {
        return activeComponentList;
    }

    public void paintComponent(Graphics g) {
        System.out.println("repaint...");
        super.paintComponents(g);
        g.clearRect(0, 0, 500, 500);
        IGContext context;
        if (!isFirst) {
            context = isDotted ? new Graphics2DDottedContext(g) : new Graphics2DContext(g);
            curve.draw(context);
        }
        isFirst = false;

    }

}