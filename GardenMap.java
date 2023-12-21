import java.util.ArrayList;

public class GardenMap extends CharGrid{
  public GardenMap(int height, int width){
    super(height,width,'.');
  }

  public Point getStart(){
    for(int row=0;row<HEIGHT;row++){
      for(int col=0;col<WIDTH;col++){
        if(this.get(row,col) == 'S'){
          return new Point(row,col);
        }
      }
    }
    return null;
  }
  public GardenMap clone(){
    GardenMap z = new GardenMap(this.getHeight(),this.getWidth());
    for(int row=0;row<HEIGHT;row++){
      for(int col=0;col<WIDTH;col++){
        z.set(row,col,this.get(row,col));
      }
    }
    return z;
  }

  public ArrayList<Point> getNeighbors(Point p){
    long x = p.getX();
    long y = p.getY();
    ArrayList<Point> z = new ArrayList<Point>();
    if(x > 0){
      z.add(new Point(x-1,y));
    }
    if(x < this.getHeight()-1){
      z.add(new Point(x+1,y));
    }
    if(y > 0){
      z.add(new Point(x,y-1));
    }
    if(y < this.getWidth()-1){
      z.add(new Point(x,y+1));
    }
    return z;
  }
  public GardenMap step(){
    GardenMap next = this.clone();
    if(this.getStart() != null){
      Point start = this.getStart();
      ArrayList<Point> neighbors = getNeighbors(start);
      for(Point p : neighbors){
        int x = (int)p.getX();
        int y = (int)p.getY();
        if(next.get(x,y) != '#'){
          next.set((int)p.getX(),(int)p.getY(),'O');
        }
      }
      next.set((int)start.getX(),(int)start.getY(),'.');
    }else{
      for(int row=0;row<this.getHeight();row++){
        for(int col=0;col<this.getWidth();col++){
          if(this.get(row,col) == 'O'){
            Point here = new Point(row,col);
            ArrayList<Point> neighbors = getNeighbors(here);
            for(Point p : neighbors){
              int x = (int)p.getX();
              int y = (int)p.getY();
              if(next.get(x,y) != '#'){
                next.set((int)p.getX(),(int)p.getY(),'O');
              }
            }
            next.set((int)here.getX(),(int)here.getY(),'.');
          }
        }
      }
    }
    return next;
  }
}
