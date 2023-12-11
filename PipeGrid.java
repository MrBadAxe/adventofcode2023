import java.util.ArrayList;

public class PipeGrid{
  private final char EMPTY = '.';

  public final int HEIGHT;
  public final int WIDTH;
  char[][] grid;

  public PipeGrid(int height, int width){
    this.HEIGHT = height;
    this.WIDTH = width;
    this.grid = new char[HEIGHT][WIDTH];
    for(int row=0;row<HEIGHT;row++){
      for(int col=0;col<WIDTH;col++){
        grid[row][col] = EMPTY;
      }
    }
  }
  public char get(int row, int col){
    return grid[row][col];
  }
  public void set(int row, int col, char c){
    grid[row][col] = c;
  }
  public String toString(){
    String z = "";
    for(int row=0;row<HEIGHT;row++){
      for(int col=0;col<WIDTH;col++){
        z += this.get(row,col);
      }
      z += "\n";
    }
    return z;
  }
  public Point getStart(){
    for(int row=0;row<HEIGHT;row++){
      for(int col=0;col<WIDTH;col++){
        if(grid[row][col] == 'S'){
          return new Point(row,col);
        }
      }
    }
    return null;
  }
  public ArrayList<Point> exits(Point p){
    return exits((int)p.getX(),(int)p.getY());
  }
  public ArrayList<Point> exits(int row, int col){
    ArrayList<Point> z = new ArrayList<Point>();

    char c = grid[row][col];
    if(c == '-' || c == 'J' || c == '7'){ z.add(new Point(row,col-1)); }
    if(c == '-' || c == 'L' || c == 'F'){ z.add(new Point(row,col+1)); }
    if(c == '|' || c == 'L' || c == 'J'){ z.add(new Point(row-1,col)); }
    if(c == '|' || c == 'F' || c == '7'){ z.add(new Point(row+1,col)); }

    if(c == 'S'){
      ArrayList<Point> neighbors = new ArrayList<Point>();
      if(row-1 >= 0)      {  neighbors.add(new Point(row-1,col)); }
      if(row+1 < HEIGHT)  {  neighbors.add(new Point(row+1,col)); }
      if(col-1 >= 0)      {  neighbors.add(new Point(row,col-1)); }
      if(col+1 < WIDTH)   {  neighbors.add(new Point(row,col+1)); }
      for(Point p : neighbors){
        if(exits(p).contains(new Point(row,col))){
          z.add(p);
        }
      }
    }
    return z;
  }
  public char identifyStartEquivalentPiece(){
    Point start = getStart();
    long row = start.getX();
    long col = start.getY();
    ArrayList<Point> exits = exits(start);

    if(row != 0 && exits.contains(new Point(row-1,col))){
      if(col != 0 && exits.contains(new Point(row,col-1))){
        return 'J';
      }else if(col != (WIDTH-1) && exits.contains(new Point(row,col+1))){
        return 'L';
      }else{
        return '|';
      }
    }else if(row != (HEIGHT-1) && exits.contains(new Point(row+1,col))){
      if(col != 0 && exits.contains(new Point(row,col-1))){
        return '7';
      }else if(col != (WIDTH-1) && exits.contains(new Point(row,col+1))){
        return 'F';
      }else{
        return '|';
      }
    }else{
      return '-';
    }
  }
  public ArrayList<Point> getAllPointsOnLoop(){
    ArrayList<Point> path = new ArrayList<Point>();
    Point start = getStart();
    path.add(start);
    boolean fullLoop = false;
    while(!fullLoop){
      Point last = path.get(path.size()-1);
      if(last.equals(path.get(0)) && path.size() > 1){
        fullLoop = true;
        continue;
      }
      Point nextLast = (path.size() >= 2 ? path.get(path.size()-2) : start);
      ArrayList<Point> exits = exits(last);
      if(exits.get(0).equals(nextLast)){
        path.add(exits.get(1));
      }else{
        path.add(exits.get(0));
      }
    }
    return path;
  }

}
