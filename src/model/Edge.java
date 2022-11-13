package model;

public class Edge{
    public int x1, x2, y1, y2;

    public Edge (int x1,int x2,int y1,int y2){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }
    public void orientate(){
        if(y1>y2) {
            int temp = y1;
            y1=y2;
            y2=temp;
            temp = x1;
            x1=x2;
            x2=temp;
        }

    }
    public boolean isHorizontal(){
        return y1 == y2;
    }

    public boolean hasIntersection(int y){
        return y>=y1 && y<=y2;
    }

    public int getIntersection(int y) {
        //return X coordinate of intersection
        //from function x=f(y)
        return 0;
    }
}