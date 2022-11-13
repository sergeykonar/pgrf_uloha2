package model.solid;

import transforms.Point3D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Solid {

    protected boolean isVisible = true;

    protected int color;

    protected List<Point3D> vertexBuffer = new ArrayList<>();
    List<Integer> indexBuffer = new ArrayList<>();

    protected final void addIndices(Integer... indices) {
        indexBuffer.addAll(Arrays.asList(indices));
    }

    public List<Point3D> getVertexBuffer() {
        return vertexBuffer;
    }

    public List<Integer> getIndexBuffer() {
        return indexBuffer;
    }

    public int getColor() {
        return color;
    }



    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}