package fill;

import model.Edge;
import model.Polygon;
import rasterize.Raster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScanLine extends Filler {

    private Polygon polygon;
    private List<Edge> edges = new ArrayList<>();
    private int ymin = 0;
    private int ymax = 0;

    public ScanLine(Raster raster) {
        super(raster);
    }


    @Override
    public void fill() {
        List<Integer> intersections = new ArrayList<>();
        for (int y = ymin; y <= ymax; y++){
            //find all intersections with edges))
            for (Edge edge: edges) {
                if (edge.hasIntersection(y)) {
                    int x = edge.getIntersection(y);
                    intersections.add(x);
                }
            }
            //sort intersections
            Collections.sort(intersections);

            for(int i=0; i <=intersections.size();i=i+2){
                int x1 = intersections.get(i);
                int x2 = intersections.get(i+1);
                for(int x = x1; x<=x2;x++){
                    raster.setPixel(x,y,0x00ffff);
                }
            }

        }
    }

    @Override
    public void setFillColor(int color) {

    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
        edges.clear();
        ymin = polygon.getPoints().get(0).getY();
        ymax = polygon.getPoints().get(0).getY();

        for(int i=0;i<polygon.getPoints().size();i++){
            int x1 = polygon.getPoints().get(i).getX();
            int y1 = polygon.getPoints().get(i).getY();
            int idx = (i+1)%polygon.getPoints().size();
            int x2 = polygon.getPoints().get(idx).getX();
            int y2 = polygon.getPoints().get(idx).getY();



            Edge edge = new Edge(x1, x2, y1, y2);
            if (!edge.isHorizontal()) {
                edge.orientate();
                if(ymin > edge.y1)
                    ymin = edge.y1;
                if(ymax< edge.y2)
                    ymax = edge.y2;
                edges.add(edge);
            }
        }
    }
}
