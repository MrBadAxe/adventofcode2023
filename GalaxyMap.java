import java.util.ArrayList;

public class GalaxyMap{
  private final char EMPTY = '.';
  public final int HEIGHT;
  public final int WIDTH;
  char[][] grid;

  public GalaxyMap(int height, int width){
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

  public ArrayList<Point> findAllGalaxies(){
    ArrayList<Point> galaxies = new ArrayList<Point>();
    for(int row=0;row<HEIGHT;row++){
      for(int col=0;col<WIDTH;col++){
        if(this.get(row,col) == '#'){
          galaxies.add(new Point(row,col));
        }
      }
    }
    return galaxies;
  }

  public ArrayList<Integer> getEmptyRows(){
    ArrayList<Integer> emptyRows = new ArrayList<Integer>();
    for(int row=0;row<HEIGHT;row++){
      boolean hasGalaxy = false;
      for(int col=0;col<WIDTH;col++){
        if(this.get(row,col) == '#'){
          hasGalaxy = true;
        }
      }
      if(!hasGalaxy){
        emptyRows.add(row);
      }
    }
    return emptyRows;
  }
  public ArrayList<Integer> getEmptyCols(){
    ArrayList<Integer> emptyCols = new ArrayList<Integer>();
    for(int col=0;col<WIDTH;col++){
      boolean hasGalaxy = false;
      for(int row=0;row<HEIGHT;row++){
        if(this.get(row,col) == '#'){
          hasGalaxy = true;
        }
      }
      if(!hasGalaxy){
        emptyCols.add(col);
      }
    }
    return emptyCols;
  }

  public GalaxyMap expand(int extraCells){
    ArrayList<Integer> emptyRows = getEmptyRows();
    ArrayList<Integer> emptyCols = getEmptyCols();
    System.out.println(emptyRows.toString());
    System.out.println(emptyCols.toString());
    int expHeight = this.HEIGHT + (emptyRows.size() * extraCells);
    int expWidth = this.WIDTH + (emptyCols.size() * extraCells);
    GalaxyMap expanded = new GalaxyMap(expHeight, expWidth);
    int rowCursor = 0;
    for(int row=0;row<expHeight;row++){
      boolean isEmptyRow = rowCursor < emptyRows.size() ? (emptyRows.get(rowCursor)+(rowCursor*extraCells) == row) : false;
      if(isEmptyRow){
        row+=extraCells;
        rowCursor++;
        continue;
      }
      int colCursor = 0;
      for(int col=0;col<expWidth;col++){
        boolean isEmptyCol = colCursor < emptyCols.size() ? (emptyCols.get(colCursor)+(colCursor*extraCells) == col) : false;
        if(isEmptyCol){
          col+=extraCells;
          colCursor++;
          continue;
        }
        expanded.set(row,col,this.get(row-(rowCursor*extraCells),col-(colCursor*extraCells)));
      }
    }
    return expanded;
  }

}
