public class LavaGrid implements Cloneable{
  private final int HEIGHT;
  private final int WIDTH;
  private char[][] grid;

  public LavaGrid(int x, int y, char init){
    this.HEIGHT = x;
    this.WIDTH = y;
    this.grid = new char[HEIGHT][WIDTH];
    for(int row=0;row<HEIGHT;row++){
      for(int col=0;col<WIDTH;col++){
        grid[row][col] = init;
      }
    }
  }
  public int getHeight(){
    return this.HEIGHT;
  }
  public int getWidth(){
    return this.WIDTH;
  }
  public char get(int row, int col){
    return grid[row][col];
  }
  public void set(int row, int col, char c){
    grid[row][col] = c;
  }
  public char[] getRow(int row){
    char[] z = new char[WIDTH];
    for(int col=0;col<WIDTH;col++){
      z[col] = grid[row][col];
    }
    return z;
  }
  public char[] getCol(int col){
    char[] z = new char[HEIGHT];
    for(int row=0;row<HEIGHT;row++){
      z[row] = grid[row][col];
    }
    return z;
  }
  public String toString(){
    String z = "";
    for(int row=0;row<HEIGHT;row++){
      for(int col=0;col<WIDTH;col++){
        z += grid[row][col];
      }
      z += "\n";
    }
    return z;
  }
  public LavaGrid clone(){
    LavaGrid z = new LavaGrid(this.getHeight(),this.getWidth(),'.');
    for(int row=0;row<HEIGHT;row++){
      for(int col=0;col<WIDTH;col++){
        z.set(row,col,this.get(row,col));
      }
    }
    return z;
  }
  public static boolean linesMatch(char[] a, char[] b){
    if(a.length != b.length){
      return false;
    }
    for(int k=0;k<a.length;k++){
      if(a[k] != b[k]){
        return false;
      }
    }
    return true;
  }
  public int findReflectionH(){
    for(int row=0;row<HEIGHT-1;row++){
      if(LavaGrid.linesMatch(this.getRow(row),this.getRow(row+1))){
        int start = row;
        int end = row+1;
        while(start-1 >= 0 && end+1 < this.getHeight() && LavaGrid.linesMatch(this.getRow(start-1),this.getRow(end+1))){
          start--;
          end++;
          //System.out.println(start + " " + end);
        }
        if(start == 0 || end == this.getHeight()-1){
          return row;
        }
      }
    }
    return -1;
  }
  public int findReflectionV(){
    for(int col=0;col<WIDTH-1;col++){
      if(LavaGrid.linesMatch(this.getCol(col),this.getCol(col+1))){
        int start = col;
        int end = col+1;
        while(start-1 >= 0 && end+1 < this.getWidth() && LavaGrid.linesMatch(this.getCol(start-1),this.getCol(end+1))){
          start--;
          end++;
          //System.out.println(start + " " + end);
        }
        if(start == 0 || end == this.getWidth()-1){
          return col;
        }
      }
    }
    return -1;
  }
}
