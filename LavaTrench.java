import java.util.List;
import java.util.ArrayList;
public class LavaTrench{
  CharGrid grid;
  private final int rowOffset;
  private final int colOffset;

  public LavaTrench(List<Point> points){
    long rowMin = Long.MAX_VALUE;
    long rowMax = Long.MIN_VALUE;
    long colMin = Long.MAX_VALUE;
    long colMax = Long.MIN_VALUE;

    for(Point p : points){
      rowMin = Math.min(rowMin,p.getX());
      rowMax = Math.max(rowMax,p.getX());
      colMin = Math.min(colMin,p.getY());
      colMax = Math.max(colMax,p.getY());
    }
    long height = (rowMax - rowMin) + 1;
    long width = (colMax - colMin) + 1;
    rowOffset = (int)rowMin;
    colOffset = (int)colMin;
    grid = new CharGrid((int)height,(int)width,'.');
    for(int k=0;k<points.size()-1;k++){
      digTrench(points.get(k),points.get(k+1));
    }
  }
  private void digTrench(Point from, Point to){
    if(from.getX() == to.getX()){
      for(long k=Math.min(from.getY(),to.getY());k<=Math.max(from.getY(),to.getY());k++){
        grid.set((int)from.getX() - rowOffset,(int)k - colOffset,'#');
      }
    }
    if(from.getY() == to.getY()){
      for(long k=Math.min(from.getX(),to.getX());k<=Math.max(from.getX(),to.getX());k++){
        grid.set((int)k - rowOffset,(int)from.getY() - colOffset,'#');
      }
    }
  }
  public void floodFill(){
    ArrayList<Point> toBeFilled = new ArrayList<Point>();

    int startCol=0;
    while(grid.get(0,startCol) != '#'){
      startCol++;
    }
    toBeFilled.add(new Point(1,startCol+1));
    while(toBeFilled.size() > 0){
      Point p = toBeFilled.remove(0);
      //System.out.println(p);
      int row = (int)p.getX();
      int col = (int)p.getY();
      grid.set(row,col,'#');
      if(grid.get(row-1,col) != '#' && !toBeFilled.contains(new Point(row-1,col))){ toBeFilled.add(new Point(row-1,col)); }
      if(grid.get(row+1,col) != '#' && !toBeFilled.contains(new Point(row+1,col))){ toBeFilled.add(new Point(row+1,col)); }
      if(grid.get(row,col-1) != '#' && !toBeFilled.contains(new Point(row,col-1))){ toBeFilled.add(new Point(row,col-1)); }
      if(grid.get(row,col+1) != '#' && !toBeFilled.contains(new Point(row,col+1))){ toBeFilled.add(new Point(row,col+1)); }
    }
  }
  public long getVolume(){
    long total = 0;
    for(int row=0;row<grid.getHeight();row++){
      for(int col=0;col<grid.getWidth();col++){
        total += (grid.get((int)row,(int)col) == '#') ? 1 : 0;
      }
    }
    return total;
  }
  public String toString(){
    return grid.toString();
  }
}
