package rasterize;


import model.Edge;
import model.Point;
import model.Polygon;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PolygonRasterizerImpl extends PolygonRasterizer{

    public PolygonRasterizerImpl(Raster raster) {
        super(raster);
    }

    private ArrayList<Point> in;

    public static Point[] getIntersections(Polygon p, int l1x1, int l1y1,
                                           int l1x2, int l1y2) {
        List<Point> intersections = new ArrayList<Point>();
        for (int i = 0; i < p.getPointsCount(); i++) {
            int j = i == p.getPointsCount() - 1 ? 0 : i + 1;
            int l2x1 = p.getX()[i];
            int l2x2 = p.getX()[j];
            int l2y1 = p.getY()[i];
            int l2y2 = p.getY()[j];

            Point intersect = getIntersection(l1x1, l1y1, l1x2, l1y2, l2x1,
                    l2y1, l2x2, l2y2);
            if (intersect != null)
                intersections.add(intersect);
        }
        return intersections.toArray(new Point[] {});
    }

    public static Point[] getIntersections(List<Polygon> polygons, int x1,
                                           int y1, int x2, int y2) {
        List<Point> intersections = new ArrayList<Point>();
        for (Polygon p : polygons) {
            Point[] polygonIntersects = getIntersections(p, x1, y1, x2, y2);
            for (Point pt : polygonIntersects)
                intersections.add(pt);
        }
        return intersections.toArray(new Point[] {});
    }

    public static Point getIntersection(int l1x1, int l1y1, int l1x2,
                                        int l1y2, int l2x1, int l2y1, int l2x2, int l2y2) {
        double d = (l2y2 - l2y1) * (l1x2 - l1x1) - (l2x2 - l2x1)
                * (l1y2 - l1y1);
        double na = (l2x2 - l2x1) * (l1y1 - l2y1) - (l2y2 - l2y1)
                * (l1x1 - l2x1);
        double nb = (l1x2 - l1x1) * (l1y1 - l2y1) - (l1y2 - l1y1)
                * (l1x1 - l2x1);
        if (d == 0D)
            return null;

        double ua = na / d;
        double ub = nb / d;
        if (ua >= 0D && ua <= 1D && ub >= 0D && ub <= 1D) {

            int x = (int) (l1x1 + (ua * (l1x2 - l1x1)));
            int y = (int) (l1y1 + (ua * (l1y2 - l1y1)));
            Point intersect = new Point(x ,y );
            return intersect;
        }

        return null;
    }
    @Override
    public void drawPolygon(int[] x, int[] y, int points) {
        Graphics g = ((RasterBufferedImage)raster).getImg().getGraphics();
        Polygon polygon = new Polygon(x, y, points);
        g.setColor(this.color);
        g.drawPolygon(polygon.getX(), polygon.getY(), polygon.getPointsCount());
    }

    public void fillPolygon(int[] x, int[] y, int points) {
        Graphics g = ((RasterBufferedImage)raster).getImg().getGraphics();
        Polygon polygon = new Polygon(x, y, points);
        g.setColor(this.color);
        g.fillPolygon(polygon.getX(), polygon.getY(), polygon.getPointsCount());
    }
}
