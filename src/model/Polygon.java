package model;

import java.util.ArrayList;
import java.util.List;

public class Polygon {

	private int[] x;
    private int[] y;

    private int pointsCount;

    private ArrayList<Point> points;

    public Polygon(int[] x, int[] y, int pointsCount) {
        this.x = x;
        this.y = y;
        this.pointsCount = pointsCount;
    }

    public int[] getX() {
        return x;
    }

    public void setX(int[] x) {
        this.x = x;
    }

    public int[] getY() {
        return y;
    }

    public void setY(int[] y) {
        this.y = y;
    }

    public int getPointsCount() {
        return pointsCount;
    }

    public void setPointsCount(int pointsCount) {
        this.pointsCount = pointsCount;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void addPoints(List<Point> points) {
        points.addAll(points);
    }

    public void addPoint(Point point) {
        points.add(point);
    }
}
