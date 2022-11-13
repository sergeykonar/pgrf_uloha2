package fill;

import rasterize.Raster;

public abstract class Filler {

    public Filler(Raster raster){
        this.raster = raster;
    }
    protected Raster raster;

    void fill() {

    }

    void setFillColor(int color) {

    }

}
