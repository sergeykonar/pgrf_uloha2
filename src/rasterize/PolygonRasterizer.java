package rasterize;

import model.Line;
import model.Point;
import model.Polygon;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public abstract class PolygonRasterizer {

    Raster raster;

    Color color;

    public PolygonRasterizer(Raster raster) {
        this.raster = raster;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    protected void drawPolygon(int []x, int []y, int points){

    }
}
