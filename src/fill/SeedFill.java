package fill;

import model.Point;
import rasterize.Raster;

import java.util.ArrayList;

public class SeedFill extends Filler {

    private int seedX;
    private int seedY;
    private int color;
    private int backgroundColor;

    private ArrayList<Point> points;
    private Point point;

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public SeedFill(Raster raster) {
        super(raster);
    }
    private void fill(int x, int y, int color) {
        if(isInside(x, y)) {
            System.out.println(point.toString());
            raster.setPixel(x,y,color);
            fill(x+1,y, color);
            fill(x,y+1, color);
            fill(x-1,y, color);
            fill(x,y-1, color);
        }
    }

    private boolean isInside(int x, int y) {
        return raster.getPixel(x,y) == backgroundColor;

    }

    @Override
    public void fill() {
        backgroundColor = raster.getPixel(seedX, seedY);
        point = new Point(seedX, seedY);
        fill(seedX, seedY, 0xffff);
    }

    @Override
    public void setFillColor(int color) {
        this.color = color;

    }

    public void setSeed(int x,int y){
        this.seedX = x;
        this.seedY = y;
    }
}
