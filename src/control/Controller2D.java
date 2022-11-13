package control;

import fill.SeedFill;
import model.Point;
import model.Polygon;
import rasterize.*;
import view.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Controller2D extends Controller {

    private final Panel panel;

    private int x,y;
    private PolygonRasterizerImpl rasterizer;
    private PolygonRasterizerImpl polygonRasterizer;

    private ArrayList<Polygon> polygons = new ArrayList<>();
    private ArrayList<Point> points = new ArrayList<>();

    public Controller2D(Panel panel) {
        this.panel = panel;
        initObjects(panel.getRaster());
        initListeners(panel);
    }

    public void initObjects(Raster raster) {
//        rasterizer = new LineRasterizerGraphics(raster);
        rasterizer = new PolygonRasterizerImpl(raster);
        polygonRasterizer = new PolygonRasterizerImpl(raster);
        int []x = {0, 100, 200};
        int []y = {0, 200, 100};

        Polygon polygon = new Polygon(x, y, 3);
        polygons.add(polygon);


        Point p = new Point(polygon.getX()[0], polygon.getY()[0]);
        points.add(p);
     }

    @Override
    public void initListeners(Panel panel) {
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_F){
                    changeState();
                }
            }
        });
        panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isControlDown()) return;

                if (e.isShiftDown()) {
                    //TODO
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    if(isDrawing){
                        rasterizer.setColor(Color.GREEN);
                        x = e.getX();
                        y = e.getY();

                        int []x = {0, 100, 200};
                        int []y = {0, 200, 100};
                        polygonRasterizer.setColor(Color.ORANGE);
                        polygonRasterizer.drawPolygon(x, y, 3);
                    }
                    println(polygons.size() + "");
                    if (isFilling){
                        SeedFill seedFill = new SeedFill(panel.getRaster());
                        seedFill.setSeed(e.getX(), e.getY());
                        seedFill.setPoints(points);
                        seedFill.fill();
                    }

                } else if (SwingUtilities.isMiddleMouseButton(e)) {
                    //TODO
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    //TODO
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.isControlDown()) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        //TODO
                    } else if (SwingUtilities.isRightMouseButton(e)) {
                        //TODO
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(isDrawing){
                    int[] xPoints = {x, e.getX()};
                    int[] yPoints = {y, e.getY()};

                    if (!polygons.isEmpty()) {

                        ArrayList<Integer> xS = new ArrayList<>();
                        ArrayList<Integer> yS = new ArrayList<>();


                        for (int i = 0; i <= polygons.size() - 1; i++) {
                            Polygon p1 = polygons.get(i);
                            xS.add(p1.getX()[i]);
                            yS.add(p1.getY()[i]);
                            Point x = new Point(p1.getX()[i], p1.getY()[i]);
                            points.add(x);
                        }
                        xS.add(e.getX());
                        yS.add(e.getY());

                        int[] newX = xS.stream().mapToInt(i -> i).toArray();
                        int[] newY = yS.stream().mapToInt(i -> i).toArray();

                        Polygon p = new Polygon(newX, newY, newX.length);
                        polygons.add(p);
                        panel.clear();
                        int []x1 = {0, 100, 200};
                        int []y1 = {0, 200, 100};
                        polygonRasterizer.setColor(Color.ORANGE);
                        polygonRasterizer.drawPolygon(x1, y1, 3);
                        rasterizer.drawPolygon(newX, newY, newX.length);

                        Point [] points = PolygonRasterizerImpl.getIntersections(p, x1[0], y1[0], x1[1], y1[1]);

                        for (Point p2: points
                             ) {
                            println(p2.x + " " + p2.y );
                        }

                        int [] nX = new int[points.length];
                        int [] nY = new int[points.length];
                        int i = 0;
                        for (Point p2: points
                             ) {
                            nX[i] = p2.x;
                            nX[i] = p2.y;
                            i++;
                        }

                        rasterizer.fillPolygon(nX, nY, points.length);
                    } else {
                        Polygon p = new Polygon(xPoints, yPoints, 1);
                        polygons.add(p);
                        rasterizer.drawPolygon(xPoints, yPoints, polygons.size() + 1);
                    }
                }

            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {


                if (e.isControlDown()) return;

                if (e.isShiftDown()) {
                    //TODO
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    if (isDrawing){
                        if (!polygons.isEmpty()) {

                            ArrayList<Integer> xS = new ArrayList<>();
                            ArrayList<Integer> yS = new ArrayList<>();


                            for (int i = 0; i <= polygons.size() - 1; i++) {
                                Polygon p1 = polygons.get(i);
                                xS.add(p1.getX()[i]);
                                yS.add(p1.getY()[i]);

                            }
                            xS.add(e.getX());
                            yS.add(e.getY());

                            int[] newX = xS.stream().mapToInt(i -> i).toArray();
                            int[] newY = yS.stream().mapToInt(i -> i).toArray();

                            Polygon p = new Polygon(newX, newY, newX.length);

                            panel.clear();
                            int []x1 = {0, 100, 200};
                            int []y1 = {0, 200, 100};
                            polygonRasterizer.setColor(Color.ORANGE);
                            polygonRasterizer.drawPolygon(x1, y1, 3);
                            rasterizer.drawPolygon(newX, newY, newX.length);

                        } else {
                            int[] xPoints = {x, e.getX()};
                            int[] yPoints = {y, e.getY()};
                            Polygon p = new Polygon(xPoints, yPoints, 1);
                            polygons.add(p);
                            rasterizer.drawPolygon(xPoints, yPoints, polygons.size() + 1);

                        }
                    }

                } else if (SwingUtilities.isRightMouseButton(e)) {
                    //TODO
                } else if (SwingUtilities.isMiddleMouseButton(e)) {
                    //TODO
                }
                update();
            }
        });

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // na klávesu C vymazat plátno
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    panel.clear();
                    polygons.clear();

                    int []x1 = {0, 100, 200};
                    int []y1 = {0, 200, 100};
                    polygonRasterizer.setColor(Color.ORANGE);
                    polygonRasterizer.drawPolygon(x1, y1, 3);
                }
            }
        });

        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                panel.resize();
                initObjects(panel.getRaster());
            }
        });
    }

    private void update() {
//        panel.clear();
        //TODO

    }

    private void hardClear() {
        panel.clear();
    }

    public void println(String s){
        System.out.println(s);
    }


}
